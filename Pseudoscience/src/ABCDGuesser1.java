import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * First，the user is prompted to enter a constant miu to be approximated. Then,
 * the user need to input four positive real value not equal to 1. The program
 * will does a calculation and find the combination of exponents that minimizes
 * the error of the approximation of miu and then print the exponents, best
 * approximation, and corresponding relative error.
 *
 * @author Yahui Zhou
 *
 */
public final class ABCDGuesser1 {
    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser1() {
    }

    /**
     * Repeatedly asks the user for a number. Returns the number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a number
     */
    private static double getANumber(SimpleReader in, SimpleWriter out) {
        String input = in.nextLine();
        boolean numberOrNot = FormatChecker.canParseDouble(input);

        while (!numberOrNot) {
            out.println("The input should be a number!");
            out.print("Please input a number: ");
            input = in.nextLine();
            numberOrNot = FormatChecker.canParseDouble(input);
        }

        double num = Double.parseDouble(input);

        return num;
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        out.print(
                "Please select any positive real-valued constant to be approximated:");
        double miu = getANumber(in, out);

        while (miu <= 0) {
            out.print(
                    "Input is invalid. Please enter a positive real-valued number:");
            miu = getANumber(in, out);
        }

        return miu;

    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {

        out.print("Please enter a positive real number:");
        double num = getANumber(in, out);

        while (num <= 0 || num == 1) {
            out.println("Your input is not valid");
            out.print("Please enter a positive real number not equal to 1.0:");
            num = getANumber(in, out);
        }

        return num;

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
         * Set up the exponents.
         */
        final double[] exponents = { -5, -4, -3, -2, -1, 1.0 / 2, -1.0 / 3,
                -1.0 / 4, 0, 1.0 / 4, 1.0 / 3, 1.0 / 2, 1, 2, 3, 4, 5 };

        /*
         * Ask for a positive real-valued number for miu.
         */
        double miu = getPositiveDouble(in, out);

        /*
         * Ask for four positive real-valued personal numbers for a, b, c, d.
         */

        out.println("Select four personal numbers.");
        double m = getPositiveDoubleNotOne(in, out);
        double n = getPositiveDoubleNotOne(in, out);
        double p = getPositiveDoubleNotOne(in, out);
        double q = getPositiveDoubleNotOne(in, out);

        double error;
        double leastError = 1;
        double product;
        double bestProduct = 1;
        int a = 0;
        double first, second, third, fourth;
        double exp1 = exponents[0], exp2 = exponents[0], exp3 = exponents[0],
                exp4 = exponents[0];
        final int percent = 100;

        /*
         * Use the exponents one by one and find the best approximation with
         * least relative error. Print the best approximation, the exponents,
         * and the relative error.
         */
        while (a < exponents.length) {
            int b = 0;
            while (b < exponents.length) {
                int c = 0;
                while (c < exponents.length) {
                    int d = 0;
                    while (d < exponents.length) {
                        first = Math.pow(m, exponents[a]);
                        second = Math.pow(n, exponents[b]);
                        third = Math.pow(p, exponents[c]);
                        fourth = Math.pow(q, exponents[d]);
                        product = first * second * third * fourth;
                        error = (Math.abs(product - miu) / miu) * percent;

                        /*
                         * Update the least_error whenever the error is less
                         * than the least_error. Update the corresponding
                         * exponents.
                         */
                        if (error < leastError) {
                            leastError = error;
                            bestProduct = product;
                            exp1 = exponents[a];
                            exp2 = exponents[b];
                            exp3 = exponents[c];
                            exp4 = exponents[d];
                        }

                        d++;
                    }
                    c++;
                }
                b++;
            }
            a++;
        }

        /*
         * Print the best approximation, the exponents, and the relative error.
         */
        out.println();
        out.println("The positive real-valued constant should be approximated: "
                + miu);
        out.println("The exponents are: " + exp1 + " " + exp2 + " " + exp3 + " "
                + exp4);
        out.println("The best approximation of miu: " + bestProduct);
        out.print("The corresponding relative error is: ");
        out.print(leastError, 2, false);
        out.println("%");

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
