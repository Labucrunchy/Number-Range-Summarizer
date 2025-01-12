package numberrangesummarizer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Phillip
 * 
 * Unit tests for the NumberRangeSummarizerImpl class.
 * 
 * Tests validate correct handling of:
 *   - Standard input processing and summarization (including negative numbers).
 *   - Sorting input and omitting duplicates.
 *   - Invalid inputs like null, non-integer, or improperly-formatted strings.
 */
class NumberRangeSummarizerTest {

    @Test
    void testGivenExample() {

        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        String expectedOutput = "1, 3, 6-8, 12-15, 21-24, 31";

        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();

        Collection<Integer> collected = summarizer.collect(input);
        String result = summarizer.summarizeCollection(collected);

        assertEquals(expectedOutput, result, "The expected summary was not returned.");
    }

    @Test
    void testEndsWithRange() {

        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24";
        String expectedOutput = "1, 3, 6-8, 12-15, 21-24";

        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();

        Collection<Integer> collected = summarizer.collect(input);
        String result = summarizer.summarizeCollection(collected);

        assertEquals(expectedOutput, result, "The expected summary was not returned.");
    }

    @Test
    void testNegativeNumbers() {
        String input = "-10,-9,-8,-7,-5,-1,1,2,3,5";
        String expectedOutput = "-10--7, -5, -1, 1-3, 5";

        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();

        Collection<Integer> collected = summarizer.collect(input);
        String result = summarizer.summarizeCollection(collected);

        assertEquals(expectedOutput, result, "The expected summary was not returned.");
    }

    @Test
    void testEmptyInput() {
        String input = "";
        String expectedOutput = "";

        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();

        Collection<Integer> collected = summarizer.collect(input);
        String result = summarizer.summarizeCollection(collected);

        assertEquals(expectedOutput, result, "The expected summary was not returned.");
    }

    @Test
    void testSorting() {
        String input = "5,4,3,2,1";
        Collection<Integer> expectedOutput = Arrays.asList(1, 2, 3, 4, 5);

        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();

        Collection<Integer> collected = summarizer.collect(input);
        Collection<Integer> collectedArray = new ArrayList<>(collected);

        assertEquals(expectedOutput, collectedArray, "The expected summary was not returned.");
    }

    @Test
    void testRemovesDuplicates() {
        String input = "1,2,2,3,4,4,5,6,10,13";
        String expectedOutput = "1-6, 10, 13";

        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();

        Collection<Integer> collected = summarizer.collect(input);
        String result = summarizer.summarizeCollection(collected);

        assertEquals(expectedOutput, result, "The expected summary was not returned.");
    }

    @Test
    void testValidWithSpaces() {
        String input = " 1 , 2 , 3 ";
        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();
        Collection<Integer> result = summarizer.collect(input);
        assertEquals(Arrays.asList(1, 2, 3), new ArrayList<>(result));
    }

    @Test
    void testNullInput() {
        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> summarizer.collect(null));
        assertEquals("Input cannot be null.", exception.getMessage());
    }

    @Test
    void testNonIntegerInput() {
        String input = "a,b,c";
        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> summarizer.collect(input));
        assertEquals("Input cannot contain non-integer values.", exception.getMessage());
    }
    
    @Test
    void testEmptyElement() {
        String input = "1,,2";
        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> summarizer.collect(input));
        assertEquals("Input cannot contain non-integer values.", exception.getMessage());
    }

    @Test
    void testStartsWithComma() {
        String input = ",1,2,3";
        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> summarizer.collect(input));
        assertEquals("Input cannot begin or end with a comma.", exception.getMessage());
    }

    @Test
    void testEndsWithComma() {
        String input = "1,2,3,";
        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> summarizer.collect(input));
        assertEquals("Input cannot begin or end with a comma.", exception.getMessage());
    }

}
