// 71
// Given an absolute path for a file (Unix-style), simplify it.
//
// For example,
// path = "/home/", => "/home"
// path = "/a/./b/../../c/", => "/c"
// path = "/a/../../b/../c//.//", => "/c"
// path = "/a//b////c/d//././/..", => "/a/b/c"
//
// In a UNIX-style file system, a period ('.') refers to the current directory, so it can be ignored in a simplified path. Additionally, a double period ("..") moves up a directory, so it cancels out whatever the last directory was. For more information, look here: https://en.wikipedia.org/wiki/Path_(computing)#Unix_style
//
// Corner Cases:
//
// Did you consider the case where path = "/../"?
// In this case, you should return "/".
// Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
// In this case, you should ignore redundant slashes and return "/home/foo".


class Solution {
    public String simplifyPath(String path) {
        if (path == null) {
            return path;
        }
        if (path.isEmpty()) {
            return "/";
        }

        return mytry(path);

        // return method1(path);
    }

    private String method1(String path) {
        // 如果一个个查看是否要跳过或者退回， 会比较麻烦， 因为有一个点和两个点， 所以最好是把 path 按照 / split 开， 然后看是否需要加入这个路径
        // ref: https://leetcode.com/problems/simplify-path/discuss/25686/Java-10-lines-solution-with-stack
        Set<String> skip = new HashSet<>(Arrays.asList(".",""));
        Stack<String> stack = new Stack<>();
        for (String curr : path.split("/")) {
            if (curr.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (skip.contains(curr)) {
                continue;
            } else {
                stack.push(curr);
            }
        }
        String result = "";
        while (!stack.isEmpty()) {
            result = "/" + stack.pop() + result;
        }
        return result.equals("") ? "/" : result ;
    }

    private String mytry(String path) {
        int n = path.length();
        Stack<String> stack = new Stack<>();
        // '/' is like a seperator
        String[] arr = path.split("/");
        for (String s : arr) {
            if (s.isEmpty() || s.equals(".")) {
                continue;
            }
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(s);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop()).insert(0, "/");
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }
}
