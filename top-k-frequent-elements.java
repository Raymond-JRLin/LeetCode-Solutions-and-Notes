// 347. Top K Frequent Elements
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-empty array of integers, return the k most frequent elements.
//
// Example 1:
//
// Input: nums = [1,1,1,2,2,3], k = 2
// Output: [1,2]
// Example 2:
//
// Input: nums = [1], k = 1
// Output: [1]
// Note:
//
// You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
// Your algorithm's time complexity must be better than O(n log n), where n is the array's size.


class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

//         method 1: use PQ to sort - O(nlogk) time
        // result = method1_pq(nums, k);

//         method 2: use bucket sort - O(n) time
        result = method2_bucket(nums, k);

        return result;
    }
    private List<Integer> method1_pq(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = getFreq(nums);
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(k, new Comparator<Pair>() {
            public int compare(Pair o1, Pair o2) {
                return o1.freq - o2.freq; // top k freq: min-heap
            }
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int val = entry.getKey();
            int freq = entry.getValue();
            Pair pair = new Pair(val, freq);
            // still need to control the size of pq manually
            if (pq.size() < k) {
                pq.offer(pair);
            } else {
                if (freq > pq.peek().freq) {
                    pq.poll();
                    pq.offer(pair);
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            list.add(curr.val);
        }
        // reverse to get discending order
        for (int i = list.size() - 1; i >= 0; i--) {
            result.add(list.get(i));
        }
        return result;
    }

    private List<Integer> method2_bucket(int[] nums, int k) {
        Map<Integer, Integer> map = getFreq(nums);
        int n = nums.length;
        List<Integer>[] bucket = new List[n + 1];
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (bucket[freq] == null) {
                List<Integer> list = new ArrayList<>();
                list.add(key);
                bucket[freq] = list;
            } else {
                bucket[freq].add(key);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = n; i >= 0 && result.size() < k; i--) {
            if (bucket[i] != null) {
                result.addAll(bucket[i]);
            }
        }
        return result;
    }

    private Map<Integer, Integer> getFreq(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        return map;
    }
}
class Pair{
    public int val;
    public int freq;
    public Pair(int v, int f) {
        val = v;
        freq = f;
    }
}
