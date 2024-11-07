package numberrangesummarizer;

import java.util.Collection;
import java.util.PriorityQueue;

/**
 * An implementation of the {@code NumberRangeSummarizer} interface for input in CSV format
 */
public class CSVNumberRangeSummarizer implements NumberRangeSummarizer{
    @Override
    public Collection<Integer> collect(String input) {
        String[] tokens = input.split(",");
        PriorityQueue<Integer> collection = new PriorityQueue<>();
        for (String token : tokens) {
            try {
                collection.add(Integer.parseInt(token.strip()));
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Input should be integers separated by commas, '" + token + "' is not an integer");
            }
        }
        return collection;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        PriorityQueue<Integer> collection = new PriorityQueue<>(input);
        StringBuilder ans = new StringBuilder();
        Range range = null;
        while (!collection.isEmpty()) {
            int num = collection.poll();
            if (range == null) {
                range = new Range(num, num);
                continue;
            }
            if(num == range.end){
                continue;
            }
            if(num != range.end + 1){
                ans.append(range);
                ans.append(", ");
                range = null;
            }else{
                range.end ++;
            }
            if (range == null) {
                range = new Range(num, num);
            }
        }
        ans.append(range);
        return ans.toString();
    }

    public class Range{
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

    public static void main(String[] args) {
        CSVNumberRangeSummarizer s = new CSVNumberRangeSummarizer();
        System.out.println(s.collect("forty-two, 17, 428"));
        System.out.println(s.summarizeCollection(s.collect("1, 2, 3, 5")));
    }
}
