// 93. Restore IP Addresses
// DescriptionHintsSubmissionsDiscussSolution
// Given a string containing only digits, restore it by returning all possible valid IP address combinations.
//
// Example:
//
// Input: "25525511135"
//
// Output: ["255.255.11.135", "255.255.111.35"]


class Solution {
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() < 4 || s.length() > 12) {
            return Collections.emptyList();
        }

        // return method1(s);

        return method2(s);
    }

    private List<String> method2(String s) {
        // ref: https://leetcode.com/problems/restore-ip-addresses/discuss/30944/Very-simple-DFS-solution
        List<String> result = new ArrayList<>();
        dfs(s, result, "", 0, 0);
        return result;
    }
    private void dfs(String s, List<String> result, String ip, int index, int count) {
        if (count > 4) {
            return;
        }
        if (count == 4 && index == s.length()) {
            result.add(ip);
            return;
        }
        for (int i = 0; i < 4; i++) {
            String curr = s.substring(index, index + i);
            if (!isValid(curr)) {
                continue;
            }
            ip += curr + (count < 4 ? "." : "");
            dfs(s, result, ip, index + i, count + 1);
        }
    }

    private List<String> method1(String s) {
        // ref: https://leetcode.com/problems/restore-ip-addresses/discuss/30949/My-code-in-Java
        int n = s.length();
        List<String> result = new ArrayList<>();
        // 把每一种可能取出来判断是否 valid， 注意取值范围， i j k 都是 end index
        for (int i = 1; i < 4 && i < n - 2; i++) {
            for (int j = i + 1; j < i + 4 && j < n - 1; j++) {
                for (int k = j + 1; k < j + 4 && k < n; k++) {
                    String a = s.substring(0, i);
                    String b = s.substring(i, j);
                    String c = s.substring(j, k);
                    String d = s.substring(k, n);
                    // check is valid
                    if (isValid(a) && isValid(b) && isValid(c) && isValid(d)) {
                        String ip = a + "." + b + "." + c + "." + d;
                        result.add(ip);
                    }
                }
            }
        }
        return result;
    }
    private boolean isValid(String s) {
        // [0, 255]
        if (s.length() > 3 || s.length() > 1 && s.charAt(0) == '0' || Integer.parseInt(s) > 255) {
            return false;
        }
        return true;
    }
}
