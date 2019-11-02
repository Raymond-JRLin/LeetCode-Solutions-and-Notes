// 468. Validate IP Address
// DescriptionHintsSubmissionsDiscussSolution
//
// Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.
//
// IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;
//
// Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.
//
// IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).
//
// However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.
//
// Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.
//
// Note: You may assume there is no extra space or special characters in the input string.
//
// Example 1:
//
// Input: "172.16.254.1"
//
// Output: "IPv4"
//
// Explanation: This is a valid IPv4 address, return "IPv4".
//
// Example 2:
//
// Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
//
// Output: "IPv6"
//
// Explanation: This is a valid IPv6 address, return "IPv6".
//
// Example 3:
//
// Input: "256.256.256.256"
//
// Output: "Neither"
//
// Explanation: This is neither a IPv4 address nor a IPv6 address.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public String validIPAddress(String IP) {
        if (IP == null || IP.length() < 4) {
            return "Neither";
        }

        return method1(IP);
    }

    // 分别去判断
    // IPv4: 点分割， 分割后 4 部分， 每部分是 [0, 255]
    // IPv6: 冒号分割， 分割后 8 部分， 每部分数字是 [0, 9], 字母可以是 [a, f] or [A, F]

    private String method1(String ip) {
        if (isIpv4(ip)) {
            return "IPv4";
        } else if (isIpv6(ip)) {
            return "IPv6";
        } else {
            return "Neither";
        }
    }
    private boolean isIpv4(String s) {
        int n = s.length();
        if (n < 7) {
            // 至少 4 个字母， 3 个点
            return false;
        }
        // 第一个和最后一个不能为 .
        if (s.charAt(0) == '.' || s.charAt(n - 1) == '.') {
            return false;
        }
        // 用 . 分开
        String[] tokens = s.split("\\.");
        if (tokens.length != 4) {
            return false;
        }
        // 查每一部分
        for (String token : tokens) {
            if (!isIpv4Token(token)) {
                return false;
            }
        }
        return true;
    }
    private boolean isIpv4Token(String s) {
        int n = s.length();
        if (n <= 0 || n > 3 || n > 1 && s.charAt(0) == '0') {
            return false;
        }
        // 第一个可能是负号
        if (!Character.isDigit(s.charAt(0))) {
            return false;
        }
        // 可能不一定能过 parse
        try {
            int num = Integer.parseInt(s);
            if (num < 0 || num > 255) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }
    private boolean isIpv6(String s) {
        if (s.length() < 15) {
            // 至少 8 个字母， 7 个冒号
            return false;
        }
        int n = s.length();
        // 第一个和最后一个不能为 :
        if (s.charAt(0) == ':' || s.charAt(n - 1) == ':') {
            return false;
        }
        // 用 : 分开
        String[] tokens = s.split(":");
        if (tokens.length != 8) {
            return false;
        }
        // 查每一部分
        for (String token : tokens) {
            if (!isIpv6Token(token)) {
                return false;
            }
        }
        return true;
    }
    private boolean isIpv6Token(String s) {
        int n = s.length();
        // Ipv6 每部分长度为 4
        if (n <= 0 || n > 4) {
            return false;
        }
        char[] arr = s.toCharArray();
        // 每个字符要么是数字 [0, 9], 要么是字母 [a, f] or [A, F]
        for (char c : arr) {
            boolean isDigit = (c >= '0' && c <= '9');
            boolean isUppercase = (c >= 'A' && c <= 'F');
            boolean isLowercase = (c >= 'a' && c <= 'f');
            // 3 个全部不满足
            if (!isDigit && !isUppercase && !isLowercase) {
                return false;
            }
        }
        return true;
    }
}
