// 815. Bus Routes
// DescriptionHintsSubmissionsDiscussSolution
// We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.
//
// We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.
//
// Example:
// Input:
// routes = [[1, 2, 7], [3, 6, 7]]
// S = 1
// T = 6
// Output: 2
// Explanation:
// The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
// Note:
//
// 1 <= routes.length <= 500.
// 1 <= routes[i].length <= 500.
// 0 <= routes[i][j] < 10 ^ 6.


class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (routes == null || routes.length == 0 || routes[0].length == 0) {
            return -1;
        }
        if (S == T) {
            return 0;
        }

        // return method1(routes, S, T);

        return method2(routes, S, T);
    }

    private int method2(int[][] routes, int s, int t) {
        // 1: create a relation 每一个 station 有哪些 bus 经过
        Map<Integer, List<Integer>> stations = new HashMap<>(); // <station, buses>
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                List<Integer> list = stations.getOrDefault(routes[i][j], new ArrayList<Integer>());
                list.add(i);
                stations.put(routes[i][j], list);
            }
        }

        // 2. BFS by stations
        int result = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s); // put stations into queue
        Set<Integer> visitedStation = new HashSet<>();
        Set<Integer> visitedBus = new HashSet<>();
        while (!queue.isEmpty()) {
            result++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll(); // station
                visitedStation.add(curr);
                for (int bus : stations.get(curr)) {
                    // which bus will pass this current station
                    if (visitedBus.contains(bus)) {
                        continue;
                    }
                    visitedBus.add(bus);
                    for (int station : routes[bus]) {
                        // check all stations that this bus can pass by
                        if (station == t) {
                            return result;
                        }
                        if (visitedStation.contains(station)) {
                            continue;
                        }
                        queue.offer(station);
                        visitedStation.add(station);
                    }
                }
            }
        }
        return -1;
    }

    private int method1(int[][] routes, int s, int t) {
        // similar to Bus station in LintCode
        // 1: create a mapping of real station number and our indexes starting from 0
        int buses = routes.length;
        int[] status = new int[buses];
        int count = 0; // count # distinct stations
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> mapping = new HashMap<>(); // <real station number, our index>
        for (int i = 0; i < buses; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                if (!mapping.containsKey(routes[i][j])) {
                    mapping.put(routes[i][j], count++);
                }
                if (routes[i][j] == s) {
                    status[i] |= 1; // 起始 bus
                    queue.offer(i); // 放入的是 bus 而不是 station

                }
                if (routes[i][j] == t) {
                    status[i] |= 2; // 终点 bus
                }
            }
        }

        // 2: create a relation 每一个 station 有哪些 bus 经过
        List<List<Integer>> stations = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            stations.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < buses; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                int index = mapping.get(routes[i][j]);
                stations.get(index).add(i);
            }
        }

        // 3: 记录哪些 bus 可以互相换乘， 即建立图之间的关系
        List<List<Integer>> transfer = new ArrayList<>();
        for (int i = 0; i < buses; i++) {
            transfer.add(new ArrayList<>());
            for (int j = 0; j < buses; j++) {
                transfer.get(i).add(0);
            }
        }
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < stations.get(i).size(); j++) {
                for (int k = j + 1; k < stations.get(i).size(); k++) {
                    int a = stations.get(i).get(j);
                    int b = stations.get(i).get(k);
                    transfer.get(a).set(b, 1);
                    transfer.get(b).set(a, 1);
                }
            }
        }

        // 4. BFS
        int result = 0;
        while (!queue.isEmpty()) {
            result++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if ((status[curr] & 2) != 0) {
                    return result;
                }
                for (int j = 0; j < buses; j++) {
                    if ((status[j] & 1) != 0) {
                        continue;
                    }
                    if (transfer.get(curr).get(j) != 1) {
                        continue;
                    }
                    queue.offer(j);
                    status[j] |= 1;
                }
            }
        }
        return -1;
    }
}
