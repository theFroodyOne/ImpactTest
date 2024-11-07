# CSV Number Range Summarizer

## Assumptions
1. Invalid input causes a NumberFormatException to be thrown instead of trying to recover somehow, like returning a meaningless result
2. Input should be non-null and non-empty
3. Duplicates in the list are ignored
4. Whilst this implementation works for negative numbers, they are intentionally excluded since the resultant output is hard to interpret