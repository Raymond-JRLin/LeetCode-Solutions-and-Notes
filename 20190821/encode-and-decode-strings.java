// 271. Encode and Decode Strings
// DescriptionHintsSubmissionsDiscussSolution
//
// Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.
//
// Machine 1 (sender) has the function:
//
// string encode(vector<string> strs) {
//   // ... your code
//   return encoded_string;
// }
//
// Machine 2 (receiver) has the function:
//
// vector<string> decode(string s) {
//   //... your code
//   return strs;
// }
//
// So Machine 1 does:
//
// string encoded_string = encode(strs);
//
// and Machine 2 does:
//
// vector<string> strs2 = decode(encoded_string);
//
// strs2 in Machine 2 should be the same as strs in Machine 1.
//
// Implement the encode and decode methods.
//
//
//
// Note:
//
//     The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
//     Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
//     Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


public class Codec {

    // 这道题的重点是： 如何 encode 之后能够找回原来的 string
    // 而不是在于 hash

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        // 把长度放在前面， 因为 1- 不知道长度几位数， 2- 哪怕有数字在 str 里面， 也可以通过长度来找到正确的 string
        for (String str : strs) {
            sb.append(str.length()).append("#").append(str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            // 从 i 开始找第一个遇到的 #
            int pondIndex = s.indexOf('#', i);
            // 从 i 到 # 是后面要还原的 string 的长度
            int len = Integer.parseInt(s.substring(i, pondIndex));
            // 找到原 string
            String ori = s.substring(pondIndex + 1, pondIndex + 1 + len);
            result.add(ori);
            i = pondIndex + 1 + len;
        }
        return result;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
