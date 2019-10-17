// 379. Design Phone Directory
// DescriptionHintsSubmissionsDiscussSolution
//
// Design a Phone Directory which supports the following operations:
//
//     get: Provide a number which is not assigned to anyone.
//     check: Check if a number is available or not.
//     release: Recycle or release a number.
//
// Example:
//
// // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
// PhoneDirectory directory = new PhoneDirectory(3);
//
// // It can return any available phone number. Here we assume it returns 0.
// directory.get();
//
// // Assume it returns 1.
// directory.get();
//
// // The number 2 is available, so return true.
// directory.check(2);
//
// // It returns 2, the only number that is left.
// directory.get();
//
// // The number 2 is no longer available, so return false.
// directory.check(2);
//
// // Release number 2 back to the pool.
// directory.release(2);
//
// // Number 2 is available again, return true.
// directory.check(2);
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class PhoneDirectory {

    Set<Integer> set;
    Queue<Integer> queue;
    int n;

    // 也可以不用每次去查， 用一个数据结构（比如 queue） 存下所有和 set 来做

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        queue = new LinkedList<>();
        for (int i = 0; i < maxNumbers; i++) {
            queue.offer(i);
        }
        n = maxNumbers;
    }

    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (queue.isEmpty()) {
            return -1;
        }

        int num = queue.poll();
        set.add(num);
        return num;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        if (number < 0 || number >= n) {
            return false;
        }
        return !set.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (number < 0 || number >= n) {
            return;
        }
        if (set.contains(number)) {
            set.remove(number);
            queue.offer(number);
        }
    }
}

// class PhoneDirectory {

//     boolean[] isUsed;
//     int index;
//     int n;

//     // 也可以不用每次去移动 index 查找 next， 用一个数据结构（比如 queue） 存下所有和 set 来做

//     /** Initialize your data structure here
//         @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
//     public PhoneDirectory(int maxNumbers) {
//         n = maxNumbers;
//         isUsed = new boolean[n];
//         index = 0;
//     }

//     /** Provide a number which is not assigned to anyone.
//         @return - Return an available number. Return -1 if none is available. */
//     public int get() {
//         if (index == -1) {
//             return -1;
//         }

//         int num = index;
//         isUsed[index] = true;
//         next();
//         return num;
//     }

//     /** Check if a number is available or not. */
//     public boolean check(int number) {
//         return !isUsed[number];
//     }

//     /** Recycle or release a number. */
//     public void release(int number) {
//         isUsed[number] = false;
//         index = number;
//     }

//     private void next() {
//         int i = 1;
//         for (; i < n; i++) {
//             int p = (index + i) % n;
//             if (!isUsed[p]) {
//                 index = p;
//                 break;
//             }
//         }
//         if (i == n) {
//             index = -1;
//         }
//     }
// }


/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
