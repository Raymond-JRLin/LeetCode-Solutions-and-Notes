// 605. Can Place Flowers
// User Accepted: 1048
// User Tried: 1181
// Total Accepted: 1070
// Total Submissions: 4038
// Difficulty: Easy
// Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.
//
// Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
//
// Example 1:
// Input: flowerbed = [1,0,0,0,1], n = 1
// Output: True
// Example 2:
// Input: flowerbed = [1,0,0,0,1], n = 2
// Output: False
// Note:
// The input array won't violate no-adjacent-flowers rule.
// The input array size is in the range of [1, 20000].
// n is a non-negative integer which won't exceed the input array size.


public class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        // return mytry(flowerbed, n);

        // return method2(flowerbed, n);

        return method3(flowerbed, n);
    }

    private boolean method3(int[] flowerbed, int n) {
        // another way
        // count how many 0 positions to be placed
        int count = 1; // starting position flowerbed[0] can place in the very beginning
        int result = 0;
        for (int num : flowerbed) {
            if (num == 0) {
                count++;
            } else {
                // - 1 becuase no-adjacent-flowers rule, e.g. empty position of 3 or 4 can only place 2
                result += (count - 1) / 2;
                count = 0;
            }
        }
        // the end of array, don't need to - 1 because the rightmost flowerbed[len - 1] side can place
        if (count != 0) {
            result += count / 2;
        }
        return result >= n;
    }

    private boolean method2(int[] flowerbed, int n) {
        // simplified version
        int len = flowerbed.length;
		int count = 0;
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 0) {
                int prev = i > 0 ? flowerbed[i - 1] : 0;
                int next = i < len - 1 ? flowerbed[i + 1] : 0;
                if (prev == 0 && next == 0) {
                    count++;
                    flowerbed[i] = 1;
                }
            }
        }
        return count >= n;
    }

    private boolean mytry(int[] flowerbed, int n) {
        int length = flowerbed.length;
		int count = 0;
		// edge case
		if (n == 0) {
			return true;
		}
		// special case
		if (length == 1) {
			return (n == 1 && flowerbed[0] == 0) || (n == 0 && flowerbed[0] == 1);
		}
		// case of head
		if (flowerbed[0] == 0 && flowerbed[1] == 0) {
			flowerbed[0] = 1;
			count++;
		}
		// case in the middle
		for (int i = 1; i < length - 1; i++) {
			if (flowerbed[i - 1] == 0 && flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
				flowerbed[i] = 1;
				count++;
			}
			if (count == n) {
				return true;
			}
		}
		// case of tail
		if (flowerbed[length - 1] == 0 && flowerbed[length - 2] == 0) {
			flowerbed[length - 1] = 1;
			count++;
		}
		if (count == n) {
			return true;
		} else {
			return false;
		}
    }
}
