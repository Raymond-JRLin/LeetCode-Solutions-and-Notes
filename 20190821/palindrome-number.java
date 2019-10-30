// 9. Palindrome Number
// DescriptionHintsSubmissionsDiscussSolution
//
// Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
//
// Example 1:
//
// Input: 121
// Output: true
//
// Example 2:
//
// Input: -121
// Output: false
// Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
//
// Example 3:
//
// Input: 10
// Output: false
// Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
//
// Follow up:
//
// Coud you solve it without converting the integer to a string?
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        // return mytry(x);

        return method2(x);
    }

    private boolean method2(int x) {
        // palindrome 其实就是关于中心镜像对称
        // 连续的 x /= 10 可以得到高位一半（左半边）
        // num * 10 + x % 10 可以得到低位一半（右半边）， 且是从右到左的顺序， 即已经翻转了
        // 所以可以比较上述两半边是否一样， 或者差一位一样 （x 有奇数个）

        // edge case: trailing zeros 肯定不行
        if (x >= 10 && x % 10 == 0) {
            return false;
        }
        int right = 0;
        while (x > right) {
            right = right * 10 + x % 10;
            x /= 10;
        }
        // x 是奇数个位数， right >= x, 奇数的话， right 要抹掉最后一位， 即原 x 中间那一位
        return x == right || right / 10 == x;
    }

    private boolean mytry(int x) {
        StringBuilder sb = new StringBuilder();
        while (x != 0) {
            sb.append(x % 10);
            x /= 10;
        }
        String s = sb.reverse().toString();
        return isPalindrome(s);
    }
    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
