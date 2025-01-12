package numberrangesummarizer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Phillip
 * 
 * This class implements the NumberRangeSummarizer interface to parse
 * and summarize a collection of integers into a comma-delimited string
 * of ranges. Duplicate numbers are ignored as they are already present
 * in a given range. If the empty string is given as input, it also returns 
 * the empty string as this is simply a summary containing no numbers.
 */
public class NumberRangeSummarizerImpl implements NumberRangeSummarizer {

    /**
     * Parses a comma-separated string of integers and returns a sorted, unique
     * collection.
     * 
     * @param input the input string containing integers separated by commas
     * @return a sorted Collection of unique integers
     * @throws IllegalArgumentException if the input is null or invalid
     */
    @Override
    public Collection<Integer> collect(String input) {

        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }

        if (input.trim().isEmpty()) {
            return Collections.emptyList(); // Return an empty collection for an empty string.
        }

        if (input.trim().startsWith(",") || input.trim().endsWith(",")) {
            throw new IllegalArgumentException("Input cannot begin or end with a comma.");
        }

        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(TreeSet::new)); // A TreeSet is used to ensure the set is sorted
                                                                     // and that all elements are unique.
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input cannot contain non-integer values.", e);
        }
    }

    /**
     * Converts a collection of integers into a summarized string of ranges.
     * Consecutive numbers are grouped into ranges, while others are listed
     * individually.
     * 
     * @param input a sorted collection of unique integers
     * @return a comma-delimited summary string of number ranges
     */
    @Override
    public String summarizeCollection(Collection<Integer> input) {

        if (input.isEmpty()) {
            return "";  // Base case for when an empty list is received (input string was "")
        }

        List<Integer> sortedInput = new ArrayList<>(input);

        StringBuilder summaryString = new StringBuilder();
        int start = sortedInput.get(0);
        int end = start;
        int current;

        summaryString.append(start); // Add the first number immediately

        for (int i = 1; i < sortedInput.size(); i++) {
            current = sortedInput.get(i);
            if (current == end + 1) {
                end = current;
            } else {
                if (end > start) {
                    summaryString.append("-").append(end); // Add the second part of the range if it is encountered.
                }
                summaryString.append(", ").append(current); // Always add the first part of the next range immediately.
                start = current;
                end = current;
            }
        }

        if (end > start) {
            summaryString.append("-").append(end); // Add the second part of the final range if needed.
        }

        return summaryString.toString();
    }
}
