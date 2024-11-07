package numberrangesummarizer;

import java.util.Collection;
import java.util.PriorityQueue;

/**
 * An implementation of the {@code NumberRangeSummarizer} interface for input in CSV format
 */
public class CSVNumberRangeSummarizer implements NumberRangeSummarizer{
    /**
     * Convert a comma-separated list of ints into a {@code Collection<Integer>} (a {@code
     * PriorityQueue<Integer>} to be precise)
     * @param input Comma-separated list of integers
     * @return A {@code PriorityQueue} of the integers in {@code input}
     */
    @Override
    public Collection<Integer> collect(String input) {
        String[] tokens = input.split(",");
        PriorityQueue<Integer> collection = new PriorityQueue<>();
        for (String token : tokens) {
            int num;
            try {
                //remove whitespace that could trip up parseInt
                num = Integer.parseInt(token.strip());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Input should be integers separated by commas, '"
                        + token + "' is not an integer");
            }
            if(num < 0){
                throw new NumberFormatException("Negative numbers are not supported");
            }
            collection.add(num);
        }
        return collection;
    }

    /**
     * Summarize a collection of ints by condensing consecutive integers into a range
     * @param input The integer {@code Collection} to summarize
     * @return The integers in {@code input} in a comma-separated list, with consecutive ints
     *          condensed into ranges (e.g. 1-3, 5)
     */
    @Override
    public String summarizeCollection(Collection<Integer> input) {
        //use PriorityQueue to keep ints in order
        PriorityQueue<Integer> collection = new PriorityQueue<>(input);
        StringBuilder ans = new StringBuilder();
        int num = collection.poll();
        //store start and end of range
        Range range = new Range(num, num);
        while (!collection.isEmpty()) {
            num = collection.poll();
            if(num == range.end){
                //same as previous num, ignore
                continue;
            }
            if(num == range.end + 1){
                //continue range
                range.end ++;
            }else{
                //new range
                ans.append(range);
                ans.append(", ");
                range = new Range(num, num);
            }
        }
        ans.append(range);
        return ans.toString();
    }

    /**
     * Encapsulate range of numbers in summary
     */
    public static class Range{
        int start;
        int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            if (start != end) {
                return start + "-" + end;
            } else {
                return "" + start;
            }
        }
    }
}
