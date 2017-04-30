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
import java.text.ParseException;
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

    public void extractThisDay(String day) throws ParseException {

        Calendar dateStart = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("y-M-d");
        dateStart.setTime(sdf.parse(day));
        Calendar currentDate = Calendar.getInstance();

        try {
            query.setCount(100);
            query.setUntil(day);
            QueryResult result = twitter.search(query);

            
            for (int i = 0; i < 2; i++) {
                query = result.nextQuery();
                result = twitter.search(query);
                for (Status status : result.getTweets()) {
                    texts.add(status.getText());
                    currentDate.setTime(status.getCreatedAt());
                }
            }
            System.out.println("Extraction arrêtée à " + currentDate);
            writeInFile(day);
            this.texts.clear();

        } catch (Exception e) {
            System.err.println("In extractTweet date " + e);
        }
    }

    public void extractTweetsFromNowToDate(Calendar date) {
        Calendar dateStart = new GregorianCalendar();

        int i = 1;
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

                    if (dateStart.getTimeInMillis() - currentDate.getTimeInMillis() > i * hourInMilli) {
                        System.out.println("Extraction arrêtée à " + currentDate);
                        writeInFile(currentDate.toString());
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

    private void writeInFile(String date) {
        String path = "tweets_files/" + candidateName + "/" + date;
        try {
            PrintWriter writer = new PrintWriter(path, "UTF-8");

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
