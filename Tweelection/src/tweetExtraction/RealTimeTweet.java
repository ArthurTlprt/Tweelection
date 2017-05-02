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
    
    
    
    public RealTimeTweet(String name1, String name2){
       this.candidate = new String[2];
       this.candidate[0]=name1;
       this.candidate[1]=name2;
       this.query = new Query[2];
       this.query[0] = new Query(candidate[0]);
       this.query[1] = new Query(candidate[1]);
       this.texts = new ArrayList[2];
       

        cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("ZroXRDzuSrvwL6hYhgizrkQtR")
                .setOAuthConsumerSecret("OpK1ze2cdxM9ImgFdPsuhJaxwJJSld2rPrkjK7AZdDisFeCn1C")
                .setOAuthAccessToken("849890633770815488-wstUiGK2ydWmCcJdpdhjo8mnUBoobrw")
                .setOAuthAccessTokenSecret("TLho2WgfGxtmWNB3o9BLVxqb1rJzJmBLHmbyDFgCNit63");
        tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
        //extractTweets();
        startExtraction();
    }
    
    private void extractTweets() {
        try {
            for(int i=0;i<2;i++){
                this.query[i]=new Query(candidate[i]);
                this.query[i].setCount(100);
                QueryResult result = null;
                result = twitter.search(this.query[i]);
                int j=0;
                ArrayList<String> x=new ArrayList<String>();
                x.add(this.candidate[i]);
                for (Status status : result.getTweets()) {
                    x.add(status.getText());
                    //this.texts[i].add(status.getText());
                    String s=status.getText();
                    System.out.println(s);
                    j++;
                }
                this.texts[i]=x;
                System.out.println(j);
            }
        } catch (Exception e) {
            System.err.println("In extractTweet date " + e);
        }
    }
    
    
    public void startExtraction(){
        Calendar x1=Calendar.getInstance();
        int a=0;
        System.out.println(a);
        extractTweets();
        while(true){
            Calendar x2=Calendar.getInstance();
            while(x2.getTimeInMillis()-x1.getTimeInMillis()>10000){
                a++;
                System.out.println(a);
                x1=x2;
                extractTweets();
                
            }
        }
    }
}



