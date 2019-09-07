// 163. Missing Ranges
// DescriptionHintsSubmissionsDiscussSolution
// Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
//
// Example:
//
// Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
// Output: ["2", "4->49", "51->74", "76->99"]
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            List<String> result = new ArrayList<>();
            if (lower == upper) {
                result.add(String.valueOf(lower));
            } else {
                result.add(String.valueOf(lower) + "->" + String.valueOf(upper));
            }
            return result;
        }

        // wrong and annoying
        // return mytry(nums, lower, upper);

        // return method1(nums, lower, upper);

        return method2(nums, lower, upper);

    }

    private List<String> method2(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        long next = (long) lower; // the next value we gonna find
        for (int num : nums) {
            if (num < next) {
                continue;
            }
            if (num == next) {
                next++;
                continue;
            }
            // next < num
            result.add(getRange(next, num - 1));
            next = (long) num + 1;
        }
        // check the last one and upper
        if (next <= upper) {
            result.add(getRange(next, upper));
        }
        return result;
    }
    private String getRange(long next, int num) {
        if (next == num) {
            // actually num get - 1 here
            return String.valueOf(next);
        } else {
            return String.format("%d->%d", next, num);
        }
    }

    private List<String> method1(int[] nums, int lower, int upper) {
        int n = nums.length;
        List<String> result = new ArrayList<>();
        // use long to avoid extreme case: like [-2147483648,-2147483648,0,2147483647,2147483647], lower = -2147483648, upper = 2147483647
        long prev = (long) lower - 1; // so we can include lower, and avoid nums[i]
        for (int i = 0; i <= n; i++) {
            long curr = i < n ? (long) nums[i] : (long) upper + 1; // same as how we set prev
            if (prev + 2 == curr) {
                result.add(String.valueOf(prev + 1));
            } else if (prev + 2 < curr) {
                result.add(String.valueOf(prev + 1) + "->" + String.valueOf(curr - 1));
            }
            prev = curr;
        }

        return result;
    }

    /*private List<String> mytry(int[] nums, int lower, int upper) {
        int n = nums.length;
        List<String> result = new ArrayList<>();

        if (nums[0] > upper || nums[n - 1] < lower) {
            result.add(String.valueOf(lower));
            result.add("->");
            result.add(String.valueOf(upper));
            return result;
        }

        int i;
        for (i = 0; i < n - 1; i++) {
            if (nums[i] < lower) {
                continue;
            }
            if (nums[i] > upper) {
                return result;
            }
            String s = getString(nums[i], nums[i + 1], lower, upper);
            if (s != null) {
                result.add(s);
            }
        }
        int end = nums[n - 1];
        if (end + 1 == upper){
            result.add(String.valueOf(upper));
        } else if (end + 1 < upper) {
            result.add(String.valueOf(nums[n - 1] + 1) + "->" + String.valueOf(upper));
        }

        return result;
    }
    private String getString(int left, int right, int lower, int upper) {
        if (left + 1 == right) {
            return null;
        }
        if (left + 2 == right) {
            return String.valueOf(left + 1);
        }
        int start = left + 1;
        int end = right - 1;
        String result = String.valueOf(start) + "->" + String.valueOf(end);
        return result;
    }*/
}
