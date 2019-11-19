// 635. Design Log Storage System
// DescriptionHintsSubmissionsDiscussSolution
//
// You are given several logs that each log contains a unique id and timestamp. Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.
//
// Design a log storage system to implement the following functions:
//
// void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.
//
// int[] Retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the range from start to end. Start and end all have the same format as timestamp. However, granularity means the time level for consideration. For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.
//
// Example 1:
//
// put(1, "2017:01:01:23:59:59");
// put(2, "2017:01:01:22:59:59");
// put(3, "2016:01:01:00:00:00");
// retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
// retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
//
// Note:
//
//     There will be at most 300 operations of Put or Retrieve.
//     Year ranges from [2000,2017]. Hour ranges from [00,23].
//     Output for Retrieve has no order required.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class LogSystem {

    // 不能把 timestamp string 分成 7 个 granularity 来分开记录
    // 要注意 granularity 是指精度， 比如 granularity 是 hour， 那么前面 年月日 也同样得在范围内， 只是最后只比较到 时

    // Map<String, Integer> map; // <timestamp, id>
    Map<String, Integer> indices; // <gran, index>

    // method2
    TreeMap<String, Integer> map;
    String min;
    String max;

    public LogSystem() {
        // map = new HashMap<>();

        indices = new HashMap<>();
        indices.put("Year", 4);
        indices.put("Month", 7);
        indices.put("Day", 10);
        indices.put("Hour", 13);
        indices.put("Minute", 16);
        indices.put("Second", 19);

        // method2
        map = new TreeMap<>();
        min = "2000:00:00:00:00:00";
        max = "2017:12:31:23:59:59";
    }

    public void put(int id, String timestamp) {
        map.put(timestamp, id);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> result = new ArrayList<>();
        int index = indices.get(gra);

        // 比较的时候， 从 0 一直到 granularity 那里
        // String start = s.substring(0, index);
        // String end = e.substring(0, index);
        // for (String key : map.keySet()) {
        //     String curr = key.substring(0, index);
        //     if (curr.compareTo(start) >= 0 && curr.compareTo(end) <= 0) {
        //         result.add(map.get(key));
        //     }
        // }

        // method2
        String start = s.substring(0, index) + min.substring(index);
        String end = e.substring(0, index) + max.substring(index);
        Map<String, Integer> sub = map.subMap(start, true, end, true);
        for (String key : sub.keySet()) {
            result.add(sub.get(key));
        }
        return result;
    }
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */
