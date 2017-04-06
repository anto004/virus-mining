package acquisition;

import com.mongodb.MongoClient;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.util.*;


public class App {
    private static final String TWITTER_CONSUMER_KEY = "7Q15wbLp77kwJsw966xfi7bK5";
    private static final String TWITTER_CONSUMER_SECRET = "PJ98u45w13H7EsZTGQaSC22s5yudqlJMxehzXpJNxeOYyltBmn";
    private static final String TWITTER_ACCESS_TOKEN = "850111215187537920-CvUipsrrawaNpWFEcgmqqHXLXSXE3zI";
    private static final String TWITTER_ACCESS_TOKEN_SECRET = "TvvRKr9trjK28mta8ZnxZwIHmDEjciPKVTt9Sl03iiBW3";
    private static void getTweets() throws TwitterException{
        ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true).setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
                    .setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET)
                    .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
                    .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);
        
        TwitterFactory twitterFactory = new TwitterFactory(cb.build());
        Twitter twitter = twitterFactory.getInstance();
        try {
            Query query = new Query("Dennis");
            QueryResult result;
            int noOfTweets = 5;
            int tweetCount = 0;
            do{
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets)
                    System.out.println("User: " + tweet.getUser().getScreenName() + " - " + tweet.getText());
                tweetCount++;
            }while(result.hasNext() && tweetCount < noOfTweets);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
    }

    private static void getData(String collectionName){
        MongoClient mongoClient = new MongoClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("zika");
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

        MongoCursor<Document> cursor = collection.find().iterator();

        while(cursor.hasNext()){
            System.out.println(cursor.next().toJson());
        }

    }

    public static void main(String[] args) throws TwitterException{
        //getData("Argentina");
        
        getTweets();

    }
}
