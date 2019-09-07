// 841. Keys and Rooms
// DescriptionHintsSubmissionsDiscussSolution
// There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room.
//
// Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.
//
// Initially, all the rooms start locked (except for room 0).
//
// You can walk back and forth between rooms freely.
//
// Return true if and only if you can enter every room.
//
// Example 1:
//
// Input: [[1],[2],[3],[]]
// Output: true
// Explanation:
// We start in room 0, and pick up key 1.
// We then go to room 1, and pick up key 2.
// We then go to room 2, and pick up key 3.
// We then go to room 3.  Since we were able to go to every room, we return true.
// Example 2:
//
// Input: [[1,3],[3,0,1],[2],[0]]
// Output: false
// Explanation: We can't enter the room with number 2.
// Note:
//
// 1 <= rooms.length <= 1000
// 0 <= rooms[i].length <= 1000
// The number of keys in all rooms combined is at most 3000.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms == null || rooms.size() == 0) {
            return true;
        }
        // if (rooms.get(0).size() == 0) {
        //     // no keys to get at the 1st step
        //     return false;
        // }

        return mytry(rooms);
    }

    private boolean mytry(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n]; // visited room
        Set<Integer> keys = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>(); // record the unused keys in our hand
        for (int key : rooms.get(0)) {
            queue.offer(key);
        }
        visited[0] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (visited[curr]) {
                    continue;
                }
                visited[curr] = true;
                for (int key : rooms.get(curr)) {
                    if (visited[key]) {
                        continue;
                    }
                    queue.offer(key);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
