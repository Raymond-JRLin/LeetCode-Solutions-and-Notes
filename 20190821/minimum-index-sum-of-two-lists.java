// 599. Minimum Index Sum of Two Lists
// DescriptionHintsSubmissionsDiscussSolution
//
// Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.
//
// You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.
//
// Example 1:
//
// Input:
// ["Shogun", "Tapioca Express", "Burger King", "KFC"]
// ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
// Output: ["Shogun"]
// Explanation: The only restaurant they both like is "Shogun".
//
// Example 2:
//
// Input:
// ["Shogun", "Tapioca Express", "Burger King", "KFC"]
// ["KFC", "Shogun", "Burger King"]
// Output: ["Shogun"]
// Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
//
// Note:
//
//     The length of both lists will be in the range of [1, 1000].
//     The length of strings in both lists will be in the range of [1, 30].
//     The index is starting from 0 to the list length minus 1.
//     No duplicates in both lists.
//
// Seen this question in a real interview before?
//
//     Difficulty:Easy


class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        if (list1 == null || list1.length == 0 || list2 == null || list2.length == 0) {
            return new String[0];
        }

        return mytry(list1, list2);
    }

    private String[] mytry(String[] l1, String[] l2) {
        int n = l1.length;
        int m = l2.length;
        Map<String, Integer> m1 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            m1.put(l1[i], i);
        }
        int min = n + m;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (m1.containsKey(l2[i])) {
                String s = l2[i];
                int sum = i + m1.get(s);
                if (sum == min) {
                    list.add(s);
                } else if (sum < min) {
                    list = new ArrayList<>();
                    list.add(s);
                    min = sum;
                }
            }
        }
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
