// 1056. Confusing Number
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a number N, return true if and only if it is a confusing number, which satisfies the following condition:
//
// We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid. A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.
//
//
//
// Example 1:
//
// Input: 6
// Output: true
// Explanation:
// We get 9 after rotating 6, 9 is a valid number and 9!=6.
//
// Example 2:
//
// Input: 89
// Output: true
// Explanation:
// We get 68 after rotating 89, 86 is a valid number and 86!=89.
//
// Example 3:
//
// Input: 11
// Output: false
// Explanation:
// We get 11 after rotating 11, 11 is a valid number but the value remains the same, thus 11 is not a confusing number.
//
// Example 4:
//
// Input: 25
// Output: false
// Explanation:
// We get an invalid number after rotating 25.
//
//
//
// Note:
//
//     0 <= N <= 10^9
//     After the rotation we can ignore leading zeros, for example if after rotation we have 0008 then this number is considered as just 8.
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public boolean confusingNumber(int N) {


        // return mytry(N);

        return method2(N);
    }

    private boolean method2(int n) {
        // 把配对的， 即翻转之后一样的， 拿过来， 然后比较翻转前后是否是一样的
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(6, 9);
        map.put(9, 6);
        map.put(0, 0);
        map.put(1, 1);
        map.put(8, 8);

        int reverse = 0;
        int ori = n;
        while (n > 0) {
            int residue = n % 10;
            // invalid
            if (!map.containsKey(residue)) {
                return false;
            }
            // 这题因为 n 的大小限制， 虽然不会出现这种情况， 但是仍然要考虑到这个 case
            if(reverse > Integer.MAX_VALUE / 10) {
                return false;
            }
            // reverse number
            reverse = reverse * 10 + map.get(residue);
            n /= 10;
        }
        return reverse != ori;
    }

    private boolean mytry(int n) {
        // corner case 还是很多的
        // 如果有 invalid digit， 就是翻转之后不是 valid number， 那么肯定不会 confusing
        Set<Integer> invalidDigits = new HashSet<>(new ArrayList<>(Arrays.asList(2, 3, 4, 5, 7)));

        if (n < 10) {
            // 注意 0， 1， 8 翻转是一样的
            if (invalidDigits.contains(n) || n == 1 || n == 0 || n == 8) {
                return false;
            } else {
                return true;
            }
        }
        // 分成两边来比较
        // 6/9 翻转会互换， 所以就想等， 不会 confusing
        Set<String> pairs = new HashSet<>(new ArrayList<>(Arrays.asList("69", "96")));
        String s = String.valueOf(n);
        int len = s.length();

        int leftEnd = len % 2 == 0 ? (len - 1) / 2 : len / 2 - 1;
        int rightStart = (len + 1) / 2;
        // 如果是奇数长度， 中间这个如果 invalid， 那么也不会 confusing
        if (len % 2 != 0) {
            if (invalidDigits.contains(s.charAt(len / 2) - '0')) {
                return false;
            }
        }

        boolean isConfusing = false;
        // 分两边对应查看
        while (leftEnd >= 0 && rightStart < len) {
            int left = s.charAt(leftEnd--) - '0';
            int right = s.charAt(rightStart++) - '0';

            if (invalidDigits.contains(left) || invalidDigits.contains(right)) {
                return false;
            }
            // 如果两边数字一样， 那么翻转之后也一样， 就没问题， 不会 confusing
            // 如果不等， 并且不是 6/9 互换， 那么就可能会 confusing
            if (left != right && !pairs.contains(left + "" + right)) {
                isConfusing = true;
            }
        }

        return isConfusing;
    }
}
