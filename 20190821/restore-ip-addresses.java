// 93. Restore IP Addresses
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a string containing only digits, restore it by returning all possible valid IP address combinations.
//
// Example:
//
// Input: "25525511135"
// Output: ["255.255.11.135", "255.255.111.35"]
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() == 0 || s.length() > 12) {
            return Collections.emptyList();
        }

        // return method1(s);

        return method2(s);
    }

    // 找所有可能的组合， DFS 没跑了， 就是找合乎要求的地址
    // 合法的是： 4 个分段， 每个 [0, 255]
    // 所以在每个 DFS 中查 2 个条件： 4 个分段， 每个分段都要满足 [0, 255]

    private List<String> method2(String s) {
        // 直接 iteration 找所有可能的结果， 看是否合法
        int n = s.length();
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                for (int k = 1; k <= 3; k++) {
                    for (int l = 1; l <= 3; l++) {
                        if (i + j + k + l != n) {
                            continue;
                        }
                        int a = Integer.parseInt(s.substring(0, i));
                        int b = Integer.parseInt(s.substring(i, i + j));
                        int c = Integer.parseInt(s.substring(i + j, i + j + k));
                        int d = Integer.parseInt(s.substring(i + j + k, i + j + k + l));
                        if (!isValid(a) || !isValid(b) || !isValid(c) || !isValid(d)) {
                            continue;
                        }
                        String curr = a + "." + b + "." + c + "." + d;
                        // 上面个 index 可能不连续
                        if (curr.length() != n + 3) {
                            continue;
                        }
                        result.add(curr);
                    }
                }
            }
        }
        return result;
    }
    private boolean isValid(int num) {
        return num >= 0 && num <= 255;
    }

    private List<String> method1(String s) {
        List<String> result = new ArrayList<>();
        dfs(s, result, "", 0, 0);
        return result;
    }
    private void dfs(String s, List<String> result, String addr, int index, int count) {
        if (index == s.length()) {
            if (count == 4) {
                result.add(addr);
            }
            return;
        }
        // 找此 portion （4个中其中一个）的所有可能
        for (int i = 1; i < 4; i++) {
            int end = index + i;
            // 超出 s 范围
            if (end > s.length()) {
                break;
            }
            String curr = s.substring(index, end);
            // 查当前 curr 是否合法: [0, 255]
            if (curr.length() == 2 && curr.charAt(0) == '0' || curr.length() == 3 && Integer.parseInt(curr) > 255) {
                break;
            }
            dfs(s, result, addr + (count == 0 ? "" : ".") + curr, end, count + 1);
        }
    }
}
