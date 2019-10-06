// 274. H-Index
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
//
// According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
//
// Example:
//
// Input: citations = [3,0,6,1,5]
// Output: 3
// Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
//              received 3, 0, 6, 1, 5 citations respectively.
//              Since the researcher has 3 papers with at least 3 citations each and the remaining
//              two with no more than 3 citations each, her h-index is 3.
//
// Note: If there are several possible values for h, the maximum one is taken as the h-index.
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }

        // return method1(citations);

        return method2(citations);
    }

    // 我觉得是题目这个 h 要好好理解是什么意思
    // h 篇论文引用都 >= h
    // BS 看 II

    private int method2(int[] citations) {
        // bucket sort
        int n = citations.length;
        int[] bucket = new int[n + 1];
        for (int num : citations) {
            if (num >= n) {
                bucket[n]++;
            } else {
                bucket[num]++;
            }
        }
        int count = 0;
        for (int i = n; i >= 0; i--) {
            count += bucket[i];
            // 同样地， 从后往前找， 是因为要找到可能的最大的 h
            // 此时走过的后面的 bucket[i] 论文篇数也是符合更小的 h 的
            // count = 大于等于当前引用数为 i 的论文的篇数
            if (count >= i) {
                // bucket[i] = 引用数是 i 的论文的篇数， i == n 是指引用数 >= n
                return i;
            }
        }
        return 0;
    }

    private int method1(int[] citations) {
        if (citations.length == 1) {
            return citations[0] >= 1 ? 1 : 0;
        }
        // 对于一个数组来说 nums[i] 就是论文的引用数， 如果从高到低排序之后 i 就是变成个数 i + 1
        // 降序排列是因为 i 变大， 个数变多， 从前往后走过的就是被覆盖的， 应该要降低才可以囊括进去
        // 也就是说， 走到 i 的时候， 前面一共有 i + 1 个 >= nums[i] 的
        List<Integer> list = new ArrayList<>();
        for (int num : citations) {
            list.add(num);
        }
        Collections.sort(list, Collections.reverseOrder());
        int n = list.size();
        for (int i = n - 1; i >= 0; i--) {
            if (list.get(i) >= i + 1) {
                // 注意个数和 index 之间的关系
                // 这里返回的是 index 对应的个数， 因为 nums[i] 代表的论文引用数是可以大于 h 的， 这里求的是 h 这个数， 即多少篇， 所以是个数
                return i + 1;
            }
        }
        return 0;
    }

    private int method1(int[] citations) {
        if (citations.length == 1) {
            return citations[0] >= 1 ? 1 : 0;
        }
        Arrays.sort(citations);
        int n = citations.length;
        for (int i = n - 1; i >= 0; i--) {
            if (list.get(i) >= i + 1) {
                // 注意个数和 index 之间的关系
                // 这里返回的是 index 对应的个数， 因为 nums[i] 代表的论文引用数是可以大于 h 的， 这里求的是 h 这个数， 即多少篇， 所以是个数
                return i + 1;
            }
        }
        return 0;
    }

    // wrong => 二分看 H Index II
    // 正好这里移动错误
    // private int mytry(int[] citations) {
    //     if (citations.length == 1) {
    //         return citations[0] >= 1 ? 1 : 0;
    //     }
    //     int n = citations.length;
    //     Arrays.sort(citations);
    //     int start = 0;
    //     int end = n - 1;
    //     while (start < end) {
    //         int mid = start + (end - start) / 2;
    //         if (n - mid >= citations[mid]) {
    //             start = mid;
    //         } else {
    //             end = mid - 1;
    //         }
    //     }
    //     return citations[start];
    // }
}
