/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweelection;

import java.text.ParseException;
import tweetAnalyze.TweetAnalyze;

/**
 * Calls an instance of TweetAnaluze class
 */
public class Tweelection {
    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws ParseException, InterruptedException {
        TweetAnalyze ta = new TweetAnalyze();
    }

}
