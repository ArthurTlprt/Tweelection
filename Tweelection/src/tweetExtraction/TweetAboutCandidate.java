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
import java.text.SimpleDateFormat;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author arthur
 */
public class TweetAboutCandidate {
    
    private static final int hourInMilli = 600000;

    private final String candidateName;

    private Query query;
    private final List<String> dates;
    private final List<String> texts;

    private final ConfigurationBuilder cb;
    private final TwitterFactory tf;
    private final Twitter twitter;

    public TweetAboutCandidate(String candidateName) {
        this.candidateName = candidateName;
        this.query = new Query(candidateName);
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

    public void extractTweetsFromNowToDate(Calendar date) {
        Calendar dateStart = new GregorianCalendar();
        
        int i=1;
        try {
            query.setCount(100);
            QueryResult result = twitter.search(query);

            Calendar currentDate = Calendar.getInstance();
            do {
                query = result.nextQuery();
                result = twitter.search(query);
                for (Status status : result.getTweets()) {
                    texts.add(status.getText());
                    
                    currentDate.setTime(status.getCreatedAt());
                    
                    //System.out.print("difference ");
                    //System.out.println(dateStart.getTimeInMillis() - currentDate.getTimeInMillis());
                    
                    if (dateStart.getTimeInMillis() - currentDate.getTimeInMillis() > i * hourInMilli) {
                        System.out.println("Extraction arrêtée à " + currentDate);
                        writeInFile(currentDate);
                        this.texts.clear();
                        dateStart.setTime(currentDate.getTime());
                        i++;
                    }
                    
                }
            } while (true);
            
        } catch (Exception e) {
            System.err.println("In extractTweet date " + e);
        }
    }


    private void writeInFile(Calendar date) {
        System.out.println(date.toString());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        System.out.println(now);

        try {
            PrintWriter writer = new PrintWriter("tweets_files/" + candidateName + "/" + date.getTime().toString(), "UTF-8");

            for (String text : texts) {
                writer.println(text);
            }

            writer.close();

        } catch (IOException e) {
            System.err.println("Unable to write in the file");
        }
    }

    List<String> getDates() {
        return dates;
    }

    List<String> getTexts() {
        return texts;
    }

}
