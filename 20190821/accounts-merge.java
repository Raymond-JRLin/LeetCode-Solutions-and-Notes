// 721. Accounts Merge
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
//
// Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
//
// After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
//
// Example 1:
//
// Input:
// accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
// Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
// Explanation:
// The first and third John's are the same person as they have the common email "johnsmith@mail.com".
// The second John and Mary are different people as none of their email addresses are used by other accounts.
// We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
// ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
//
// Note:
// The length of accounts will be in the range [1, 1000].
// The length of accounts[i] will be in the range [1, 10].
// The length of accounts[i][j] will be in the range [1, 30].
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null || accounts.size() == 0) {
            return Collections.emptyList();
        }

        // return method1(accounts);

        return method2(accounts);
    }

    // 仔细想一下， 其实就是一个图
    // 1. 同一个账户名字下的 email 是相互连接的
    // 2. 不同账户下的 email 可能是连接的， 从而两个不同的账户的 email 则相互连接

    private List<List<String>> method2(List<List<String>> accounts) {
        // 其实就是要联通相同的 email 及其相连的， 所以可以用 Union Find
        Map<String, String> emails = new HashMap<>(); // <email, account name>
        Map<String, String> parents = new HashMap<>(); // <email, parent email>
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                emails.put(account.get(i), name);
                parents.put(account.get(i), account.get(i)); // point to themselves first
            }
        }
        // find the parent node
        for (List<String> account : accounts) {
            // 同一个 account 下面的本来就是互相连接的， 那么用第一个找到 parent
            String parentEmail = find(account.get(1), parents);
            for (int i = 2; i < account.size(); i++) {
                // 下面每个 email， 都追根溯源
                String root = find(account.get(i), parents);
                // 然后指向这个 parent
                parents.put(root, parentEmail);
            }
        }
        // union
        Map<String, TreeSet<String>> map = new HashMap<>(); // <parent email, children email>
        for (List<String> account : accounts) {
            // 还是一样的， 用第一个找到 parent
            String parentEmail = find(account.get(1), parents);
            TreeSet<String> set = map.computeIfAbsent(parentEmail, dummy -> (new TreeSet<>()));
            // 同一个 account 下面连接的 email 都放入这个 parent 的 set
            // 因为我们之前把同一个 account 下面的 email 都指向了同一个 parent， 所以如果有重复的 email， 也会指向这个 parent email
            for (int i = 1; i < account.size(); i++) {
                set.add(account.get(i));
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (String parentEmail : map.keySet()) {
            List<String> list = new ArrayList<>();
            list.add(emails.get(parentEmail));
            list.addAll(new ArrayList<>(map.get(parentEmail)));
            result.add(list);
        }
        return result;
    }
    private String find(String curr, Map<String, String> emails) {
        if (emails.get(curr) == curr) {
            // 说明 curr 已经是 parent 自己了
            return curr;
        } else {
            // 如果指向了其他的 parent， 一直往下走知道找到 parent node
            return find(emails.get(curr), emails);
        }
    }

    private List<List<String>> method1(List<List<String>> accounts) {
        int n = accounts.size();
        Map<String, Set<String>> adj = new HashMap<>(); // <email, set of neighboring emails> in the same one account
        Map<String, String> map = new HashMap<>(); // <email, account name>
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email1 = account.get(i);
                map.put(email1, name);
                if (i == 1) {
                    continue;
                }
                String email2 = account.get(i - 1);
                Set<String> set1 = adj.computeIfAbsent(email1, dummy -> (new HashSet<>()));
                Set<String> set2 = adj.computeIfAbsent(email2, dummy -> (new HashSet<>()));
                set1.add(email2);
                set2.add(email1);
            }
        }

        Set<String> visited = new HashSet<>();
        List<List<String>> result = new ArrayList<>();
        for (String email : map.keySet()) {
            if (visited.contains(email)) {
                continue;
            }
            List<String> emails = new ArrayList<>();
            dfs(adj, email, visited, emails);
            Collections.sort(emails);
            emails.add(0, map.get(email));
            result.add(emails);
        }
        return result;
    }
    private void dfs(Map<String, Set<String>> adj, String curr, Set<String> visited, List<String> emails) {
        emails.add(curr);
        visited.add(curr);
        if (!adj.containsKey(curr)) {
            return;
        }
        for (String next : adj.get(curr)) {
            if (visited.contains(next)) {
                continue;
            }
            dfs(adj, next, visited, emails);
        }
    }
}
