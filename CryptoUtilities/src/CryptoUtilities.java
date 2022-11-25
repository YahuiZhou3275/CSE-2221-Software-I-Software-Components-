import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Utilities that could be used with RSA cryptosystems.
 *
 * @author Yahui Zhou
 *
 */
public final class CryptoUtilities {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CryptoUtilities() {
    }

    /**
     * Useful constant, not a magic number: 3.
     */
    private static final int THREE = 3;

    /**
     * Useful constant, not a magic number: 1.
     */
    private static final NaturalNumber ONE = new NaturalNumber2(1);

    /**
     * Useful constant, not a magic number: 1.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2);
    /**
     * Pseudo-random number generator.
     */
    private static final Random GENERATOR = new Random1L();

    /**
     * Returns a random number uniformly distributed in the interval [0, n].
     *
     * @param n
     *            top end of interval
     * @return random number in interval
     * @requires n > 0
     * @ensures <pre>
     * randomNumber = [a random number uniformly distributed in [0, n]]
     * </pre>
     */
    public static NaturalNumber randomNumber(NaturalNumber n) {
        assert !n.isZero() : "Violation of: n > 0";
        final int base = 10;
        NaturalNumber result;
        int d = n.divideBy10();
        if (n.isZero()) {
            /*
             * Incoming n has only one digit and it is d, so generate a random
             * number uniformly distributed in [0, d]
             */
            int x = (int) ((d + 1) * GENERATOR.nextDouble());
            result = new NaturalNumber2(x);
            n.multiplyBy10(d);
        } else {
            /*
             * Incoming n has more than one digit, so generate a random number
             * (NaturalNumber) uniformly distributed in [0, n], and another
             * (int) uniformly distributed in [0, 9] (i.e., a random digit)
             */
            result = randomNumber(n);
            int lastDigit = (int) (base * GENERATOR.nextDouble());
            result.multiplyBy10(lastDigit);
            n.multiplyBy10(d);
            if (result.compareTo(n) > 0) {
                /*
                 * In this case, we need to try again because generated number
                 * is greater than n; the recursive call's argument is not
                 * "smaller" than the incoming value of n, but this recursive
                 * call has no more than a 90% chance of being made (and for
                 * large n, far less than that), so the probability of
                 * termination is 1
                 */
                result = randomNumber(n);
            }
        }
        return result;
    }

    /**
     * Finds the greatest common divisor of n and m.
     *
     * @param n
     *            one number
     * @param m
     *            the other number
     * @updates n
     * @clears m
     * @ensures n = [greatest common divisor of #n and #m]
     */
    public static void reduceToGCD(NaturalNumber n, NaturalNumber m) {

        /*
         * Use Euclid's algorithm; in pseudocode: if m = 0 then GCD(n, m) = n
         * else GCD(n, m) = GCD(m, n mod m)
         */

        // when m != 0, GCD(n, m) = GCD(m, n mod m)
        if (!m.isZero()) {

            NaturalNumber remainder = n.divide(m);

            reduceToGCD(m, remainder);

            // assign m (the greatest common divisor) to n
            n.transferFrom(m);
        }
    }

    /**
     * Reports whether n is even.
     *
     * @param n
     *            the number to be checked
     * @return true iff n is even
     * @ensures isEven = (n mod 2 = 0)
     */
    public static boolean isEven(NaturalNumber n) {

        boolean evenOrNot = false;
        NaturalNumber temp = new NaturalNumber2();
        temp.copyFrom(n);
        NaturalNumber remainder = new NaturalNumber2(temp.divide(TWO));

        // if the remainder is zero, then the NaturalNumber is even
        if (remainder.isZero()) {
            evenOrNot = true;
        }

        return evenOrNot;
    }

