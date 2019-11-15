// 332. Reconstruct Itinerary
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
//
// Note:
//
//     If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
//     All airports are represented by three capital letters (IATA code).
//     You may assume all tickets form at least one valid itinerary.
//
// Example 1:
//
// Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
// Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
//
// Example 2:
//
// Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
// Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
// Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
//              But it is larger in lexical order.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        if (tickets == null || tickets.size() == 0) {
            return Collections.emptyList();
        }

        // return mytry(tickets);

        return method2(tickets);
    }

    private List<String> method2(List<List<String>> tickets) {
        // 如果用 list 的话， 拿出来的时候要 sort， 而且如果有循环的话， 也不太好解决， 因为要从 list 中删掉
        // 所以考虑这样一种 data structure： 拿出来就从里面删掉了 - stack， queue， pq， dequeue 都可以， 而 pq 还可以 sort
        Map<String, PriorityQueue<String>> adj = new HashMap<>(); // <from, PriorityQueue of tos>
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            PriorityQueue<String> tos = adj.computeIfAbsent(from, dummy -> (new PriorityQueue<>()));
            tos.add(to);
        }
        List<String> result = new ArrayList<>();
        dfs(adj, "JFK", result);
        return result;
    }
    private void dfs(Map<String, PriorityQueue<String>> adj, String from, List<String> result) {
        // 这个不同于正常的 DFS， 是倒过来的
        // 因为我们发现如果按照 DFS 的顺序加进去， 如果到了 dead end， 那就不知道怎么办了
        // 所以当我们走到一个走不下去的地方， 那么就意味着它是最终的终点， 因为题目保证了至少有一个解
        if (!adj.containsKey(from)) {
            result.add(0, from);
            return;
        }
        PriorityQueue<String> pq = adj.get(from);
        while (!pq.isEmpty()) {
            String to = pq.poll();
            dfs(adj, to, result);
        }
        // 走完了 from 的下家， 再加入 from
        result.add(0, from);
    }

    private List<String> mytry(List<List<String>> tickets) {
        Map<String, List<String>> adj = new HashMap<>(); // <from, List of tos>
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            List<String> tos = adj.computeIfAbsent(from, dummy -> (new ArrayList<>()));
            tos.add(to);
        }
        List<String> result = new ArrayList<>();
        int n = tickets.size();
        dfs2(adj, n, "JFK", result);
        return result;
    }
    private boolean dfs2(Map<String, List<String>> adj, int n, String from, List<String> result) {
        result.add(from);
        if (n == 0) {
            return true;
        }
        if (!adj.containsKey(from)) {
            return false;
        }
        List<String> tos = adj.get(from);
        // 如果用 list 的话， 拿出来的时候要 sort
        Collections.sort(tos, (o1, o2) -> (o1.compareTo(o2)));
        for (int i = 0; i < tos.size(); i++) {
            String to = tos.get(i);
            // 记得从 list 中删掉
            tos.remove(i);
            if (dfs2(adj, n - 1, to, result)) {
                return true;
            }
            // 如果有循环的话， 没用完所有的 ticket， 要把这个重新加回去
            tos.add(i, to);
            result.remove(result.size() - 1);
        }
        return false;
    }

}
