/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetExtraction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import tweetAnalyze.tweetAnalyze;
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
public class RealTimeTweet {

    private String[] candidate;
    private Query[] query;
    private ArrayList<String>[] texts;

    private final ConfigurationBuilder cb;
    private final TwitterFactory tf;
    private final Twitter twitter;

    private long interval;

    private Calendar momentBegin;

    private tweetAnalyze ta;
    
    public RealTimeTweet(ArrayList<String> names) {
        int size = names.size();

        this.candidate = new String[size];
        for (int i = 0; i < size; i++) {
            this.candidate[i] = names.get(i);
        }

        this.query = new Query[size];
        for (int i = 0; i < size; i++) {
            this.query[i] = new Query(candidate[i]);
        }

        this.texts = new ArrayList[size];

        cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("ZroXRDzuSrvwL6hYhgizrkQtR")
                .setOAuthConsumerSecret("OpK1ze2cdxM9ImgFdPsuhJaxwJJSld2rPrkjK7AZdDisFeCn1C")
                .setOAuthAccessToken("849890633770815488-wstUiGK2ydWmCcJdpdhjo8mnUBoobrw")
                .setOAuthAccessTokenSecret("TLho2WgfGxtmWNB3o9BLVxqb1rJzJmBLHmbyDFgCNit63");
        tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
        //extractTweets();

        // fixe l'interval pour limiter le nombre de requete (eviter erreur api)
        interval = 15 * 60 * candidate.length * 1000 / 180;

        //startExtraction();
    }

    public void setTweetAnalyze(tweetAnalyze ta) { this.ta = ta; }
    public void setMomentBegin() { momentBegin = Calendar.getInstance(); }
    public void setMomentBegin(Calendar x) { momentBegin = x; }
    
    public Calendar getMomentBegin() { return momentBegin; }
    public String[] getCandidates() { return candidate; }
    public ArrayList<String>[] getTexts() { return texts; }
    public ArrayList<String> getTexts(int index) { return texts[index]; }
    public long getInterval() { return interval; }
    
    public void extractTweets() {
        try {
            for (int i = 0; i < candidate.length; i++) {
                this.query[i] = new Query(candidate[i]);
                this.query[i].setCount(100);
                QueryResult result = null;
                result = twitter.search(this.query[i]);

                int j = 0;
                ArrayList<String> x = new ArrayList<String>();
                x.add(this.candidate[i]);
                int b = 0;
                for (Status status : result.getTweets()) {
                    if (status.getCreatedAt().getTime() < momentBegin.getTimeInMillis()) {
                        if (momentBegin.getTimeInMillis() - (interval + 10000) > status.getCreatedAt().getTime()) {
                            b++;
                            x.add(status.getText());
                            //this.texts[i].add(status.getText());
                            String s = status.getText();
                            j++;
                        }
                    }
                }
                System.out.println(b);
                this.texts[i] = x;
                //System.out.println(j);
            }
        } catch (Exception e) {
            System.err.println("In extractTweet date " + e);
        }
    }

    public void startExtraction() {
        momentBegin = Calendar.getInstance();
        int a = 0;
        //System.out.println(a);
        extractTweets();
        //tweetAnalyze ta = new tweetAnalyze();
        ta.setRealTime(true);
        for (String candidate1 : candidate) {
            ta.addSubject(candidate1);
        }
        for (int i = 0; i < texts.length; i++) {
            ta.launchAnalyzeRealTime(texts[i]);
        }
        ta.displayGraph();

        while (true) {
            Calendar x2 = Calendar.getInstance();
            while (x2.getTimeInMillis() - momentBegin.getTimeInMillis() > interval) {
                a++;
                System.out.println(a);
                momentBegin = x2;
                extractTweets();
                for (int i = 0; i < texts.length; i++) {
                    ta.launchAnalyzeRealTime(texts[i]);
                }

                ta.refreshGraph();
                //ta.save();
            }
        }
    }
}
