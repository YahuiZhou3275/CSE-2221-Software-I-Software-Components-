import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Asked the user for a file including the words and their definitions. Then
 * create a top-level index page which merely lists each term in alphabetic
 * order in the glossary, and separate pages for each of the terms with their
 * definitions. Clicking on a term in the index shall take the user to the page
 * for that term and its associated definition. Moreover, clicking on any term
 * in the glossary that happens to appear in a definition shall take you to the
 * page for that term and its associated definition.
 *
 * @author Yahui Zhou
 *
 */
public final class Glossary {
    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    /**
     * Read {@code input} and restore each word and its definition from the
     * input file {@code inputFile}.Finally return words with definitions in
     * pairs.
     *
     * @param input
     *            source of strings, one per line
     * @return words and definitions in pairs read from {@code input}
     * @requires in.is_open
     * @ensures <pre>
     * in.is_open and
     * in.ext_name = #in.ext_name and
     * in.content = "" and
     * [storeWords contains words -> definitions from input]
     * </pre>
     */
    public static Map<String, String> storeWords(SimpleReader input) {
        assert input != null : "Violation of: input is not null";
        assert input.isOpen() : "Violation of: input.is_open";

        Map<String, String> wordWithDef = new Map1L<>();

        // read through the whole file
        while (!input.atEOS()) {
            // store the words
            String word = input.nextLine();
            // store the definition
            String definition = input.nextLine();
            // read the next line and store it in the definition, until it
            // reads an empty line
            String temp = input.nextLine();
            while (!input.atEOS() && !temp.equals("")) {
                definition = definition.concat(temp);
                temp = input.nextLine();
            }
            // add the word with its definition in pairs
            if (!wordWithDef.hasKey(word)) {
                wordWithDef.add(word, definition);
            }
        }

        return wordWithDef;

    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param charSet
     *            the {@code Set} to be replaced
     * @replaces charSet
     * @ensures charSet = entries(str)
     */
    public static void generateElements(String str, Set<Character> charSet) {
        assert str != null : "Violation of: str is not null";
        assert charSet != null : "Violation of: charSet is not null";

        charSet.clear();
        // store each character in the str in the set
        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            // store the character when the set does not include it
            if (!charSet.contains(temp)) {
                charSet.add(temp);
            }
        }
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        char temp = text.charAt(position);
        int length = 0;
        int i = 0;
        String result = "";

        // if the first character catched is a separator,
        // then check whether the following characters are separators
        // until it catches a word or reach the end of the text.
        if (separators.contains(temp)) {
            while ((separators.contains(temp))
                    && (position + i) < text.length()) {
                length += 1;
                i++;
                if (position + i < text.length()) {
                    temp = text.charAt(position + i);
                }
            }
        } else {
            // if the first character catched is not a separator,
            // then it is a word
            // then check whether the following characters are separators
            // until it catches a separator or reach the end of the text
            while (!separators.contains(temp)
                    && (position + i) < text.length()) {
                length += 1;
                i++;
                if (position + i < text.length()) {
                    temp = text.charAt(position + i);
                }
            }
        }

        result = text.substring(position, position + length);

        return result;
    }

    /**
     * Ask the user for a file including the words and their definitions. Then
     * create an index page with words in alphabetic order and separate pages
     * for each of the terms with their definitions. Clicking on a term in the
     * index shall take you to the page for that term and its associated
     * definition. Moreover, clicking on any term in the glossary that happens
     * to appear in a definition shall take you to the page for that term and
     * its associated definition.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // ask for the input file
        out.print("Please enter the name of input file: ");
        String inputFile = in.nextLine();
        SimpleReader inFile = new SimpleReader1L(inputFile);

        // ask for the output file
        out.print("Please enter the name of the output folder: ");
        String outputFolder = in.nextLine();

        // set up output file
        String outputFile = outputFolder + "/index.html";
        SimpleWriter outFile = new SimpleWriter1L(outputFile);

        // set up index page
        outFile.println("<html> <head> <title>Glossary</title></head><body>");
        outFile.println("<h1>Glossary</h1>");
        outFile.println("<hr>");
        outFile.println("<h2>Index</h2>");

        // read and store the words and definitions in the file
        Map<String, String> wordWithDef = storeWords(inFile);
        Queue<String> wordsInOrder = new Queue1L<>();

        // store the words in Queue.
        Map<String, String> tempMap = wordWithDef.newInstance();
        tempMap.transferFrom(wordWithDef);
        while (tempMap.size() > 0) {
            Map.Pair<String, String> x = tempMap.removeAny();
            String word = x.key();
            wordsInOrder.enqueue(word);
            wordWithDef.add(x.key(), x.value());
        }

        // sort the words in alphabetical order
        Comparator<String> order = new StringLT();
        wordsInOrder.sort(order);

        // store some separators and store them in a set
        outFile.println("<ul>");
        final String separatorStr = ":,.?/;:'[]{}|`~ \"\\@#$%^&*()_+-=\t";
        Set<Character> separatorSet = new Set1L<>();
        generateElements(separatorStr, separatorSet);

        // read the whole definition, catch each word or separators within it.
        // if it catches separators, then print it out.
        // Otherwise, it catches words.
        // if the words is in the word list, then link it to the page for that word
        // Otherwise, print the word out.
        Queue<String> tempQue = wordsInOrder.newInstance();
        tempQue.transferFrom(wordsInOrder);
        while (tempQue.length() > 0) {

            // catch the word which is in alphabetical order
            String catchWord = tempQue.dequeue();
            if (wordWithDef.hasKey(catchWord)) {
                String definition = wordWithDef.value(catchWord);
                String eachWordFile = outputFolder + "/" + catchWord + ".html";
                SimpleWriter wordFile = new SimpleWriter1L(eachWordFile);

                // set the word in the list in the index page
                outFile.println("<li> <a href=\"" + catchWord + ".html" + "\">"
                        + catchWord + "</a></li>");

                // create and set the page for each word
                wordFile.println("<html> <head> <title>" + catchWord
                        + "</title></head>");
                wordFile.print("<h1 style=\"color: Red;\"><b><i>" + catchWord
                        + "</h1></i></b></head> <body>");
                wordFile.println("<p style=\"margin-left: 40px\">");

                // print out the definition
                int position = 0;
                while (position < definition.length()) {
                    // catch next word or separators in the definition
                    String token = nextWordOrSeparator(definition, position,
                            separatorSet);
                    // if it catches separators, then print it out
                    if (separatorSet.contains(token.charAt(0))) {
                        wordFile.print(token);
                    } else {
                        // if it catches words within the word list, then link
                        // it to the word's page
                        // otherwise, print out the word
                        if (wordWithDef.hasKey(token)) {
                            wordFile.print("<a href = \"" + token + ".html\">"
                                    + token + "</a>");
                        } else {
                            wordFile.print(token);
                        }
                    }

                    position += token.length();
                }
                // clicking the index to take the user back to the index page
                wordFile.println("</p>");
                wordFile.println("<hr>");
                wordFile.println(
                        "<p>Return to <a href = \"index.html\">index</a>.</p>");
                // close the output streams
                wordFile.close();
            }

        }

        outFile.println("</ul>");
        outFile.println("</body></html>");
        /*
         * Close input and output streams
         */
        outFile.close();
        in.close();
        out.close();
    }

}
