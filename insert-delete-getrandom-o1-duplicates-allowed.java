// 381. Insert Delete GetRandom O(1) - Duplicates allowed
// DescriptionHintsSubmissionsDiscussSolution
// Design a data structure that supports all following operations in average O(1) time.
//
// Note: Duplicate elements are allowed.
// insert(val): Inserts an item val to the collection.
// remove(val): Removes an item val from the collection if present.
// getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
// Example:
//
// // Init an empty collection.
// RandomizedCollection collection = new RandomizedCollection();
//
// // Inserts 1 to the collection. Returns true as the collection did not contain 1.
// collection.insert(1);
//
// // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
// collection.insert(1);
//
// // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
// collection.insert(2);
//
// // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
// collection.getRandom();
//
// // Removes 1 from the collection, returns true. Collection now contains [1,2].
// collection.remove(1);
//
// // getRandom should return 1 and 2 both equally likely.
// collection.getRandom();
// Seen this question in a real interview before?
// Difficulty:Hard


class RandomizedCollection {

    List<Integer> list;
    Map<Integer, Set<Integer>> map;
    Random rand;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        list = new LinkedList<>();
        map = new HashMap<>(); // <val, set of index>
        rand = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean already = map.containsKey(val);
        if (!already) {
            map.put(val, new LinkedHashSet<Integer>());
        }
        map.get(val).add(list.size());
        list.add(val);
        return !already;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val).iterator().next();
        map.get(val).remove(index); // 这条命令要放在 if 前面， 是因为本题允许 duplicates， 如果 index 拿到的数恰恰又是要删除的 val， 这里 remove 掉的 index 会在 if 里面再加回来， 但是如果放在后面， 那么 if 里因为是 set 则加不进去， 然后被删掉， 也就意味着同一个 val 会被删除两个 index
        if (index < list.size() - 1) {
            int num = list.get(list.size() - 1);
            list.set(index, num);
            map.get(num).remove(list.size() - 1);
            map.get(num).add(index);
        }
        list.remove(list.size() - 1);
        if (map.get(val).isEmpty()) {
            map.remove(val);
        }
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        int index = rand.nextInt(list.size());
        return list.get(index);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
