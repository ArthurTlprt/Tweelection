/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweelection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import sentimentanalysis.BagOfWords;
import sentimentanalysis.Review;
import tweetExtraction.TweetAboutCandidate;
import org.knowm.xchart.XYChart;

/**
 *
 * @author gerald
 */
public class Tweelection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {

        TweetAboutCandidate tweetAboutFillon = new TweetAboutCandidate("fillon");
        
        Calendar date = new GregorianCalendar(2017, 04, 26, 9, 0);

        tweetAboutFillon.extractTweetsFromNowToDate(date);

        /*TweetAboutCandidate tweetAboutLepen = new TweetAboutCandidate("Marine");
        tweetAboutLepen.extractTweets(1000);
        tweetAboutLepen.writeInFile();

        TweetAboutCandidate tweetAboutMacron = new TweetAboutCandidate("macron");
        tweetAboutMacron.extractTweets(1000);
        tweetAboutMacron.writeInFile();

        TweetAboutCandidate tweetAboutHamon = new TweetAboutCandidate("hamon");
        tweetAboutHamon.extractTweets(1000);
        tweetAboutHamon.writeInFile();

        TweetAboutCandidate tweetAboutMelanchon = new TweetAboutCandidate("melanchon");
        tweetAboutMelanchon.extractTweets(1000);
        tweetAboutMelanchon.writeInFile();*/
        
        try {
            BagOfWords bog = new BagOfWords();
            bog = new BagOfWords(bog.deserialize());
            //bog.learnReviews("rates.txt", "reviews.txt");
            //bog.learnReviews("rates2.txt", "reviews2.txt");
            //bog = new BagOfWords(bog.deserialize());
            //bog.addModifier("tres", 2);
            //bog.addModifier("pas", -1);
            System.out.println(bog.evaluateAccuracy("rates2.txt", "reviews2.txt"));
            //bog.print(20);
            //bog.load("bog.txt");
            //BagOfWords bog2 = new BagOfWords(bog);
            //bog.print(10);
            //bog2.print(10);
            //Review review = new Review();
            //review.setText("Parce que les étrangers coûtent moins cher que ma femme, je continuerai à voler en toute impunité");
            //review.parseReview();
            //System.out.println(bog.analyzeReview(review));
            //System.out.println(bog.getWordClasse("tres"));
            /*bog.addWord("Bonjour", 3);
            bog.addWord("nul", 0);
            bog.addWord("parfait", 5);
            for(int i = 0; i < 125; i++)
                bog.addWord("capitalisme", 0);
            bog.print(5);*/
            //bog.serialize();
        } catch (Exception e) {
            System.out.println("Ton père le chien");
            //System.out.println(e.getMessage());
        }

    }

}
