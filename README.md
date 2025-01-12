# Number-Range-Summarizer

### Description

This is a Maven project containing an interface and implementation for a number range summarizer as well as tests using JUnit 5 to verify functionality and robustness.

The summarizer takes as input a comma-delimited list of integers and returns a comma-delimited list of integers in ascending order with sequential integers being grouped into ranges. 

### Example:

 ``Sample Input: "1,3,6,7,8,12,13,14,15,21,22,23,24,31"``

 ``Result: "1, 3, 6-8, 12-15, 21-24, 31"``

### Additional Information

- Negative numbers are allowed
- If an empty string is given, the result is also an empty string since it is simply a summary with no numbers
- The input is not assumed to be sorted.
- The input may contain duplicates which are ignored as these numbers will already appear in the summary.
- A TreeSet is used to store the input as it ensures that the result is sorted and contains no duplicates.
- Non-integer input will result in errors being thrown.