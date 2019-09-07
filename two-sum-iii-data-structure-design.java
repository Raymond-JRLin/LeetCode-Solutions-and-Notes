// 170. Two Sum III - Data structure design
// DescriptionHintsSubmissionsDiscussSolution
// Design and implement a TwoSum class. It should support the following operations: add and find.
//
// add - Add the number to an internal data structure.
// find - Find if there exists any pair of numbers which sum is equal to the value.
//
// Example 1:
//
// add(1); add(3); add(5);
// find(4) -> true
// find(7) -> false
// Example 2:
//
// add(3); add(1); add(2);
// find(3) -> true
// find(6) -> false
// Seen this question in a real interview before?
// Difficulty:Easy


class TwoSum {

    Map<Integer, Integer> map;
    List<Integer> keys;

    // Thinking: we should do trade-off, like this function may have more add() or find() operation, my answer is based on more-add, so add() is O(1) and find() is O(N). If we wanna do reversely, we can use 2 set to store the number and sum, and pre-calculate sum of each number when we add(), then just search sum set to do find()
    // A improvement is: we keep a list to record all adding number, i.e. keySet of map, it takes less time to iterate a list

    /** Initialize your data structure here. */
    public TwoSum() {
        map = new HashMap<Integer, Integer>(); // <value, freq>
        keys = new ArrayList<Integer>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        // mytryAdd(number);

        method2Add(number);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        // return mytryFind(value);

        return method2Find(value);
    }

    private void method2Add(int number) {
        if (map.containsKey(number)) {
            map.put(number, map.get(number) + 1);
        } else {
            map.put(number, 1);
            keys.add(number);
        }
    }
    private boolean method2Find(int value) {
        for (int key : keys) {
            int target = value - key;
            if (map.containsKey(target)) {
                if (key != target || key == target && map.get(target) > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private void mytryAdd(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }
    private boolean mytryFind(int value) {
        for (int key : map.keySet()) {
            int target = value - key;
            if (map.containsKey(target)) {
                if (key == target) {
                    // we cannot return false here, since even though itself doesn't have 2, but we may find answer in following search, so we should only return true when we found a correct answer during searching process, and return false at the end
                    if (map.get(target) > 1) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
