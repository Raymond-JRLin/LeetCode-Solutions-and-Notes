// 355. Design Twitter
// DescriptionHintsSubmissionsDiscussSolution
//
// Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:
//
//     postTweet(userId, tweetId): Compose a new tweet.
//     getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
//     follow(followerId, followeeId): Follower follows a followee.
//     unfollow(followerId, followeeId): Follower unfollows a followee.
//
// Example:
//
// Twitter twitter = new Twitter();
//
// // User 1 posts a new tweet (id = 5).
// twitter.postTweet(1, 5);
//
// // User 1's news feed should return a list with 1 tweet id -> [5].
// twitter.getNewsFeed(1);
//
// // User 1 follows user 2.
// twitter.follow(1, 2);
//
// // User 2 posts a new tweet (id = 6).
// twitter.postTweet(2, 6);
//
// // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
// twitter.getNewsFeed(1);
//
// // User 1 unfollows user 2.
// twitter.unfollow(1, 2);
//
// // User 1's news feed should return a list with 1 tweet id -> [5],
// // since user 1 is no longer following user 2.
// twitter.getNewsFeed(1);
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Twitter {

    private int timestamp;

    private Map<Integer, User> map; // <user id, user object>

    public class Tweet {
        int id;
        int time;
        Tweet next;

        public Tweet(int id) {
            this.id = id;
            this.time = timestamp++;
            this.next = null;
        }
    }

    public class User {
        int id;
        Set<Integer> followings;
        Tweet tweetRoot;

        public User(int id) {
            this.id = id;
            this.followings = new HashSet<>();
            this.tweetRoot = null;
            followings.add(id); // follow self
        }

        public void follow(int userId) {
            followings.add(userId);
        }

        public void unfollow(int userId) {
            followings.remove(userId);
        }

        public void postTweet(int tweetId) {
            Tweet tweet = new Tweet(tweetId);
            tweet.next = tweetRoot;
            tweetRoot = tweet;
        }
    }

    /** Initialize your data structure here. */
    public Twitter() {
        timestamp = 0;
        map = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!map.containsKey(userId)) {
            map.put(userId, new User(userId));
        }
        User user = map.get(userId);
        user.postTweet(tweetId);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        if (!map.containsKey(userId)) {
            return Collections.emptyList();
        }

        Set<Integer> followings = map.get(userId).followings;
        PriorityQueue<Tweet> pq = new PriorityQueue<>((o1, o2) -> (Integer.compare(o2.time, o1.time)));
        for (int followId : followings) {
            Tweet tweet = map.get(followId).tweetRoot;
            if (tweet != null) {
                pq.offer(tweet);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 10 && !pq.isEmpty(); i++) {
            Tweet curr = pq.poll();
            result.add(curr.id);
            if (curr.next != null) {
                pq.offer(curr.next);
            }
        }
        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!map.containsKey(followerId)) {
            User user = new User(followerId);
            map.put(followerId, user);
        }
        if (!map.containsKey(followeeId)) {
            User user = new User(followeeId);
            map.put(followeeId, user);
        }
        map.get(followerId).follow(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (!map.containsKey(followerId) || !map.containsKey(followeeId)) {
            return;
        }
        if (followerId == followeeId) {
            return;
        }
        map.get(followerId).unfollow(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
