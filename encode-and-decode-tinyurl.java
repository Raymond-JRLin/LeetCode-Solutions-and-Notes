// 535. Encode and Decode TinyURL
// DescriptionHintsSubmissionsDiscussSolution
// Note: This is a companion problem to the System Design problem: Design TinyURL.
// TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
//
// Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
//


public class Codec {

    // mytry:
    String head = "http://tinyurl.com/";
    Map<String, String> longToShort = new HashMap<>(); // <longUrl, shortUrl>
    Map<String, String> shortToLong = new HashMap<>(); // <shortUrl, longUrl>
    String s = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        return mytry_encode(longUrl);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return mytry_decode(shortUrl);
    }

    private String mytry_encode(String longUrl) {
        if (longToShort.containsKey(longUrl)) {
            return longToShort.get(longUrl);
        }
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(62);
            sb.append(s.charAt(index));
        }
        String result = sb.toString();
        if (shortToLong.containsKey(result)) {
            return mytry_encode(longUrl);
        } else {
            longToShort.put(longUrl, result);
            shortToLong.put(result, longUrl);
            return head + result;
        }
    }
    private String mytry_decode(String shortUrl) {
        String target = shortUrl.substring(shortUrl.length() - 6);
        if (!shortToLong.containsKey(target)) {
            return null;
        }
        return shortToLong.get(target);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
