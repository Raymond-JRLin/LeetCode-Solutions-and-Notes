// 246. Strobogrammatic Number
// DescriptionHintsSubmissionsDiscussSolution
// A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
//
// Write a function to determine if a number is strobogrammatic. The number is represented as a string.
//
// Example 1:
//
// Input:  "69"
// Output: true
// Example 2:
//
// Input:  "88"
// Output: true
// Example 3:
//
// Input:  "962"
// Output: false


class Solution {
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) {
            return true;
        }

        // return mytry(num);

        // return method2(num);

        return method3(num);
    }

    private boolean method3(String s) {
        // tricky String
        // https://leetcode.com/problems/strobogrammatic-number/discuss/67188/4-lines-in-Java
        int n = s.length();
        int left = 0;
        int right = n - 1;
        // record all valid pairs into a string
        String pair = "00 11 88 696";
        // we should use <= , since even though there's only 1 (or left), we still need to check it if it's in set
        while (left <= right) {
            // use string.contains() to check
            if (!pair.contains(s.charAt(left) + "" + s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean method2(String s) {
        // HashMap
        // https://leetcode.com/problems/strobogrammatic-number/discuss/67182/Accepted-Java-solution
        int n = s.length();
        int left = 0;
        int right = n - 1;
        // 因为是要 pair 左右相对， 那用 map 更好一些
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        map.put('6', '9');
        map.put('9', '6');
        // we should use <= , since even though there's only 1 (or left), we still need to check it if it's in set
        while (left <= right) {
            // 只查 left 就好了， right 留给下一个 if 去判断
            if (!map.containsKey(s.charAt(left))) {
                return false;
            }
            if (map.get(s.charAt(left)) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean mytry(String s) {
        // HashSet
        int n = s.length();
        int left = 0;
        int right = n - 1;
        Set<Character> set = new HashSet<>();
        set.add('0');
        set.add('1');
        set.add('6');
        set.add('9');
        set.add('8');
        // we should use <= , since even though there's only 1 (or left), we still need to check it if it's in set
        while (left <= right) {
            if ((s.charAt(left) == '1' || s.charAt(left) == '8' || s.charAt(left) == '0' ||
                 s.charAt(right) == '1' || s.charAt(right) == '8' || s.charAt(right) == '0')
                && s.charAt(left) != s.charAt(right)) {
                return false;
            } else if (s.charAt(left) == '6' && s.charAt(right) != '9' || s.charAt(left) == '9' && s.charAt(right) != '6') {
                return false;
            } else if (!set.contains(s.charAt(left)) || !set.contains(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
