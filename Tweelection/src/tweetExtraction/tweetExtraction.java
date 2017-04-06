/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetExtraction;

import java.util.List;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author arthur
 */
public class tweetExtraction {

    public static void main(String[] args) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
        .setOAuthConsumerKey("ZroXRDzuSrvwL6hYhgizrkQtR")
        .setOAuthConsumerSecret("OpK1ze2cdxM9ImgFdPsuhJaxwJJSld2rPrkjK7AZdDisFeCn1C")
        .setOAuthAccessToken("849890633770815488-wstUiGK2ydWmCcJdpdhjo8mnUBoobrw")
        .setOAuthAccessTokenSecret("TLho2WgfGxtmWNB3o9BLVxqb1rJzJmBLHmbyDFgCNit63");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        //Twitter twitter = TwitterFactory.getSingleton();
        List<Status> statuses;
        try {
            /*statuses = twitter.getHomeTimeline();
            System.out.println("Showing home timeline.");
            for (Status status : statuses) {
                System.out.println(status.getUser().getName() + ":"
                        + status.getText());
            }*/
            Query query = new Query("fillon");
            QueryResult result = twitter.search(query);
            int b= 0;
            for (Status status : result.getTweets()) {
                b+=1;
                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
            }
            System.out.println(b);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
