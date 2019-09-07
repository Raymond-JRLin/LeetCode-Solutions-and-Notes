// 179. Largest Number
// DescriptionHintsSubmissionsDiscussSolution
// Given a list of non negative integers, arrange them such that they form the largest number.
//
// Example 1:
//
// Input: [10,2]
// Output: "210"
// Example 2:
//
// Input: [3,30,34,5,9]
// Output: "9534330"
// Note: The result may be very large, so you need to return a string instead of an integer.
//
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }

        return method1(nums);
    }

    private String method1(int[] nums) {
        int n = nums.length;
        String[] array = new String[n];
        for (int i = 0; i < n; i++) {
            array[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(array, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String str1 = o1 + o2;
                String str2 = o2 + o1;
                return str2.compareTo(str1);
            }
        });
        if (array[0].equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : array) {
            sb.append(s);
        }
        return sb.toString();
    }

    // wrong
    private String mytry(int[] nums) {
        // 排序比较的是从高位开始， int 是不好做到的， 把它转换成 String
        int n = nums.length;
        String[] array = new String[n];
        for (int i = 0; i < n; i++) {
            array[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(array, new Comparator<String>() {
            @Override
            // 这个 comparator 是错误的
            public int compare(String o1, String o2) {
                int i = 0;
                int j = 0;
                while (i < o1.length() && j < o2.length()) {
                    if (o1.charAt(i) - '0' > o2.charAt(j) - '0') {
                        return -1;
                    } else if (o1.charAt(i) - '0' < o2.charAt(j) - '0') {
                        return 1;
                    } else {
                        i++;
                        j++;
                    }
                }
                // 一个走完， 一个没走完是很难去判断的， 譬如 12， 121， 按照下面 121 比 12 大， 但实际上应该这么组成 12/121
                // 所以应该是比较两者组合之后的数谁更大
                if (i == o1.length() && j == o2.length()) {
                    return o2.charAt(j - 1) - o1.charAt(i - 1);
                }
                if (i == o1.length() && o2.charAt(j) == '0') {
                    return 1;
                }
                if (j == o2.length() && o1.charAt(i) == '0') {
                    return -1;
                }
                if (i == 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String s : array) {
            sb.append(s);
        }
        return sb.toString();
    }
}
