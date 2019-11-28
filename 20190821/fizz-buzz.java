// 412. Fizz Buzz
// DescriptionHintsSubmissionsDiscussSolution
//
// Write a program that outputs the string representation of numbers from 1 to n.
//
// But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.
//
// Example:
//
// n = 15,
//
// Return:
// [
//     "1",
//     "2",
//     "Fizz",
//     "4",
//     "Buzz",
//     "Fizz",
//     "7",
//     "8",
//     "Fizz",
//     "Buzz",
//     "11",
//     "Fizz",
//     "13",
//     "14",
//     "FizzBuzz"
// ]
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public List<String> fizzBuzz(int n) {
        if (n < 1) {
            return Collections.emptyList();
        }

        // return mytry(n);

        return method2(n);
    }

    private List<String> method2(int n) {
        // not use %
        List<String> result = new ArrayList<>();
        final String FIZZ = "Fizz";
        final String BUZZ = "Buzz";
        int f = 3, b = 5;
        for (int i = 1; i <= n; i++) {
            if (i == f && i == b) {
                result.add(FIZZ + BUZZ);
                f += 3;
                b += 5;
            } else if (i == f) {
                f += 3;
                result.add(FIZZ);
            } else if (i == b) {
                b += 5;
                result.add(BUZZ);
            } else {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }

    private List<String> mytry(int n) {
        List<String> result = new ArrayList<>();
        final String FIZZ = "Fizz";
        final String BUZZ = "Buzz";
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                result.add(FIZZ + BUZZ);
            } else if (i % 3 == 0) {
                result.add(FIZZ);
            } else if (i % 5 == 0) {
                result.add(BUZZ);
            } else {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }
}