    /**
     * Updates n to its p-th power modulo m.
     *
     * @param n
     *            number to be raised to a power
     * @param p
     *            the power
     * @param m
     *            the modulus
     * @updates n
     * @requires m > 1
     * @ensures n = #n ^ (p) mod m
     */
    public static void powerMod(NaturalNumber n, NaturalNumber p,
            NaturalNumber m) {
        assert m.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: m > 1";

        /*
         * Use the fast-powering algorithm as previously discussed in class,
         * with the additional feature that every multiplication is followed
         * immediately by "reducing the result modulo m"
         */

        // when p is zero, then the result is 1
        if (p.isZero()) {
            n.copyFrom(ONE);
        } else {
            // if p is even, then n^p = (n^(p/2))^2
            if (isEven(p)) {
                // first get p/2
                p.divide(TWO);

                // get the remainder of n^(p/2) mod m
                powerMod(n, p, m);

                // n^(p/2) mod m * n^(p/2) mod m
                NaturalNumber nTemp = new NaturalNumber2();
                nTemp.copyFrom(n);
                nTemp.multiply(n);

                // get the remainder (the result)
                NaturalNumber remainder = new NaturalNumber2(nTemp.divide(m));

                // assign the remainder (the result) to n
                n.copyFrom(remainder);

                // restore p value
                p.multiply(TWO);

            } else {
                // if p is odd, then n^p = (n^(p/2))^2 * n
                NaturalNumber nOriginal = new NaturalNumber2();
                nOriginal.copyFrom(n);

                // first get p/2
                p.divide(TWO);

                // get the remainder of n^(p/2) mod m
                powerMod(n, p, m);

                // n^(p/2) mod m * n^(p/2) mod m
                NaturalNumber nTemp = new NaturalNumber2();
                nTemp.copyFrom(n);
                nTemp.multiply(n);

                // multiply n since p is odd
                nTemp.multiply(nOriginal);

                // get the remainder (the result)
                NaturalNumber remainder = new NaturalNumber2(nTemp.divide(m));

                // assign the remainder to n
                n.copyFrom(remainder);

                // restore p value
                p.multiply(TWO);
                p.increment();
            }

        }

    }

    /**
     * Reports whether w is a "witness" that n is composite, in the sense that
     * either it is a square root of 1 (mod n), or it fails to satisfy the
     * criterion for primality from Fermat's theorem.
     *
     * @param w
     *            witness candidate
     * @param n
     *            number being checked
     * @return true iff w is a "witness" that n is composite
     * @requires n > 2 and 1 < w < n - 1
     * @ensures <pre>
     * isWitnessToCompositeness =
     *     (w ^ 2 mod n = 1)  or  (w ^ (n-1) mod n /= 1)
     * </pre>
     */
    public static boolean isWitnessToCompositeness(NaturalNumber w,
            NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(2)) > 0 : "Violation of: n > 2";
        assert (new NaturalNumber2(1)).compareTo(w) < 0 : "Violation of: 1 < w";
        n.decrement();
        assert w.compareTo(n) < 0 : "Violation of: w < n - 1";
        n.increment();

        boolean witness = false;
        NaturalNumber two = new NaturalNumber2(2);

        // when 1 < w < n – 1, calculate w^2 mod n
        NaturalNumber temp1 = new NaturalNumber2();
        temp1.copyFrom(w);
        powerMod(temp1, two, n);

        // when 0 < w < n, calculate w^(n-1) mod n
        NaturalNumber temp2 = new NaturalNumber2();
        temp2.copyFrom(w);
        NaturalNumber nMinusOne = new NaturalNumber2();
        nMinusOne.copyFrom(n);
        nMinusOne.decrement();
        powerMod(temp2, nMinusOne, n);

        // if  (w ^ 2 mod n = 1)  or  (w ^ (n-1) mod n /= 1), then
        // the witness candidate can prove that the number is not prime.
        if (temp1.compareTo(ONE) == 0 || temp2.compareTo(ONE) != 0) {
            witness = true;
        }

