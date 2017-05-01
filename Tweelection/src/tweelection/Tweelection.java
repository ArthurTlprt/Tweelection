/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweelection;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
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

    private static List<String> period;

    private static void setPeriod(String str_startDate, String str_endDate) throws ParseException {
        period = new ArrayList<String>();

        DateFormat formatter;

        formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = (Date) formatter.parse(str_startDate);
        Date endDate = (Date) formatter.parse(str_endDate);
        long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
        long endTime = endDate.getTime(); // create your endtime here, possibly using Calendar or Date
        long curTime = startDate.getTime();
        while (curTime <= endTime) {
            period.add(formatter.format(curTime));
            curTime += interval;
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, InterruptedException {

        setPeriod("2017-04-22", "2017-04-30");


        //on peut faire ça pour chaque candidat maintenant

        TweetAboutCandidate tweetAboutFillon = new TweetAboutCandidate("fillon");
        tweetAboutFillon.setPeriod(period);
        //tweetAboutFillon.extractThisDay();

        TweetAboutCandidate tweetAboutMelenchon = new TweetAboutCandidate("melenchon");
        tweetAboutMelenchon.setPeriod(period);
        //tweetAboutMelenchon.extractThisDay();

        TweetAboutCandidate tweetAboutHamon = new TweetAboutCandidate("hamon");
        tweetAboutHamon.setPeriod(period);
        //tweetAboutHamon.extractThisDay();

        TweetAboutCandidate tweetAboutMacron = new TweetAboutCandidate("macron");
        tweetAboutMacron.setPeriod(period);
        //tweetAboutMacron.extractThisDay();

        TweetAboutCandidate tweetAboutMLP = new TweetAboutCandidate("MLP");
        tweetAboutMLP.setPeriod(period);
        tweetAboutMLP.extractThisDay();



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
