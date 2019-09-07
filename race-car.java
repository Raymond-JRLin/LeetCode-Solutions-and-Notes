// 818. Race Car
// DescriptionHintsSubmissionsDiscussSolution
// Your car starts at position 0 and speed +1 on an infinite number line.  (Your car can go into negative positions.)
//
// Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).
//
// When you get an instruction "A", your car does the following: position += speed, speed *= 2.
//
// When you get an instruction "R", your car does the following: if your speed is positive then speed = -1 , otherwise speed = 1.  (Your position stays the same.)
//
// For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.
//
// Now for some target position, say the length of the shortest sequence of instructions to get there.
//
// Example 1:
// Input:
// target = 3
// Output: 2
// Explanation:
// The shortest instruction sequence is "AA".
// Your position goes from 0->1->3.
// Example 2:
// Input:
// target = 6
// Output: 5
// Explanation:
// The shortest instruction sequence is "AAARA".
// Your position goes from 0->1->3->7->7->6.
//
//
// Note:
//
// 1 <= target <= 10000.
// Seen this question in a real interview before?
// Difficulty:Hard


class Solution {
    public int racecar(int target) {
        return method1(target);
    }

    private int method1(int target) {
        // BFS: find the shortest path
        Car start = new Car(0, 1);
        Queue<Car> queue = new LinkedList<>();
        queue.offer(start);
        Set<Integer> set = new HashSet<>();
        set.add(getHash(0, 1));
        int result = 0;
        while (!queue.isEmpty()) {
            result++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Car curr = queue.poll();
                int nextPos1 = curr.pos + curr.speed; // A
                int nextSpe1 = curr.speed * 2;
                int nextPos2 = curr.pos; // R
                int nextSpe2 = curr.speed > 0 ? -1 : 1;
                if (nextPos1 == target || nextPos2 == target) {
                    return result;
                }
                // we cannot do continue here, because we can go 2 directions on the same node
                if (Math.abs(nextPos1 - target) <= target && !set.contains(getHash(nextPos1, nextSpe1))) {
                    queue.offer(new Car(nextPos1, nextSpe1));
                    set.add(getHash(nextPos1, nextSpe1));
                }
                if (Math.abs(nextPos2 - target) <= target && !set.contains(getHash(nextPos2, nextSpe2))) {
                    queue.offer(new Car(nextPos2, nextSpe2));
                    set.add(getHash(nextPos2, nextSpe2));
                }
            }
        }
        return -1;
    }
    private int getHash(int pos, int speed) {
        return pos * 12345 + speed;
    }
    private class Car {
        public int pos;
        public int speed;
        public Car(int p, int s) {
            pos = p;
            speed = s;
        }
    }
}
