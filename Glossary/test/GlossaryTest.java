import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 * @author Yahui Zhou
 *
 */
public class GlossaryTest {
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    /*
     * Tests of storeWords
     */
    /**
     * Test storeWords with inputFile1_words with one-lined definitions
     */
    @Test
    public void storeWords_inputFile1() {
        String file = "data/inputFile1.txt";
        SimpleReader in = new SimpleReader1L(file);

        Map<String, String> pairExpected = new Map1L<>();
        pairExpected.add("meaning",
                "something that one wishes to convey, especially by language ");
        pairExpected.add("term", "a word whose definition is in a glossary ");
        pairExpected.add("word",
                "a string of characters in a language, which has at least one character ");

        Map<String, String> pair = Glossary.storeWords(in);

        assertEquals(pairExpected, pair);
    }

    /**
     * Test storeWords with inputFile2_words with one-lined or two-lined
     * definitions
     */
    @Test
    public void storeWords_inputFile2() {
        String file = "data/inputFile2.txt";
        SimpleReader in = new SimpleReader1L(file);

        Map<String, String> pairExpected = new Map1L<>();
        pairExpected.add("OSU", "the Ohio State University");
        pairExpected.add("term", "a word whose definition is in a glossary ");
        pairExpected.add("word",
                "a string of characters in a language, which has at least one character ");

        Map<String, String> pair = Glossary.storeWords(in);

        assertEquals(pairExpected, pair);
    }

    /**
     * Test storeWords with inputFile3_words with one-lined or more-lined
     * definitions
     */
    @Test
    public void storeWords_inputFile3() {
        String file = "data/inputFile3.txt";
        SimpleReader in = new SimpleReader1L(file);

        Map<String, String> pairExpected = new Map1L<>();
        pairExpected.add("OSU", "the Ohio State University");
        pairExpected.add("term", "a word whose definition is in a glossary ");
        pairExpected.add("word",
                "a string of characters in a language, which has at least one character ");

        Map<String, String> pair = Glossary.storeWords(in);

        assertEquals(pairExpected, pair);
    }

    /**
     * Test storeWords with inputFile4_words with multiple-lined definitions
     */
    @Test
    public void storeWords_inputFile4() {
        String file = "data/inputFile4.txt";
        SimpleReader in = new SimpleReader1L(file);

        Map<String, String> pairExpected = new Map1L<>();
        pairExpected.add("OSU", "the Ohio State University");
        pairExpected.add("term", "a word whose definition is in a glossary ");
        pairExpected.add("word",
                "a string of characters in a language, which has at least one character ");

        Map<String, String> pair = Glossary.storeWords(in);

        assertEquals(pairExpected, pair);
    }

    /*
     * Tests of generateElements
     */
    /**
     * Test generateElements_with string ",. "
     */
    @Test
    public void generateElements_str1_set1() {
        String str = ",. ";
        String strExpected = ",. ";

        Set<Character> charSet = new Set1L<>();
        Glossary.generateElements(str, charSet);

        Set<Character> charSetExpected = new Set1L<>();
        charSetExpected.add(',');
        charSetExpected.add('.');
        charSetExpected.add(' ');

        assertEquals(strExpected, str);
        assertEquals(charSetExpected, charSet);
    }

    /**
     * Test generateElements_with string ",./// "
     */
    @Test
    public void generateElements_str2_set2() {
        String str = ",.///";
        String strExpected = ",.///";

        Set<Character> charSet = new Set1L<>();
        Glossary.generateElements(str, charSet);

        Set<Character> charSetExpected = new Set1L<>();
        charSetExpected.add(',');
        charSetExpected.add('.');
        charSetExpected.add('/');

        assertEquals(strExpected, str);
        assertEquals(charSetExpected, charSet);
    }

    /**
     * Test generateElements_with string ",.[] "
     */
    @Test
    public void generateElements_str3_set3() {
        String str = ",.[]";
        String strExpected = ",.[]";

        Set<Character> charSet = new Set1L<>();
        Glossary.generateElements(str, charSet);

        Set<Character> charSetExpected = new Set1L<>();
        charSetExpected.add(',');
        charSetExpected.add('.');
        charSetExpected.add('[');
        charSetExpected.add(']');

        assertEquals(strExpected, str);
        assertEquals(charSetExpected, charSet);
    }

    /**
     * Test generateElements_with string "-+?!!-+"
     */
    @Test
    public void generateElements_str4_set4() {
        String str = "-+?!!-+";
        String strExpected = "-+?!!-+";

        Set<Character> charSet = new Set1L<>();
        Glossary.generateElements(str, charSet);

        Set<Character> charSetExpected = new Set1L<>();
        charSetExpected.add('-');
        charSetExpected.add('+');
        charSetExpected.add('?');
        charSetExpected.add('!');

        assertEquals(strExpected, str);
        assertEquals(charSetExpected, charSet);
    }

