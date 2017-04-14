/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetExtraction;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author arthur
 */
public class TweetAboutCandidate {

    private final String candidateName;

    private Query query;
    private final List<Status> tweets;

    private ConfigurationBuilder cb;
    private TwitterFactory tf;
    private Twitter twitter;

    public TweetAboutCandidate(String candidateName) {
        this.candidateName = candidateName;
        this.query = new Query(candidateName);
        this.tweets = new ArrayList<Status>();
        cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("ZroXRDzuSrvwL6hYhgizrkQtR")
                .setOAuthConsumerSecret("OpK1ze2cdxM9ImgFdPsuhJaxwJJSld2rPrkjK7AZdDisFeCn1C")
                .setOAuthAccessToken("849890633770815488-wstUiGK2ydWmCcJdpdhjo8mnUBoobrw")
                .setOAuthAccessTokenSecret("TLho2WgfGxtmWNB3o9BLVxqb1rJzJmBLHmbyDFgCNit63");
        tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public void extractTweets(int numberOfTweets) {
        try {
            query.setCount(100);
            QueryResult result = twitter.search(query);

            int b = 0;
            while (result.hasNext() && b < numberOfTweets) {
                b += 100;
                query = result.nextQuery();
                result = twitter.search(query);
                for (Status status : result.getTweets()) {
                    tweets.add(status);
                }
            }
            System.out.println(tweets.size());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void writeInFile(String fileName) {
        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(tweets);
        } catch (FileNotFoundException e) {
            System.out.println("failed to create a file " + fileName);
        } catch (IOException e) {
            System.err.println("Failed to write in file " + fileName);
        }
    }

    public List<Status> getTweets() {
        return this.tweets;
    }

}
