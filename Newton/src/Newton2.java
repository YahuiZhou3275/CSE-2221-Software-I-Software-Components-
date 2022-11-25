import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Newton2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        double r = x;
        final double error = 0.0001;

        /*
         * Avoid the program executing a division by 0
         */
        if (x != 0.0) {
            while ((Math.abs(r * r - x) / x) >= error * error) {
                r = (r + x / r) / 2;
            }
        }

        return r;
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
         * Initial the variable, answer, so it can enter the loop.
         */
        String answer = "y";

        /*
         * The program will process until the user does not want to.
         */
        while (answer.equals("y")) {

            /*
             * Prompt the user for the number.
             */
            out.print("Enter a number:");
            double initial = in.nextDouble();

            /*
             * Use sqrt method to compute roots by Newton Iteration.
             */
            double outcome = sqrt(initial);

            /*
             * Report the answer and ask the user whether he or she want to
             * calculate another square root or not.
             */
            out.println("The foot of this number by using Newton Iteration is "
                    + outcome);
            out.println();
            out.print("Do you want to calculate another square root?");
            out.println(" (y for yes, others for no)");

            /*
             * Update the user's input.
             */
            answer = in.nextLine();
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
