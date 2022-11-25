import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

import components.queue.Queue;
import components.queue.Queue2;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    /*
     * Tests of combination
     */
    /**
     * Test combination input "a" and 0 "d".
     */
    @Test
    public void testCombination_a_d_0() {
        String a = "a";
        String aExpected = "a";
        String b = "d";
        String bExpected = "d";
        String comb = StringReassembly.combination(a, b, 0);
        String combExpected = "ad";
        assertEquals(aExpected, a);
        assertEquals(bExpected, b);
        assertEquals(combExpected, comb);
    }

    /**
     * Test combination input "abc" and "def" with 0 overlap.
     */
    @Test
    public void testCombination_abc_def_0() {
        String a = "abc";
        String aExpected = "abc";
        String b = "def";
        String bExpected = "def";
        String comb = StringReassembly.combination(a, b, 0);
        String combExpected = "abcdef";
        assertEquals(aExpected, a);
        assertEquals(bExpected, b);
        assertEquals(combExpected, comb);
    }

    /**
     * Test combination input "abcdef" and "qwedef" with 0 overlap.
     */
    @Test
    public void testCombination_abcdef_qwedef_0() {
        String a = "abcdef";
        String aExpected = "abcdef";
        String b = "qwedef";
        String bExpected = "qwedef";
        String comb = StringReassembly.combination(a, b, 0);
        String combExpected = "abcdefqwedef";
        assertEquals(aExpected, a);
        assertEquals(bExpected, b);
        assertEquals(combExpected, comb);
    }

    /**
     * Test combination input "123456" and "654321" with 0 overlap.
     */
    @Test
    public void testCombination_123456_654321_0() {
        String a = "123456";
        String aExpected = "123456";
        String b = "654321";
        String bExpected = "654321";
        String comb = StringReassembly.combination(a, b, 0);
        String combExpected = "123456654321";
        assertEquals(aExpected, a);
        assertEquals(bExpected, b);
        assertEquals(combExpected, comb);
    }

    /**
     * Test combination input "abcdef" and "defghi" with 3 overlap.
     */
    @Test
    public void testCombination_abcdef_defghi_3() {
        String a = "abcdef";
        String aExpected = "abcdef";
        String b = "defghi";
        String bExpected = "defghi";
        String comb = StringReassembly.combination(a, b, 3);
        String combExpected = "abcdefghi";
        assertEquals(aExpected, a);
        assertEquals(bExpected, b);
        assertEquals(combExpected, comb);
    }

    /**
     * Test combination input "abc" and "cdef" with 1 overlap.
     */
    @Test
    public void testCombination_abc_cdef_1() {
        String a = "abc";
        String aExpected = "abc";
        String b = "cdef";
        String bExpected = "cdef";
        String comb = StringReassembly.combination(a, b, 1);
        String combExpected = "abcdef";
        assertEquals(aExpected, a);
        assertEquals(bExpected, b);
        assertEquals(combExpected, comb);
    }

    /**
     * Test combination input "a" and "a" with 1 overlap.
     */
    @Test
    public void testCombination_a_a_1() {
        String a = "a";
        String aExpected = "a";
        String b = "a";
        String bExpected = "a";
        String comb = StringReassembly.combination(a, b, 1);
        String combExpected = "a";
        assertEquals(aExpected, a);
        assertEquals(bExpected, b);
        assertEquals(combExpected, comb);
    }

    /**
     * Test combination input "123456" and "456" with 3 overlap.
     */
    @Test
    public void testCombination_123456_456_3() {
        String a = "123456";
        String aExpected = "123456";
        String b = "456";
        String bExpected = "456";
        String comb = StringReassembly.combination(a, b, 3);
        String combExpected = "123456";
        assertEquals(aExpected, a);
        assertEquals(bExpected, b);
        assertEquals(combExpected, comb);
    }

    /**
     * Test combination input "123" and "123456" with 3 overlap.
     */
    @Test
    public void testCombination_123_123456_3() {
        String a = "123";
        String aExpected = "123";
        String b = "123456";
        String bExpected = "123456";
        String comb = StringReassembly.combination(a, b, 3);
        String combExpected = "123456";
        assertEquals(aExpected, a);
        assertEquals(bExpected, b);
        assertEquals(combExpected, comb);
    }

    /*
     * Tests of addToSetAvoidingSubstrings
     */
    /**
     * Test addToSetAvoidingSubstrings input {"abc", "xyz"} and "abcd".
     */
    @Test
    public void addToSetAvoidingSubstrings_Set1_str1() {
        String str = "abcd";
        String strExpected = "abcd";

        Set<String> set = new Set1L<>();
        set.add("abc");
        set.add("xyz");
        StringReassembly.addToSetAvoidingSubstrings(set, str);
        Set<String> setExpected = new Set1L<>();
        setExpected.add("abcd");
        setExpected.add("xyz");

        assertEquals(strExpected, str);
        assertEquals(setExpected, set);
    }

    /**
     * Test addToSetAvoidingSubstrings input {"abc", "123"} and "abc".
     */
    @Test
    public void addToSetAvoidingSubstrings_Set2_str2() {
        String str = "abc";
        String strExpected = "abc";

        Set<String> set = new Set1L<>();
        set.add("abc");
        set.add("123");
        StringReassembly.addToSetAvoidingSubstrings(set, str);
        Set<String> setExpected = new Set1L<>();
        setExpected.add("abc");
        setExpected.add("123");

        assertEquals(strExpected, str);
        assertEquals(setExpected, set);
    }

    /**
     * Test addToSetAvoidingSubstrings input {"abc", "def"} and "123".
     */
    @Test
    public void addToSetAvoidingSubstrings_Set3_str3() {
        String str = "123";
        String strExpected = "123";

        Set<String> set = new Set1L<>();
        set.add("abc");
        set.add("def");
        StringReassembly.addToSetAvoidingSubstrings(set, str);
        Set<String> setExpected = new Set1L<>();
        setExpected.add("abc");
        setExpected.add("def");
        setExpected.add("123");

        assertEquals(strExpected, str);
        assertEquals(setExpected, set);
    }

    /**
     * Test addToSetAvoidingSubstrings input {"abc", "def"} and "ab".
     */
    @Test
    public void addToSetAvoidingSubstrings_Set4_str4() {
        String str = "ab";
        String strExpected = "ab";

        Set<String> set = new Set1L<>();
        set.add("abc");
        set.add("def");
        StringReassembly.addToSetAvoidingSubstrings(set, str);
        Set<String> setExpected = new Set1L<>();
        setExpected.add("abc");
        setExpected.add("def");

        assertEquals(strExpected, str);
        assertEquals(setExpected, set);
    }

    /*
     * Tests of linesFromInput
     */
    /**
     * Test linesFromInput input with {"OHIO", "STATE", "UNIVERSITY"}.
     */
    @Test
    public void linesFromInput_txt1() {
        String inputFileName = "data/inputTest_OSU";
        SimpleReader inFile = new SimpleReader1L(inputFileName);
        Set<String> set = StringReassembly.linesFromInput(inFile);

        Set<String> setExpected = new Set1L<>();
        setExpected.add("OHIO");
        setExpected.add("STATE");
        setExpected.add("UNIVERSITY");

        assertEquals(setExpected, set);
    }

    /**
     * Test linesFromInput input with Test linesFromInput input with {"GOOD",
     * "LUCK"}.
     */
    @Test
    public void linesFromInput_txt2() {
        String inputFileName = "data/inputTest_GOODLUCK";
        SimpleReader inFile = new SimpleReader1L(inputFileName);
        Set<String> set = StringReassembly.linesFromInput(inFile);

        Set<String> setExpected = new Set1L<>();
        setExpected.add("GOOD");
        setExpected.add("LUCK");

        assertEquals(setExpected, set);
    }

    /**
     * Test linesFromInput input with Test linesFromInput input with {"BEST",
     * "WISHES"}.
     */
    @Test
    public void linesFromInput_txt3() {
        String inputFileName = "data/inputTest_BESTWISHES";
        SimpleReader inFile = new SimpleReader1L(inputFileName);
        Set<String> set = StringReassembly.linesFromInput(inFile);

        Set<String> setExpected = new Set1L<>();
        setExpected.add("BEST");
        setExpected.add("WISHES");

        assertEquals(setExpected, set);
    }

    /**
     * Test linesFromInput input with Test linesFromInput input with {"Merry",
     * "Chrismas"}.
     */
    @Test
    public void linesFromInput_txt4() {
        String inputFileName = "data/inputTest_MerryChrismas";
        SimpleReader inFile = new SimpleReader1L(inputFileName);
        Set<String> set = StringReassembly.linesFromInput(inFile);

        Set<String> setExpected = new Set1L<>();
        setExpected.add("Merry");
        setExpected.add("Chrismas");

        assertEquals(setExpected, set);
    }

    /*
     * Tests of printWithLineSeparators
     */
    /**
     * Test printWithLineSeparators input "OHIO STATE UNIVERSITY".
     *
     * @throws FileNotFoundException
     */
    @Test
    public void printWithLineSeparators_1() throws FileNotFoundException {
        String file = "data/outputTest";
        SimpleWriter textWriter = new SimpleWriter1L(file);

        String text = "OHIO STATE UNIVERSITY";
        StringReassembly.printWithLineSeparators(text, textWriter);

        String inputFileName = file;
        SimpleReader inFile = new SimpleReader1L(inputFileName);

        Queue<String> result = new Queue2<>();
        while (!inFile.atEOS()) {
            String element = inFile.nextLine();
            result.enqueue(element);
        }

        Queue<String> resultExpected = new Queue2<>();
        resultExpected.enqueue("OHIO STATE UNIVERSITY");

        assertEquals(resultExpected, result);

        inFile.close();
    }

    /**
     * Test printWithLineSeparators input "OHIO~STATE~UNIVERSITY".
     *
     * @throws FileNotFoundException
     */
    @Test
    public void printWithLineSeparators_2() throws FileNotFoundException {
        String file = "data/outputTest";
        SimpleWriter textWriter = new SimpleWriter1L(file);

        String text = "OHIO~STATE~UNIVERSITY";
        StringReassembly.printWithLineSeparators(text, textWriter);

        String inputFileName = file;
        SimpleReader inFile = new SimpleReader1L(inputFileName);

        Queue<String> result = new Queue2<>();
        while (!inFile.atEOS()) {
            String element = inFile.nextLine();
            result.enqueue(element);
        }

        Queue<String> resultExpected = new Queue2<>();
        resultExpected.enqueue("OHIO");
        resultExpected.enqueue("STATE");
        resultExpected.enqueue("UNIVERSITY");

        assertEquals(resultExpected, result);

        inFile.close();
    }

    /**
     * Test printWithLineSeparators input "OHIO~~~STATE~UNIVERSITY".
     *
     * @throws FileNotFoundException
     */
    @Test
    public void printWithLineSeparators_3() throws FileNotFoundException {
        String file = "data/outputTest";
        SimpleWriter textWriter = new SimpleWriter1L(file);

        String text = "OHIO~~~STATE~UNIVERSITY";
        StringReassembly.printWithLineSeparators(text, textWriter);

        String inputFileName = file;
        SimpleReader inFile = new SimpleReader1L(inputFileName);

        Queue<String> result = new Queue2<>();
        while (!inFile.atEOS()) {
            String element = inFile.nextLine();
            result.enqueue(element);
        }

        Queue<String> resultExpected = new Queue2<>();
        resultExpected.enqueue("OHIO");
        resultExpected.enqueue("");
        resultExpected.enqueue("");
        resultExpected.enqueue("STATE");
        resultExpected.enqueue("UNIVERSITY");

        assertEquals(resultExpected, result);

        inFile.close();
    }

    /**
     * Test printWithLineSeparators input "~OHIO~~STATE~UNIVERSITY~".
     *
     * @throws FileNotFoundException
     */
    @Test
    public void printWithLineSeparators_4() throws FileNotFoundException {
        String file = "data/outputTest";
        SimpleWriter textWriter = new SimpleWriter1L(file);

        String text = "~OHIO~~STATE~UNIVERSITY~";
        StringReassembly.printWithLineSeparators(text, textWriter);

        String inputFileName = file;
        SimpleReader inFile = new SimpleReader1L(inputFileName);

        Queue<String> result = new Queue2<>();
        while (!inFile.atEOS()) {
            String element = inFile.nextLine();
            result.enqueue(element);
        }

        Queue<String> resultExpected = new Queue2<>();
        resultExpected.enqueue("");
        resultExpected.enqueue("OHIO");
        resultExpected.enqueue("");
        resultExpected.enqueue("STATE");
        resultExpected.enqueue("UNIVERSITY");

        assertEquals(resultExpected, result);

        inFile.close();
    }

    /**
     * Test printWithLineSeparators input "~~OHIO~~STATE~UNIVERSITY~".
     *
     * @throws FileNotFoundException
     */
    @Test
    public void printWithLineSeparators_5() throws FileNotFoundException {
        String file = "data/outputTest";
        SimpleWriter textWriter = new SimpleWriter1L(file);

        String text = "~~OHIO~~STATE~UNIVERSITY~";
        StringReassembly.printWithLineSeparators(text, textWriter);

        String inputFileName = file;
        SimpleReader inFile = new SimpleReader1L(inputFileName);

        Queue<String> result = new Queue2<>();
        while (!inFile.atEOS()) {
            String element = inFile.nextLine();
            result.enqueue(element);
        }

        Queue<String> resultExpected = new Queue2<>();
        resultExpected.enqueue("");
        resultExpected.enqueue("");
        resultExpected.enqueue("OHIO");
        resultExpected.enqueue("");
        resultExpected.enqueue("STATE");
        resultExpected.enqueue("UNIVERSITY");

        assertEquals(resultExpected, result);

        inFile.close();
    }

    /**
     * Test printWithLineSeparators input "~OHIO~STATE~UNIVERSITY~".
     *
     * @throws FileNotFoundException
     */
    @Test
    public void printWithLineSeparators_6() throws FileNotFoundException {
        String file = "data/outputTest";
        SimpleWriter textWriter = new SimpleWriter1L(file);

        String text = "~OHIO~STATE~UNIVERSITY~";
        StringReassembly.printWithLineSeparators(text, textWriter);

        String inputFileName = file;
        SimpleReader inFile = new SimpleReader1L(inputFileName);

        Queue<String> result = new Queue2<>();
        while (!inFile.atEOS()) {
            String element = inFile.nextLine();
            result.enqueue(element);
        }

        Queue<String> resultExpected = new Queue2<>();
        resultExpected.enqueue("");
        resultExpected.enqueue("OHIO");
        resultExpected.enqueue("STATE");
        resultExpected.enqueue("UNIVERSITY");

        assertEquals(resultExpected, result);

        inFile.close();
    }

    /**
     * Test printWithLineSeparators input "~OHIO~STATE~UNIVERSITY~~".
     *
     * @throws FileNotFoundException
     */
    @Test
    public void printWithLineSeparators_7() throws FileNotFoundException {
        String file = "data/outputTest";
        SimpleWriter textWriter = new SimpleWriter1L(file);

        String text = "~OHIO~STATE~UNIVERSITY~~";
        StringReassembly.printWithLineSeparators(text, textWriter);

        String inputFileName = file;
        SimpleReader inFile = new SimpleReader1L(inputFileName);

        Queue<String> result = new Queue2<>();
        while (!inFile.atEOS()) {
            String element = inFile.nextLine();
            result.enqueue(element);
        }

        Queue<String> resultExpected = new Queue2<>();
        resultExpected.enqueue("");
        resultExpected.enqueue("OHIO");
        resultExpected.enqueue("STATE");
        resultExpected.enqueue("UNIVERSITY");
        resultExpected.enqueue("");

        assertEquals(resultExpected, result);

        inFile.close();
    }

}