        return witness;
    }

    /**
     * Reports whether n is a prime; may be wrong with "low" probability.
     *
     * @param n
     *            number to be checked
     * @return true means n is very likely prime; false means n is definitely
     *         composite
     * @requires n > 1
     * @ensures <pre>
     * isPrime1 = [n is a prime number, with small probability of error
     *         if it is reported to be prime, and no chance of error if it is
     *         reported to be composite]
     * </pre>
     */
    public static boolean isPrime1(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";
        boolean isPrime;
        if (n.compareTo(new NaturalNumber2(THREE)) <= 0) {
            /*
             * 2 and 3 are primes
             */
            isPrime = true;
        } else if (isEven(n)) {
            /*
             * evens are composite
             */
            isPrime = false;
        } else {
            /*
             * odd n >= 5: simply check whether 2 is a witness that n is
             * composite (which works surprisingly well :-)
             */
            isPrime = !isWitnessToCompositeness(new NaturalNumber2(2), n);
        }
        return isPrime;
    }

    /**
     * Reports whether n is a prime; may be wrong with "low" probability.
     *
     * @param n
     *            number to be checked
     * @return true means n is very likely prime; false means n is definitely
     *         composite
     * @requires n > 1
     * @ensures <pre>
     * isPrime2 = [n is a prime number, with small probability of error
     *         if it is reported to be prime, and no chance of error if it is
     *         reported to be composite]
     * </pre>
     */
    public static boolean isPrime2(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";

        /*
         * Use the ability to generate random numbers (provided by the
         * randomNumber method above) to generate several witness candidates --
         * say, 10 to 50 candidates -- guessing that n is prime only if none of
         * these candidates is a witness to n being composite (based on fact #3
         * as described in the project description); use the code for isPrime1
         * as a guide for how to do this, and pay attention to the requires
         * clause of isWitnessToCompositeness
         */
        boolean isPrime = false;

        if (n.compareTo(new NaturalNumber2(THREE)) <= 0) {
            /*
             * 2 and 3 are primes
             */
            isPrime = true;
        } else if (isEven(n)) {
            /*
             * evens are composite
             */
            isPrime = false;
        } else {
            /*
             * odd n >= 5, use fact #3 to check whether candidates is a witness
             * to n being composite
             */

            final int fifty = 50;
            NaturalNumber nMinusOne = new NaturalNumber2();
            nMinusOne.copyFrom(n);
            nMinusOne.decrement();

            for (int i = 0; i < fifty; i++) {
                //generate 50 candidates
                NaturalNumber candidate = randomNumber(n);

                // to meet fact 3's precondition
                // 1 < w < n-1
                if (candidate.compareTo(ONE) > 0
                        && candidate.compareTo(nMinusOne) < 0) {
                    isPrime = !isWitnessToCompositeness(candidate, n);
                }
            }

        }
        return isPrime;
    }

    /**
     * Generates a likely prime number at least as large as some given number.
     *
     * @param n
     *            minimum value of likely prime
     * @updates n
     * @requires n > 1
     * @ensures n >= #n and [n is very likely a prime number]
     */
    public static void generateNextLikelyPrime(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";

        /*
         * Use isPrime2 to check numbers, starting at n and increasing through
         * the odd numbers only (why?), until n is likely prime
         */

        if (!isPrime2(n)) {
            n.add(ONE);
            while (!isPrime2(n)) {
                n.add(ONE);
            }
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Sanity check of randomNumber method -- just so everyone can see how
         * it might be "tested"
         */
        final int testValue = 17;
        final int testSamples = 100000;
        NaturalNumber test = new NaturalNumber2(testValue);
        int[] count = new int[testValue + 1];
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < testSamples; i++) {
            NaturalNumber rn = randomNumber(test);
            assert rn.compareTo(test) <= 0 : "Help!";
            count[rn.toInt()]++;
        }
        for (int i = 0; i < count.length; i++) {
            out.println("count[" + i + "] = " + count[i]);
        }
        out.println("  expected value = "
                + (double) testSamples / (double) (testValue + 1));

        /*
         * Check user-supplied numbers for primality, and if a number is not
         * prime, find the next likely prime after it
         */
        while (true) {
            out.print("n = ");
            NaturalNumber n = new NaturalNumber2(in.nextLine());
            if (n.compareTo(new NaturalNumber2(2)) < 0) {
                out.println("Bye!");
                break;
            } else {
                if (isPrime1(n)) {
                    out.println(n + " is probably a prime number"
                            + " according to isPrime1.");
                } else {
                    out.println(n + " is a composite number"
                            + " according to isPrime1.");
                }
                if (isPrime2(n)) {
                    out.println(n + " is probably a prime number"
                            + " according to isPrime2.");
                } else {
                    out.println(n + " is a composite number"
                            + " according to isPrime2.");
                    generateNextLikelyPrime(n);
                    out.println("  next likely prime is " + n);
                }
            }

        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
