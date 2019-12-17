// 1291. Sequential Digits
//
//     User Accepted: 2248
//     User Tried: 2520
//     Total Accepted: 2284
//     Total Submissions: 4991
//     Difficulty: Medium
//
// An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
//
// Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
//
//
//
// Example 1:
//
// Input: low = 100, high = 300
// Output: [123,234]
//
// Example 2:
//
// Input: low = 1000, high = 13000
// Output: [1234,2345,3456,4567,5678,6789,12345]
//
//
//
// Constraints:
//
//     10 <= low <= high <= 10^9
//


class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        String l = String.valueOf(low);
        String h = String.valueOf(high);
        int start = l.length();
        int end = h.length();
        List<Integer> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            char c = '1';
            if (i == start) {
                c = l.charAt(0);
            }
            // System.out.println("create number starting form " + (c - '0') + ", with length of " + i);
            for (int first = c - '0'; first <= (9 + 1 - i); first++) {
                int num = getNum(first, i);
                // System.out.println("num = " + num);
                if (num < low) {
                    continue;
                }
                if (num > high) {
                    break;
                }
                result.add(num);
            }
        }
        return result;
    }
    private int getNum(int start, int len) {
        int num = 0;
        while (len > 0) {
            num = num * 10 + start;
            start++;
            len--;
        }
        return num;
    }
}
