// 744. Find Smallest Letter Greater Than Target
// DescriptionHintsSubmissionsDiscussSolution
// Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.
//
// Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
//
// Examples:
// Input:
// letters = ["c", "f", "j"]
// target = "a"
// Output: "c"
//
// Input:
// letters = ["c", "f", "j"]
// target = "c"
// Output: "f"
//
// Input:
// letters = ["c", "f", "j"]
// target = "d"
// Output: "f"
//
// Input:
// letters = ["c", "f", "j"]
// target = "g"
// Output: "j"
//
// Input:
// letters = ["c", "f", "j"]
// target = "j"
// Output: "c"
//
// Input:
// letters = ["c", "f", "j"]
// target = "k"
// Output: "c"
// Note:
// letters has a length in range [2, 10000].
// letters consists of lowercase letters, and contains at least 2 unique letters.
// target is a lowercase letter.
// Seen this question in a real interview before?
// Difficulty:Easy


class Solution {
    public char nextGreatestLetter(char[] letters, char target) {

        // return my_try(letters, target);

        // return method2_pq(letters, target);

        return method3_bs(letters, target);
    }

    private char method3_bs(char[] letters, char target) {
        int n = letters.length;
        int start = 0;
        int end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (letters[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (letters[start] > target) {
            return letters[start];
        } else if (letters[end] > target) {
            return letters[end];
        } else {
            return letters[0];
        }
    }

    private char method2_pq(char[] letters, char target) {
        PriorityQueue<Character> pq = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o1 - o2;
            }
        });
        for (char c : letters) {
            pq.offer(c);
        }
        // 它是 wrap around 的， 所以要先记住当前最小的， 然后从 target 往后找， 有就有， 没有就是回来最小的
        char smallest = pq.peek();
        while (!pq.isEmpty() && pq.peek() <= target) {
            pq.poll();
        }
        if (pq.isEmpty()) {
            return smallest;
        } else {
            return pq.poll();
        }
    }
    // private char my_try(char[] letters, char target) {
    //     char smallest = 'z';
    //     int[] values = new int[26];
    //     for (char c : letters) {
    //         values[c - 'a']++;
    //         if (c < smallest) {
    //             smallest = c;
    //         }
    //     }
    //     // for (int i = 0; i < target - 'a'; i++) {
    //     //     if (values[i] > 0) {
    //     //         smallest = (char) i + 'a';
    //     //         break;
    //     //     }
    //     // }
    //     // 它是 wrap around 的， 所以要先记住当前最小的， 然后从 target 往后找， 有就有， 没有就是回来最小的
    //     for (int i = target - 'a' + 1; i < 26; i++) {
    //         if (values[i] > 0) {
    //             return (char) 'a' + i;
    //         }
    //     }
    //     return smallest;
    // }
}
