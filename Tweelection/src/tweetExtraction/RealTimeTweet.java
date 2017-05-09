/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetExtraction;

import java.util.ArrayList;
import java.util.Calendar;
import tweetAnalyze.TweetAnalyze;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Cette classe servira à récupérer les tweets
 * Elle les récupère selon l'intervalle fixé 
 */
public class RealTimeTweet {

    private final String[] candidate;
    private final Query[] query;
    private final ArrayList<String>[] texts;

    private final ConfigurationBuilder cb;
    private final TwitterFactory tf;
    private final Twitter twitter;

    private final long interval;

    private Calendar momentBegin;
    
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

        /* Configure l'api pour utiliser le compte associé */ 
        cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("ZroXRDzuSrvwL6hYhgizrkQtR")
                .setOAuthConsumerSecret("OpK1ze2cdxM9ImgFdPsuhJaxwJJSld2rPrkjK7AZdDisFeCn1C")
                .setOAuthAccessToken("849890633770815488-wstUiGK2ydWmCcJdpdhjo8mnUBoobrw")
                .setOAuthAccessTokenSecret("TLho2WgfGxtmWNB3o9BLVxqb1rJzJmBLHmbyDFgCNit63");
        tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();

        /* Fixe l'interval pour limiter le nombre de requete (eviter erreur api) */
        interval = 15 * 60 * candidate.length * 1000 / 180;

    }

    public void setMomentBegin() { momentBegin = Calendar.getInstance(); }
    public void setMomentBegin(Calendar x) { momentBegin = x; }
    
    public Calendar getMomentBegin() { return momentBegin; }
    public String[] getCandidates() { return candidate; }
    public ArrayList<String>[] getTexts() { return texts; }
    public ArrayList<String> getTexts(int index) { return texts[index]; }
    public int getLength() { return candidate.length; }
    public long getInterval() { return interval; }
    
    public void extractTweets() {
        try {
            for (int i = 0; i < candidate.length; i++) {
                this.query[i] = new Query(candidate[i]);
                this.query[i].setCount(100);
                QueryResult result;
                result = twitter.search(this.query[i]);

                ArrayList<String> x = new ArrayList<>();
                x.add(this.candidate[i]);
                for (Status status : result.getTweets()) {
                    if (status.getCreatedAt().getTime() < momentBegin.getTimeInMillis()) {
                        if (momentBegin.getTimeInMillis() - (interval + 10000) > status.getCreatedAt().getTime()) {
                            x.add(status.getText());
                            String s = status.getText();
                        }
                    }
                }
                this.texts[i] = x;
            }
        } catch (TwitterException e) {
            System.err.println("In extractTweet date " + e);
        }
    }
}
