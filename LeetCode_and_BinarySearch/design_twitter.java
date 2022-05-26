// Problem Question: https://leetcode.com/problems/design-twitter/
class Twitter {
  /*
  My approach:
  1. For having time based tweets, keep a linkedlist and always add in the beginning of this linkedlist.
  2. For saving followings, just use HashMap with key as userid, and value as the set containing all the userids to which this current user follows.
  3. For getting newsfeed, we just iterate the linkedlist of tweets, and then we check if the userid who posted this tweet, is either this current user, or the user in the set of followings of this current user.
  We then keep track, to only take the first 10 valid tweet entries for this current user. 
  
  Space complexity is Tweets linkedlist of size = (Number of tweets * 2) (as we save userid and tweetid both, so multiply the size by 2, here we are using Tweet class).
  
  Space complexity also contains the HashMap entries from user to the followers Set.
  
  Time complexities for each method in my approach:
  1. postTweet: O(1), as we do addBeg in the linkedlist.
  2. getNewsFeed: O(tweets linkedlist size), as we might have to traverse whole tweets list to get 10 most recent valid tweets for this user.
  3. follow and unfollow: O(1) mostly, as we just add or remove to/from HashSet.
  (HashSet is implemented using a hash table. elements are not ordered. So, the add, remove, and contains methods has constant time complexity O(1))
  -----------------------------------------------------------------------------------------
  
  Others' Leetcode somewhat better approach, but could not be understood:
  * Using HashTable + Max Heap --> https://leetcode.com/problems/design-twitter/discuss/2058855/C%2B%2B-0ms-with-clear-explanation
  */
  class Tweet{
    int userId, tweetId;
    
    Tweet(int userId, int tweetId){
      this.userId = userId;
      this.tweetId = tweetId;
    }
  }
  
  LinkedList<Tweet> tweets;
  HashMap<Integer, Set<Integer>> followings;
  
  public Twitter() {
    this.tweets = new LinkedList<>();
    this.followings = new HashMap<>();
  }

  public void postTweet(int userId, int tweetId) {
    Tweet currTweet = new Tweet(userId, tweetId);
    this.tweets.addFirst(currTweet);
  }

  public List<Integer> getNewsFeed(int userId) {
    List<Integer> newsFeed = new ArrayList<>();
    
    Set<Integer> currFollows;
    
    if(followings.containsKey(userId)){
      currFollows = followings.get(userId);
    }else{
      currFollows = new HashSet<>();
    }
    
    for(Tweet currTweet: tweets){
      if(newsFeed.size() >= 10){
        // if size of the newsfeed is >= 10, then we already have the 10 elements and then we break from this loop
        break;
      }
      
      if(currTweet.userId == userId || currFollows.contains(currTweet.userId)){
        newsFeed.add(currTweet.tweetId);
      }
    }
    
    return newsFeed;
  }

  public void follow(int followerId, int followeeId) {
    if(followings.containsKey(followerId)){
      followings.get(followerId).add(followeeId);
    }else{
      Set<Integer> currFollows = new HashSet<>();
      currFollows.add(followeeId);
      followings.put(followerId, currFollows);
    }
  }

  public void unfollow(int followerId, int followeeId) {
    if(followings.containsKey(followerId)){
      followings.get(followerId).remove(Integer.valueOf(followeeId));
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