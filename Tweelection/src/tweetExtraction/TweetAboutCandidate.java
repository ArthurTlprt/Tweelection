/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetExtraction;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author arthur
 */
public class TweetAboutCandidate {

    private final String candidateName;

    private Query query;
    private List<Status> tweets;
    private List<String> dates;
    private List<String> texts;

    private ConfigurationBuilder cb;
    private TwitterFactory tf;
    private Twitter twitter;

    public TweetAboutCandidate(String candidateName) {
        this.candidateName = candidateName;
        this.query = new Query(candidateName);
        this.tweets = new ArrayList<Status>();
        this.dates = new ArrayList<String>();
        this.texts = new ArrayList<String>();
        
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
            do {
                b += 100;
                query = result.nextQuery();
                result = twitter.search(query);
                for (Status status : result.getTweets()) {
                    //tweets.add(status);
                    dates.add(status.getCreatedAt().toString());
                    texts.add(status.getText());
                }
            } while (result.hasNext() && b < numberOfTweets);
            System.out.println(dates.size());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    public void extractTweetsFromNowToDate(Date date) {
        try {
            query.setCount(100);
            QueryResult result = twitter.search(query);

            Date currentDate = null;
            do {
                query = result.nextQuery();
                result = twitter.search(query);
                for (Status status : result.getTweets()) {
                    //tweets.add(status);
                    dates.add(status.getCreatedAt().toString());
                    texts.add(status.getText());
                    currentDate = status.getCreatedAt();
                }
                if(!result.hasNext()) {
                    System.out.println("Extraction arrêtée à " + currentDate);
                }
            } while (result.hasNext() && date.after(currentDate));
            
            System.out.println(dates.size());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void serialize() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        System.out.println(now);

        try {
            FileOutputStream fout = new FileOutputStream("tweets/" + candidateName + now);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(tweets);
        } catch (FileNotFoundException e) {
            System.out.println("failed to create a file " + candidateName);
        } catch (IOException e) {
            System.err.println("Failed to write in file " + candidateName);
        }
    }

    public void writeInFile() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        System.out.println(now);

        try {
            PrintWriter writerDate = new PrintWriter("tweets_files/" + candidateName + "Date", "UTF-8");
            PrintWriter writerText = new PrintWriter("tweets_files/" + candidateName + "Text", "UTF-8");
            
            for( String date: dates) {
                writerDate.println(date);
            }
            
            for( String text: texts) {
                writerText.println(text);
            }
            
            writerDate.close();
            
        } catch (IOException e) {
            // do something
        }
    }
    
    List<String> getDates() {
        return dates;
    }
    
    List<String> getTexts() {
        return texts;
    }

}
