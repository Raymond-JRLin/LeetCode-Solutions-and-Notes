// 609. Find Duplicate File in System
// DescriptionHintsSubmissionsDiscussSolution
// Given a list of directory info including directory path, and all the files with contents in this directory, you need to find out all the groups of duplicate files in the file system in terms of their paths.
//
// A group of duplicate files consists of at least two files that have exactly the same content.
//
// A single directory info string in the input list has the following format:
//
// "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
//
// It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content, f2_content ... fn_content, respectively) in directory root/d1/d2/.../dm. Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.
//
// The output is a list of group of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:
//
// "directory_path/file_name.txt"
//
// Example 1:
//
// Input:
// ["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
// Output:
// [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
//
//
// Note:
//
// No order is required for the final output.
// You may assume the directory name, file name and file content only has letters and digits, and the length of file content is in the range of [1,50].
// The number of files given is in the range of [1,20000].
// You may assume no files or directories share the same name in the same directory.
// You may assume each given directory info represents a unique directory. Directory path and file info are separated by a single blank space.
//
//
// Follow-up beyond contest:
// Imagine you are given a real file system, how will you search files? DFS or BFS?
// If the file content is very large (GB level), how will you modify your solution?
// If you can only read the file by 1kb each time, how will you modify your solution?
// What is the time complexity of your modified solution? What is the most time-consuming part and memory consuming part of it? How to optimize?
// How to make sure the duplicated files you find are not false positive?


class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        if (paths == null || paths.length == 0) {
            return Collections.emptyList();
        }

        return mytry(paths);
    }

    // follow-ups:
    // 1. Imagine you are given a real file system, how will you search files? DFS or BFS ?
    // In general, BFS will use more memory then DFS. However BFS can take advantage of the locality of files in inside directories, and therefore will probably be faster

    // 2. If the file content is very large (GB level), how will you modify your solution?
    // In a real life solution we will not hash the entire file content, since it's not practical. Instead we will first map all the files according to size. Files with different sizes are guaranteed to be different. We will than hash a small part of the files with equal sizes (using MD5 for example). Only if the md5 is the same, we will compare the files byte by byte

    // 3. If you can only read the file by 1kb each time, how will you modify your solution?
    // This won't change the solution. We can create the hash from the 1kb chunks, and then read the entire file if a full byte by byte comparison is required.

    // What is the time complexity of your modified solution? What is the most time consuming part and memory consuming part of it? How to optimize?
    // Time complexity is O(n^2 * k) since in worse case we might need to compare every file to all others. k is the file size

    // How to make sure the duplicated files you find are not false positive?
    // We will use several filters to compare: File size, Hash and byte by byte comparisons.

    // Some more:
    // 1. MD5 is definitely one way to hash a file, another more optimal alternative is to use SHA256. https://stackoverflow.com/questions/14139727/sha-256-or-md5-for-file-integrity

    // 2. Also, to answer this What is the most time consuming part and memory consuming part of it? How to optimize? part:
    // Comparing the file (by size, by hash and eventually byte by byte) is the most time consuming part.
    // Generating hash for every file will be the most memory consuming part.
    // We follow the above procedure will optimize it, since we compare files by size first, only when sizes differ, we'll generate and compare hashes, and only when hashes are the same, we'll compare byte by byte.
    // Also, using better hashing algorithm will also reduce memory/time.
    // https://stackoverflow.com/questions/2722943/is-calculating-an-md5-hash-less-cpu-intensive-than-sha-family-functions

    private List<List<String>> mytry(String[] paths) {
        Map<String, Set<String>> map = new HashMap<>(); // <content, Set<paths>>
        for (String path : paths) {
            String[] strs = path.split(" ");
            String dir = strs[0];
            for (int i = 1; i < strs.length; i++) {
                String s = strs[i];
                int index = s.indexOf("(");
                String name = s.substring(0, index);
                String content = s.substring(index, s.length() - 1);
                Set<String> set = map.getOrDefault(content, new HashSet<>());
                set.add(dir + "/" + name);
                map.put(content, set);
            }
        }
        List<List<String>> result = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) {
                result.add(new ArrayList<>(map.get(key)));
            }
        }
        return result;
    }
}


// class Solution {
//     public List<List<String>> findDuplicate(String[] paths) {
//         List<List<String>> result = new ArrayList<>();
//         if (paths == null || paths.length == 0) {
//             return result;
//         }
//
// //         my try: use HashMap to count every file
//         return my_try(paths);
//     }
//     private List<List<String>> my_try(String[] paths) {
//         Map<String, List<String>> map = new HashMap<>();
//         for (String path : paths) {
//             String[] inputs = path.split(" ");
//             // inputs: {dir, file(content), file(content), ...}
//             String dir = inputs[0];
//             for (int i = 1; i < inputs.length; i++) {
//                 String[] contents = inputs[i].split("\\(");
//                 // contents: {file, content)}
//                 String name = contents[0];
//                 String text = contents[1];
//                 String dirName = dir + "/" + name;
//                 if (map.containsKey(text)) {
//                     List<String> list = map.get(text);
//                     list.add(dirName);
//                     map.put(text, list);
//                 } else {
//                     List<String> list = new ArrayList<>();
//                     list.add(dirName);
//                     map.put(text, list);
//                 }
//             }
//         }
//         List<List<String>> result = new ArrayList<>();
//         for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//             List<String> list = entry.getValue();
//             if (list.size() > 1) {
//                 result.add(list);
//             }
//         }
//         return result;
//     }
// }
