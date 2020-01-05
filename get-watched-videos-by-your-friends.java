// 1311. Get Watched Videos by Your Friends
//
//     User Accepted: 1579
//     User Tried: 2204
//     Total Accepted: 1611
//     Total Submissions: 4585
//     Difficulty: Medium
//
// There are n people, each person has a unique id between 0 and n-1. Given the arrays watchedVideos and friends, where watchedVideos[i] and friends[i] contain the list of watched videos and the list of friends respectively for the person with id = i.
//
// Level 1 of videos are all watched videos by your friends, level 2 of videos are all watched videos by the friends of your friends and so on. In general, the level k of videos are all watched videos by people with the shortest path equal to k with you. Given your id and the level of videos, return the list of videos ordered by their frequencies (increasing). For videos with the same frequency order them alphabetically from least to greatest.
//
//
//
// Example 1:
//
// Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
// Output: ["B","C"]
// Explanation:
// You have id = 0 (green color in the figure) and your friends are (yellow color in the figure):
// Person with id = 1 -> watchedVideos = ["C"]
// Person with id = 2 -> watchedVideos = ["B","C"]
// The frequencies of watchedVideos by your friends are:
// B -> 1
// C -> 2
//
// Example 2:
//
// Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 2
// Output: ["D"]
// Explanation:
// You have id = 0 (green color in the figure) and the only friend of your friends is the person with id = 3 (yellow color in the figure).
//
//
//
// Constraints:
//
//     n == watchedVideos.length == friends.length
//     2 <= n <= 100
//     1 <= watchedVideos[i].length <= 100
//     1 <= watchedVideos[i][j].length <= 8
//     0 <= friends[i].length < n
//     0 <= friends[i][j] < n
//     0 <= id < n
//     1 <= level < n
//     if friends[i] contains j, then friends[j] contains i
//


class Solution {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int f : friends[i]) {
                adj.get(i).add(f);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.offer(id);
        set.add(id);
        while (!queue.isEmpty() && level > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                for (int next : adj.get(curr)) {
                    if (set.contains(next)) {
                        continue;
                    }
                    queue.offer(next);
                    set.add(next);
                }
            }
            level--;
        }
        Map<String, Integer> map = new HashMap<>(); // <video, freq>
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (String v : watchedVideos.get(curr)) {
                map.put(v, map.getOrDefault(v, 0) + 1);
            }
        }
        List<Video> videos = new ArrayList<>();
        for (String s : map.keySet()) {
            videos.add(new Video(s, map.get(s)));
        }
        Collections.sort(videos);
        List<String> result = new ArrayList<>();
        for (Video v : videos) {
            result.add(v.name);
        }
        return result;
    }
    private class Video implements Comparable<Video> {
        String name;
        int freq;

        Video(String n, int f) {
            this.name = n;
            this.freq = f;
        }

        @Override
        public int compareTo(Video o2) {
            if (this.freq == o2.freq) {
                return this.name.compareTo(o2.name);
            } else {
                return Integer.compare(this.freq, o2.freq);
            }
        }
    }
}
