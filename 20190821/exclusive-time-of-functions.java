// 636. Exclusive Time of Functions
// DescriptionHintsSubmissionsDiscussSolution
//
// On a single threaded CPU, we execute some functions.  Each function has a unique id between 0 and N-1.
//
// We store logs in timestamp order that describe when a function is entered or exited.
//
// Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".  For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3.  "1:end:2" means the function with id 1 ended at the end of timestamp 2.
//
// A function's exclusive time is the number of units of time spent in this function.  Note that this does not include any recursive calls to child functions.
//
// The CPU is single threaded which means that only one function is being executed at a given time unit.
//
// Return the exclusive time of each function, sorted by their function id.
//
//
//
// Example 1:
//
// Input:
// n = 2
// logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
// Output: [3, 4]
// Explanation:
// Function 0 starts at the beginning of time 0, then it executes 2 units of time and reaches the end of time 1.
// Now function 1 starts at the beginning of time 2, executes 4 units of time and ends at time 5.
// Function 0 is running again at the beginning of time 6, and also ends at the end of time 6, thus executing for 1 unit of time.
// So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.
//
//
//
// Note:
//
//     1 <= n <= 100
//     Two functions won't start or end at the same time.
//     Functions will always log when they exit.
//
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {

        // return method1(n, logs);

        return method2(n, logs);
    }

    // 审题一定要清楚啊喂！
    // 这是个 logs input， 所以一定是按照 开始/结束 的顺序！
    // 题目说是 single thread， 所以一个新的 function 开始的时候， 前一个就会暂停
    // 就好比 pair parenthesis， 一个结束了， 离它最近的前一个一定是同一个 id function 的开始
    // 所以一看就是 stack， 理解错误就直接导致纠结了 =。=

    private int[] method2(int n, List<String> logs) {
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        int prevTime = 0;
        for (String s : logs) {
            String[] arr = s.split(":");
            int id = Integer.parseInt(arr[0]);
            String type = arr[1];
            int timestamp = Integer.parseInt(arr[2]);

            if (type.equals("start")) {
                // 新的一个 function 开始
                // 如果前面还有一个未完成的 start
                if (!stack.isEmpty()) {
                    // 加上前一个 start 到现在新的 start 的 time， 注意 start 相当于 time slot 的起始 （左边界）， 所以没有 + 1
                    result[stack.peek()] += timestamp - prevTime;
                }
                stack.push(id); // 把 start 放进 stack
                prevTime = timestamp; // 更新 prevTime 为当前的 time
            } else {
                // end
                // 那么前面一定有一个 start， end 是 time slot 的结束 （右边界）， 所以要 + 1
                result[stack.pop()] += timestamp + 1 - prevTime;
                prevTime = timestamp + 1; // 同样地， timestamp 相当于被这个 end 用掉了， 从下一个 time 的起始 （左边界） 再开始
            }
        }
        return result;
    }

    private int[] method1(int n, List<String> logs) {
        int[] result = new int[n];
        Stack<Log> stack = new Stack<>();
        for (String s : logs) {
            String[] arr = s.split(":");
            Log curr = new Log(arr[0], arr[1], arr[2]);
            if (curr.isStart()) {
                stack.push(curr);
            } else {
                // 碰到 end 来处理 time
                // 前面对应的这个是同一个 id， 把时间加上去
                Log prev = stack.pop();
                int time = curr.timestamp + 1 - prev.timestamp;
                result[curr.id] += time;
                // 因为我们总是碰到 end 才计算， 减去对应 id 的 start time 得到 running time
                // 那么再之前的前一个的 function， 也会同样如此计算
                // 那么对于前一个 id 就要减掉这一段的 running time
                if (!stack.isEmpty()) {
                    result[stack.peek().id] -= time;
                }
            }
        }
        return result;
    }
    private class Log {
        int id;
        String type;
        int timestamp;

        Log(String id, String type, String timestamp) {
            this.id = Integer.parseInt(id);
            this.type = type;
            this.timestamp = Integer.parseInt(timestamp);
        }

        boolean isStart() {
            return type.equals("start");
        }
    }
}
