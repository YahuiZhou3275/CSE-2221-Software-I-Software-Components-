import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Yahui Zhou
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        int result = 0;
        String numStr;

        // when exp is an operator, evaluate the expressions from
        // each subtree through the recursion method.
        // then do the last calculation.
        if (!exp.hasAttribute("value")) {
            // divide the problem into a smaller problem
            // first, calculate the value from the left of the operator,
            // which means calculating the value from the first subtree.
            XMLTree child1 = exp.child(0);
            int num1 = evaluate(child1);

            // second, calculate the value from the right of the operator.
            // which means calculating the value from the second subtree.
            XMLTree child2 = exp.child(1);
            int num2 = evaluate(child2);

            // do the calculation according to the operators
            // when doing the division, check whether the divisor is zero.
            // if it is, prints the given error message to the console and
            // terminates the application.
            String operator = exp.label();
            if (operator.equals("plus")) {
                result = num1 + num2;
            } else if (operator.equals("minus")) {
                result = num1 - num2;
            } else if (operator.equals("times")) {
                result = num1 * num2;
            } else {
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    Reporter.fatalErrorToConsole("Error - divide by zero!");
                }
            }
        } else {
            // if the root has no subtrees, then it must be an operand,
            // and that operand is the value of the expression represented by
            // the tree.
            numStr = exp.attributeValue("value");
            result = Integer.parseInt(numStr);
        }

        return result;

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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
