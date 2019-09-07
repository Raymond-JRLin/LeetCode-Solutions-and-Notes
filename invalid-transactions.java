// 1169. Invalid Transactions
// User Accepted: 1409
// User Tried: 2311
// Total Accepted: 1439
// Total Submissions: 7319
// Difficulty: Easy
// A transaction is possibly invalid if:
//
// the amount exceeds $1000, or;
// if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
// Each transaction string transactions[i] consists of comma separated values representing the name, time (in minutes), amount, and city of the transaction.
//
// Given a list of transactions, return a list of transactions that are possibly invalid.  You may return the answer in any order.
//
//
//
// Example 1:
//
// Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
// Output: ["alice,20,800,mtv","alice,50,100,beijing"]
// Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.
// Example 2:
//
// Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
// Output: ["alice,50,1200,mtv"]
// Example 3:
//
// Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
// Output: ["bob,50,1200,mtv"]
//
//
// Constraints:
//
// transactions.length <= 1000
// Each transactions[i] takes the form "{name},{time},{amount},{city}"
// Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
// Each {time} consist of digits, and represent an integer between 0 and 1000.
// Each {amount} consist of digits, and represent an integer between 0 and 2000.


class Solution {
    public List<String> invalidTransactions(String[] transactions) {
        if (transactions == null || transactions.length == 0) {
            return new ArrayList<String>();
        }

        return mytry(transactions);
    }

    private List<String> mytry(String[] transactions) {
        Set<String> set = new HashSet<>();
        Map<String, List<Transaction>> map = new HashMap<>(); // <name, List<Transaction>>
        // Arrays.sort(transactions, new myComparator());
        for (String tran : transactions) {
            Transaction t = new Transaction(tran);
            String name = t.name;
            List<Transaction> list = map.getOrDefault(name, new ArrayList<>());
            list.add(t);
            map.put(name, list);
        }
        for (String name : map.keySet()) {
            List<Transaction> list = map.get(name);
            int n = list.size();
            for (int i = 0; i < n; i++) {
                Transaction curr = list.get(i);
                for (int j = i + 1; j < n; j++) {
                    Transaction next = list.get(j);
                    // 简单的 2 for loop， 因为不能只比较 sort 之后仅相邻的
                    if (Math.abs(curr.time - next.time) <= 60 && !curr.city.equals(next.city)) {
                        set.add(curr.value);
                        set.add(next.value);
                    }
                }
                if (curr.amount > 1000) {
                    set.add(curr.value);
                }
            }
        }
        return new ArrayList<>(set);
    }
    private class Transaction {
        private String name;
        private int time;
        private int amount;
        private String city;
        private String value;

        Transaction(String val) {
            this.value = val;
            String[] arr = val.split(",");
            this.name = arr[0];
            this.time = Integer.parseInt(arr[1]);
            this.amount = Integer.parseInt(arr[2]);
            this.city = arr[3];
        }
    }
    // private class myComparator implements Comparator<String> {
    //     @Override
    //     public int compare(String o1, String o2) {
    //         String[] o1Array = o1.split(",");
    //         String[] o2Array = o2.split(",");
    //         if (!o1Array[0].equals(o2Array[0])) {
    //             return o1Array[0].compareTo(o2Array[0]);
    //         } else if (!o1Array[1].equals(o2Array[1])) {
    //             return Integer.compare(Integer.parseInt(o1Array[1]), Integer.parseInt(o2Array[1]));
    //         } else {
    //             return Integer.compare(Integer.parseInt(o1Array[2]), Integer.parseInt(o2Array[2]));
    //         }
    //     }
    // }
}
