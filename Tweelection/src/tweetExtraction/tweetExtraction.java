/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetExtraction;

import java.util.List;
import twitter4j.*;

/**
 *
 * @author arthur
 */
public class tweetExtraction {

    public static void main(String[] args) {
        Twitter twitter = TwitterFactory.getSingleton();
        List<Status> statuses;
        try {
            statuses = twitter.getHomeTimeline();
            System.out.println("Showing home timeline.");
            for (Status status : statuses) {
                System.out.println(status.getUser().getName() + ":"
                        + status.getText());
            }
        } catch (Exception e) {
            System.out.println("merde");
        }
    }

}
