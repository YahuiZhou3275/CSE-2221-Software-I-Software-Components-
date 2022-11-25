import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Yahui Zhou
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */
    /**
     * Test ReduceToGCD_with input 0 and 0. reason: condition1 - if m = 0 then
     * GCD(n, m) = n
     */
    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     * Test ReduceToGCD_with input 30 and 21. reason: condition2 - GCD(n, m) =
     * GCD(m, n mod m)
     */
    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     * Test ReduceToGCD_with input 48 and 32. reason: condition2 - GCD(n, m) =
     * GCD(m, n mod m)
     */
    @Test
    public void testReduceToGCD_48_32() {
        NaturalNumber n = new NaturalNumber2(48);
        NaturalNumber nExpected = new NaturalNumber2(16);
        NaturalNumber m = new NaturalNumber2(32);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     * Test ReduceToGCD_with input 2 and 3. reason: condition2 - GCD(n, m) =
     * GCD(m, n mod m)
     */
    @Test
    public void testReduceToGCD_2_3() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(3);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     * Test ReduceToGCD_with input 17 and 18. reason: condition2 - GCD(n, m) =
     * GCD(m, n mod m)
     */
    @Test
    public void testReduceToGCD_17_18() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(18);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    /**
     * Test IsEven with input 0. reason: lower boundary
     */
    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /**
     * Test IsEven with input 1. reason: routine
     */
    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /**
     * Test IsEven with input 3. reason: routine
     */
    @Test
    public void testIsEven_3() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber nExpected = new NaturalNumber2(3);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /**
     * Test IsEven with input 10. reason: routine
     */
    @Test
    public void testIsEven_10() {
        NaturalNumber n = new NaturalNumber2(10);
        NaturalNumber nExpected = new NaturalNumber2(10);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Tests of powerMod
     */

    /**
     * Test PowerMod with input 0, 0, and 2. reason: lower boundary (p = 0; m >
     * 1)
     */
    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /**
     * Test PowerMod with input 17, 18, and 19. reason: routine (p is even)
     */
    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /**
     * Test PowerMod with input 2, 3, and 5. reason: boundary (p is odd)
     */
    @Test
    public void testPowerMod_2_3_5() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber p = new NaturalNumber2(3);
        NaturalNumber pExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(5);
        NaturalNumber mExpected = new NaturalNumber2(5);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /**
     * Test PowerMod with input 12, 6, and 10. routine: p is even
     */
    @Test
    public void testPowerMod_12_6_10() {
        NaturalNumber n = new NaturalNumber2(12);
        NaturalNumber nExpected = new NaturalNumber2(4);
        NaturalNumber p = new NaturalNumber2(6);
        NaturalNumber pExpected = new NaturalNumber2(6);
        NaturalNumber m = new NaturalNumber2(10);
        NaturalNumber mExpected = new NaturalNumber2(10);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /**
     * Test PowerMod with input 24, 30, and 2. routine: p is even
     */
    @Test
    public void testPowerMod_24_30_2() {
        NaturalNumber n = new NaturalNumber2(24);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber p = new NaturalNumber2(30);
        NaturalNumber pExpected = new NaturalNumber2(30);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isWitnessToCompositeness
     *
     */
    /**
     * Test isWitnessToCompositeness with input 5, 12. when 1 < w < n – 1, w2
     * mod n = 1.
     */
    @Test
    public void isWitnessToCompositeness_5_12() {
        NaturalNumber w = new NaturalNumber2(5);
        NaturalNumber wExpected = new NaturalNumber2(5);
        NaturalNumber n = new NaturalNumber2(12);
        NaturalNumber nExpected = new NaturalNumber2(12);
        boolean com = CryptoUtilities.isWitnessToCompositeness(w, n);

        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(true, com);

    }

    /**
     * Test isWitnessToCompositeness with input 4, 8. when 1 < w < n – 1, w2 mod
     * n /= 1.
     */
    @Test
    public void isWitnessToCompositeness_4_8() {
        NaturalNumber w = new NaturalNumber2(4);
        NaturalNumber wExpected = new NaturalNumber2(4);
        NaturalNumber n = new NaturalNumber2(8);
        NaturalNumber nExpected = new NaturalNumber2(8);
        boolean com = CryptoUtilities.isWitnessToCompositeness(w, n);

        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(true, com);

    }

    /**
     * Test isWitnessToCompositeness with input 3, 7. when 0 < w < n, then w^n –
     * 1 mod n = 1.
     */
    @Test
    public void isWitnessToCompositeness_3_7() {
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber wExpected = new NaturalNumber2(3);
        NaturalNumber n = new NaturalNumber2(7);
        NaturalNumber nExpected = new NaturalNumber2(7);
        boolean com = CryptoUtilities.isWitnessToCompositeness(w, n);

        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(false, com);

    }

    /**
     * Test isWitnessToCompositeness with input 5, 7. when 0 < w < n, then w^n –
     * 1 mod n = 1.
     */
    @Test
    public void isWitnessToCompositeness_5_7() {
        NaturalNumber w = new NaturalNumber2(5);
        NaturalNumber wExpected = new NaturalNumber2(5);
        NaturalNumber n = new NaturalNumber2(7);
        NaturalNumber nExpected = new NaturalNumber2(7);
        boolean com = CryptoUtilities.isWitnessToCompositeness(w, n);

        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(false, com);

    }

    /**
     * Test isWitnessToCompositeness with input 2, 4. when 0 < w < n, then w^n –
     * 1 mod n = 1.
     */
    @Test
    public void isWitnessToCompositeness_2_4() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber wExpected = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(4);
        boolean com = CryptoUtilities.isWitnessToCompositeness(w, n);

        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(true, com);

    }

    /**
     * Test isWitnessToCompositeness with input 12, 36.when 0 < w < n, then w^n
     * – 1 mod n /= 1.
     */
    @Test
    public void isWitnessToCompositeness_12_36() {
        NaturalNumber w = new NaturalNumber2(12);
        NaturalNumber wExpected = new NaturalNumber2(12);
        NaturalNumber n = new NaturalNumber2(36);
        NaturalNumber nExpected = new NaturalNumber2(36);
        boolean com = CryptoUtilities.isWitnessToCompositeness(w, n);

        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(true, com);

    }

    /**
     * Test isWitnessToCompositeness with input 14, 35.
     */
    @Test
    public void isWitnessToCompositeness_14_35() {
        NaturalNumber w = new NaturalNumber2(14);
        NaturalNumber wExpected = new NaturalNumber2(14);
        NaturalNumber n = new NaturalNumber2(35);
        NaturalNumber nExpected = new NaturalNumber2(35);
        boolean com = CryptoUtilities.isWitnessToCompositeness(w, n);

        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(true, com);

    }

    /*
     * Tests of isPrime1
     *
     */
    /**
     * Test isPrime1 with input 2. reason: boundary
     */
    @Test
    public void isPrime1_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean prime = CryptoUtilities.isPrime1(n);

        assertEquals(nExpected, n);
        assertEquals(true, prime);

    }

    /**
     * Test isPrime1 with input 3. reason: condition1
     */
    @Test
    public void isPrime1_3() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber nExpected = new NaturalNumber2(3);
        boolean prime = CryptoUtilities.isPrime1(n);

        assertEquals(nExpected, n);
        assertEquals(true, prime);

    }

    /**
     * Test isPrime1 with input 28. reason: even
     */
    @Test
    public void isPrime1_28() {
        NaturalNumber n = new NaturalNumber2(28);
        NaturalNumber nExpected = new NaturalNumber2(28);
        boolean prime = CryptoUtilities.isPrime1(n);

        assertEquals(nExpected, n);
        assertEquals(false, prime);

    }

    /**
     * Test isPrime1 with input 95. reason: > 5 & odd
     */
    @Test
    public void isPrime1_95() {
        NaturalNumber n = new NaturalNumber2(95);
        NaturalNumber nExpected = new NaturalNumber2(95);
        boolean prime = CryptoUtilities.isPrime1(n);

        assertEquals(nExpected, n);
        assertEquals(false, prime);

    }

    /**
     * Test isPrime1 with input 89. reason: > 5 & odd
     */
    @Test
    public void isPrime1_89() {
        NaturalNumber n = new NaturalNumber2(89);
        NaturalNumber nExpected = new NaturalNumber2(89);
        boolean prime = CryptoUtilities.isPrime1(n);

        assertEquals(nExpected, n);
        assertEquals(true, prime);

    }

    /**
     * Test isPrime1 with input 73. reason: > 5 & odd
     */
    @Test
    public void isPrime1_73() {
        NaturalNumber n = new NaturalNumber2(73);
        NaturalNumber nExpected = new NaturalNumber2(73);
        boolean prime = CryptoUtilities.isPrime1(n);

        assertEquals(nExpected, n);
        assertEquals(true, prime);

    }

    /*
     * Tests of isPrime2
     *
     */
    /**
     * Test isPrime2 with input 2. reason: boundary
     */

    @Test
    public void isPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean prime = CryptoUtilities.isPrime2(n);

        assertEquals(nExpected, n);
        assertEquals(true, prime);

    }

    /**
     * Test isPrime2 with input 3. reason: condition1
     */
    @Test
    public void isPrime2_3() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber nExpected = new NaturalNumber2(3);
        boolean prime = CryptoUtilities.isPrime2(n);

        assertEquals(nExpected, n);
        assertEquals(true, prime);

    }

    /**
     * Test isPrime2 with input 28. reason: even
     */
    @Test
    public void isPrime2_28() {
        NaturalNumber n = new NaturalNumber2(28);
        NaturalNumber nExpected = new NaturalNumber2(28);
        boolean prime = CryptoUtilities.isPrime2(n);

        assertEquals(nExpected, n);
        assertEquals(false, prime);

    }

    /**
     * Test isPrime2 with input 95. reason: > 5 & odd
     */
    @Test
    public void isPrime2_95() {
        NaturalNumber n = new NaturalNumber2(95);
        NaturalNumber nExpected = new NaturalNumber2(95);
        boolean prime = CryptoUtilities.isPrime2(n);

        assertEquals(nExpected, n);
        assertEquals(false, prime);

    }

    /**
     * Test isPrime2 with input 89. reason: > 5 & odd
     */
    @Test
    public void isPrime2_89() {
        NaturalNumber n = new NaturalNumber2(89);
        NaturalNumber nExpected = new NaturalNumber2(89);
        boolean prime = CryptoUtilities.isPrime2(n);

        assertEquals(nExpected, n);
        assertEquals(true, prime);

    }

    /**
     * Test isPrime2 with input 73. reason: > 5 & odd
     */
    @Test
    public void isPrime2_73() {
        NaturalNumber n = new NaturalNumber2(73);
        NaturalNumber nExpected = new NaturalNumber2(73);
        boolean prime = CryptoUtilities.isPrime2(n);

        assertEquals(nExpected, n);
        assertEquals(true, prime);

    }

    /*
     * Tests of generateNextLikelyPrime
     *
     */
    /**
     * Test generateNextLikelyPrime with input 2. reason: boundary
     */
    @Test
    public void generateNextLikelyPrime_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        CryptoUtilities.generateNextLikelyPrime(n);

        assertEquals(nExpected, n);
    }

    /**
     * Test generateNextLikelyPrime with input 4. reason: routine
     */
    @Test
    public void generateNextLikelyPrime_4() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(5);
        CryptoUtilities.generateNextLikelyPrime(n);

        assertEquals(nExpected, n);
    }

    /**
     * Test generateNextLikelyPrime with input 6. reason: routine
     */
    @Test
    public void generateNextLikelyPrime_6() {
        NaturalNumber n = new NaturalNumber2(6);
        NaturalNumber nExpected = new NaturalNumber2(7);
        CryptoUtilities.generateNextLikelyPrime(n);

        assertEquals(nExpected, n);
    }

    /**
     * Test generateNextLikelyPrime with input 70. reason: routine
     */
    @Test
    public void generateNextLikelyPrime_70() {
        NaturalNumber n = new NaturalNumber2(70);
        NaturalNumber nExpected = new NaturalNumber2(71);
        CryptoUtilities.generateNextLikelyPrime(n);

        assertEquals(nExpected, n);
    }

    /**
     * Test generateNextLikelyPrime with input 82. reason: routine
     */
    @Test
    public void generateNextLikelyPrime_82() {
        NaturalNumber n = new NaturalNumber2(82);
        NaturalNumber nExpected = new NaturalNumber2(83);
        CryptoUtilities.generateNextLikelyPrime(n);

        assertEquals(nExpected, n);
    }

    /**
     * Test generateNextLikelyPrime with input 84. reason: routine
     */
    @Test
    public void generateNextLikelyPrime_84() {
        NaturalNumber n = new NaturalNumber2(84);
        NaturalNumber nExpected = new NaturalNumber2(89);
        CryptoUtilities.generateNextLikelyPrime(n);

        assertEquals(nExpected, n);
    }

    /**
     * Test generateNextLikelyPrime with input 90. reason: challenge (the
     * parameter is prime)
     */
    @Test
    public void generateNextLikelyPrime_89() {
        NaturalNumber n = new NaturalNumber2(89);
        NaturalNumber nExpected = new NaturalNumber2(89);
        CryptoUtilities.generateNextLikelyPrime(n);

        assertEquals(nExpected, n);
    }

    /**
     * Test generateNextLikelyPrime with input 97. reason: challenge (the
     * parameter is prime)
     */
    @Test
    public void generateNextLikelyPrime_97() {
        NaturalNumber n = new NaturalNumber2(97);
        NaturalNumber nExpected = new NaturalNumber2(97);
        CryptoUtilities.generateNextLikelyPrime(n);

        assertEquals(nExpected, n);
    }

}
