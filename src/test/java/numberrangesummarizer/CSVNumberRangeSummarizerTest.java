package numberrangesummarizer;

import org.junit.jupiter.api.Test;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

public class CSVNumberRangeSummarizerTest {
    /**
     * Tests for error inputs
     */
    @Test
    public void stringInput(){
        CSVNumberRangeSummarizer summarizer = new CSVNumberRangeSummarizer();
        boolean thrown = false;
        try {
            Collection<Integer> collection = summarizer.collect("forty-two, 17, 428");
        } catch (NumberFormatException e) {
            thrown = true;
            assertEquals("Input should be integers separated by commas, 'forty-two' is not an " +
                    "integer", e.getMessage());
        }
        assertTrue(thrown);
    }

    @Test
    public void floatInput(){
        CSVNumberRangeSummarizer summarizer = new CSVNumberRangeSummarizer();
        boolean thrown = false;
        try {
            Collection<Integer> collection = summarizer.collect("4.2, 17, 428");
        } catch (NumberFormatException e) {
            thrown = true;
            assertEquals("Input should be integers separated by commas, '4.2' is not an integer",
                    e.getMessage());
        }
        assertTrue(thrown);
    }

    @Test
    public void emptyInput(){
        CSVNumberRangeSummarizer summarizer = new CSVNumberRangeSummarizer();
        boolean thrown = false;
        try {
            Collection<Integer> collection = summarizer.collect("");
        } catch (NumberFormatException e) {
            thrown = true;
            assertEquals("Input should be integers separated by commas, '' is not an integer",
                    e.getMessage());
        }
        assertTrue(thrown);
    }

    @Test
    public void nullInput(){
        CSVNumberRangeSummarizer summarizer = new CSVNumberRangeSummarizer();
        boolean thrown = false;
        try {
            Collection<Integer> collection = summarizer.collect(null);
        } catch (NullPointerException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void negativeInput(){
        CSVNumberRangeSummarizer summarizer = new CSVNumberRangeSummarizer();
        boolean thrown = false;
        try {
            Collection<Integer> collection = summarizer.collect("-4, 17, 428");
        } catch (NumberFormatException e) {
            thrown = true;
            assertEquals("Negative numbers are not supported", e.getMessage());
        }
        assertTrue(thrown);
    }

    /**
     * Functionality tests
     */
    @Test
    public void givenExample(){
        CSVNumberRangeSummarizer summarizer = new CSVNumberRangeSummarizer();
        assertEquals("1, 3, 6-8, 12-15, 21-24, 31", summarizer.summarizeCollection(summarizer.
                collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31")));
    }

    @Test
    public void duplicateInput(){
        CSVNumberRangeSummarizer summarizer = new CSVNumberRangeSummarizer();
        assertEquals("1-3, 5", summarizer.summarizeCollection(summarizer.
                collect("1, 1, 1, 1, 2, 3, 5")));
    }

    @Test
    public void singleRange(){
        CSVNumberRangeSummarizer summarizer = new CSVNumberRangeSummarizer();
        assertEquals("1-10", summarizer.summarizeCollection(summarizer.
                collect("1, 2, 3, 4, 5, 6, 7, 8, 9, 10")));
    }

    @Test
    public void singleInput(){
        CSVNumberRangeSummarizer summarizer = new CSVNumberRangeSummarizer();
        assertEquals("1", summarizer.summarizeCollection(summarizer.collect("1")));
    }
}
