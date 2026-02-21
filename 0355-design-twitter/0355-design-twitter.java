class Twitter {

    private int time = 0;

    private Map<Integer, Set<Integer>> followMap; // user - people they follow

    private Map<Integer, List<Tweet>> tweetMap; // user - their tweets

    private class Tweet {
        int id;
        int time;

        Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    public Twitter() {
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        tweetMap.putIfAbsent(userId, new ArrayList<>());
        tweetMap.get(userId).add(new Tweet(tweetId, time++));
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();

        //Max heap sort by time descending
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // ensure user follows himself
        followMap.putIfAbsent(userId, new HashSet<>());
        followMap.get(userId).add(userId);

        // Push latest tweet of each followed user
        for (int user : followMap.get(userId)) {

            if (tweetMap.containsKey(user)) {

                List<Tweet> tweets = tweetMap.get(user);
                int lastIndex = tweets.size() - 1;

                Tweet t = tweets.get(lastIndex);

                // [time, tweetId, userId, index]
                maxHeap.offer(new int[] { t.time, t.id, user, lastIndex });
            }
        }
        // Extract top 10 tweets
        while (!maxHeap.isEmpty() && result.size() < 10) {

            int[] curr = maxHeap.poll();

            int tweetId = curr[1];
            int user = curr[2];
            int index = curr[3];

            result.add(tweetId);

            // Move to previous tweet of same user
            if (index > 0) {
                Tweet prev = tweetMap.get(user).get(index - 1);
                maxHeap.offer(new int[] { prev.time, prev.id, user, index - 1 });
            }
        }

        return result;
    }

    public void follow(int followerId, int followeeId) {
        followMap.putIfAbsent(followerId, new HashSet<>());
        followMap.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).remove(followeeId);
        }
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