    /*
     * Tests of nextWordOrSeparator
     */
    /**
     * Test nextWordOrSeparator_"abd def,,,,,"_pos < str.length (pos=2)
     */
    @Test
    public void nextWordOrSeparator_str1_pos1_separators() {
        String str = "abd def,,,,, ";
        String strExpected = "abd def,,,,, ";

        int pos = 2;
        int posExpected = 2;

        Set<Character> separators = new Set1L<>();
        separators.add(',');
        separators.add('.');
        separators.add(' ');

        Set<Character> separatorsExpected = new Set1L<>();
        separatorsExpected.add(',');
        separatorsExpected.add('.');
        separatorsExpected.add(' ');

        String text = Glossary.nextWordOrSeparator(str, pos, separators);
        String textExpected = "d";

        assertEquals(strExpected, str);
        assertEquals(posExpected, pos);
        assertEquals(separatorsExpected, separators);
        assertEquals(textExpected, text);

    }

    /**
     * Test nextWordOrSeparator_"abd def,,,,,"_pos < str.length (pos=0)
     */
    @Test
    public void nextWordOrSeparator_str2_pos2_separators() {
        String str = "abd def,,,,, ";
        String strExpected = "abd def,,,,, ";

        int pos = 0;
        int posExpected = 0;

        Set<Character> separators = new Set1L<>();
        separators.add(',');
        separators.add('.');
        separators.add(' ');

        Set<Character> separatorsExpected = new Set1L<>();
        separatorsExpected.add(',');
        separatorsExpected.add('.');
        separatorsExpected.add(' ');

        String text = Glossary.nextWordOrSeparator(str, pos, separators);
        String textExpected = "abd";

        assertEquals(strExpected, str);
        assertEquals(posExpected, pos);
        assertEquals(separatorsExpected, separators);
        assertEquals(textExpected, text);
    }

    /**
     * Test nextWordOrSeparator_"abd,.,.efg"_pos < str.length (pos=3)
     */
    @Test
    public void nextWordOrSeparator_str3_pos3_separators() {
        String str = "abd,.,.efg";
        String strExpected = "abd,.,.efg";

        int pos = 3;
        int posExpected = 3;

        Set<Character> separators = new Set1L<>();
        separators.add(',');
        separators.add('.');
        separators.add(' ');

        Set<Character> separatorsExpected = new Set1L<>();
        separatorsExpected.add(',');
        separatorsExpected.add('.');
        separatorsExpected.add(' ');

        String text = Glossary.nextWordOrSeparator(str, pos, separators);
        String textExpected = ",.,.";

        assertEquals(strExpected, str);
        assertEquals(posExpected, pos);
        assertEquals(separatorsExpected, separators);
        assertEquals(textExpected, text);
    }

    /**
     * Test nextWordOrSeparator_"abd,.,.efg"_pos < str.length (pos=4)
     */
    @Test
    public void nextWordOrSeparator_str4_pos4_separators() {
        String str = "abd,.,.efg";
        String strExpected = "abd,.,.efg";

        int pos = 4;
        int posExpected = 4;

        Set<Character> separators = new Set1L<>();
        separators.add(',');
        separators.add('.');
        separators.add(' ');

        Set<Character> separatorsExpected = new Set1L<>();
        separatorsExpected.add(',');
        separatorsExpected.add('.');
        separatorsExpected.add(' ');

        String text = Glossary.nextWordOrSeparator(str, pos, separators);
        String textExpected = ".,.";

        assertEquals(strExpected, str);
        assertEquals(posExpected, pos);
        assertEquals(separatorsExpected, separators);
        assertEquals(textExpected, text);
    }

    /**
     * Test nextWordOrSeparator_"abd,.efg"_pos < str.length (pos=6)
     */
    @Test
    public void nextWordOrSeparator_str5_pos5_separators() {
        String str = "abd,.efg";
        String strExpected = "abd,.efg";

        int pos = 6;
        int posExpected = 6;

        Set<Character> separators = new Set1L<>();
        separators.add(',');
        separators.add('.');
        separators.add(' ');

        Set<Character> separatorsExpected = new Set1L<>();
        separatorsExpected.add(',');
        separatorsExpected.add('.');
        separatorsExpected.add(' ');

        String text = Glossary.nextWordOrSeparator(str, pos, separators);
        String textExpected = "fg";

        assertEquals(strExpected, str);
        assertEquals(posExpected, pos);
        assertEquals(separatorsExpected, separators);
        assertEquals(textExpected, text);
    }

    /**
     * Test nextWordOrSeparator_"abd def"_pos = str.length (pos=6)
     */
    @Test
    public void nextWordOrSeparator_str6_pos6_separators() {
        String str = "abd def";
        String strExpected = "abd def";

        int pos = 6;
        int posExpected = 6;

        Set<Character> separators = new Set1L<>();
        separators.add(',');
        separators.add('.');
        separators.add(' ');

        Set<Character> separatorsExpected = new Set1L<>();
        separatorsExpected.add(',');
        separatorsExpected.add('.');
        separatorsExpected.add(' ');

        String text = Glossary.nextWordOrSeparator(str, pos, separators);
        String textExpected = "f";

        assertEquals(strExpected, str);
        assertEquals(posExpected, pos);
        assertEquals(separatorsExpected, separators);
        assertEquals(textExpected, text);
    }

}
