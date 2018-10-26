// 332. Reconstruct Itinerary
// DescriptionHintsSubmissionsDiscussSolution
// Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
//
// Note:
//
// If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
// All airports are represented by three capital letters (IATA code).
// You may assume all tickets form at least one valid itinerary.
// Example 1:
//
// Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
// Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
// Example 2:
//
// Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
// Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
// Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
//              But it is larger in lexical order.



class Solution {
    public List<String> findItinerary(String[][] tickets) {
        if (tickets == null || tickets.length == 0 || tickets[0].length == 0) {
            return Collections.emptyList();
        }

        // return mytry(tickets);

        return method1(tickets);

        // return method2(tickets);
    }

    private List<String> method2(String[][] tickets) {
        // iteration, same duea
        Map<String, PriorityQueue<String>> map = new HashMap<>(); // <from, tos>
        for (String[] ticket : tickets) {
            PriorityQueue<String> pq = map.getOrDefault(ticket[0], new PriorityQueue<>());
            pq.offer(ticket[1]);
            map.put(ticket[0], pq);
        }
        List<String> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            String curr = stack.peek();
            if (!map.containsKey(curr) || map.get(curr).isEmpty()) {
                result.add(0, stack.pop());
            } else {
                stack.push(map.get(curr).poll());
            }
        }
        return result;
    }

    private List<String> method1(String[][] tickets) {
        // Eulerian path, Greedy DFS
        // 由于题目要求解法按字母顺序小的，那么我们考虑用multiset，可以自动排序。等我们图建立好了以后，从节点JFK开始遍历，只要当前节点映射的multiset里有节点，我们取出这个节点，将其在multiset里删掉，然后继续递归遍历这个节点，由于题目中限定了一定会有解，那么等图中所有的multiset中都没有节点的时候，我们把当前节点存入结果中，然后再一层层回溯回去，将当前节点都存入结果，那么最后我们结果中存的顺序和我们需要的相反的，我们最后再翻转一下即可，
        Map<String, PriorityQueue<String>> map = new HashMap<>(); // <from, tos>
        for (String[] ticket : tickets) {
            PriorityQueue<String> pq = map.getOrDefault(ticket[0], new PriorityQueue<>());
            pq.offer(ticket[1]);
            map.put(ticket[0], pq);
        }
        List<String> result = new ArrayList<>();
        String from = "JFK";
        dfs(map, result, from);
        return result;
    }
    private void dfs(Map<String, PriorityQueue<String>> map, List<String> result, String from) {
        while (map.containsKey(from) && !map.get(from).isEmpty()) {
            dfs(map, result, map.get(from).poll());
        }
        result.add(0, from);
    }

    private List<String> mytry(String[][] tickets) {
        // 错了， 因为 ticket 之间是可能有循环的, e.g. [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
        Map<String, List<String>> map = new HashMap<>(); // <from, tos>
        for (String[] ticket : tickets) {
            List<String> list = map.getOrDefault(ticket[0], new ArrayList<>());
            list.add(ticket[1]);
            map.put(ticket[0], list);
        }
        List<String> result = new ArrayList<>();
        String from = "JFK";
        result.add(from);
        while (map.containsKey(from)) {
            List<String> tos = map.get(from);
            if (tos.size() > 1) {
                Collections.sort(tos, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o2.compareTo(o1);
                    }
                });
            }
            from = tos.get(0);
            result.add(from);
        }
        return result;
    }
}
