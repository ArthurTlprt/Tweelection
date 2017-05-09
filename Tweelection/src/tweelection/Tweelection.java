/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweelection;

import java.text.ParseException;
import sentimentanalysis.BagOfWords;
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
        /*BagOfWords bog = new BagOfWords();
        bog.addModifier("pas", -1);
        bog.addModifier("très", 2);
        bog.learnReviews("rates.txt", "reviews.txt");
        bog.learnReviews("rates2.txt", "reviews2.txt");
        bog.learnReviews("rates3.txt", "reviews3.txt");
        bog.setFileName("bog.tl");
        bog.serialize();*/
    }

}
