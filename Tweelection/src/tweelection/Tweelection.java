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
import tweetExtraction.TweetAboutSubject;
import org.knowm.xchart.XYChart;
import tweetAnalyze.tweetAnalyze;
import tweetExtraction.RealTimeTweet;

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
        
        
        //  Mode sur une periode
        /*
        setPeriod("2017-04-28", "2017-04-30");
        TweetAboutSubject tweetAboutFillon = new TweetAboutSubject("fillon", period);
        TweetAboutSubject tweetAboutMelenchon = new TweetAboutSubject("melenchon", period);
        TweetAboutSubject tweetAboutHamon = new TweetAboutSubject("hamon", period);
        TweetAboutSubject tweetAboutMacron = new TweetAboutSubject("macron", period);
        TweetAboutSubject tweetAboutMLP = new TweetAboutSubject("MLP", period);
        TweetAboutSubject tweetAboutLassalle = new TweetAboutSubject("lassalle", period);
        TweetAboutSubject tweetAboutDupont_Aignan = new TweetAboutSubject("dupont-aignan", period);
        TweetAboutSubject tweetAboutPoutou = new TweetAboutSubject("poutou", period);
        */
        
        // Mode temps réel
        ArrayList<String> names1 = new ArrayList<>(), names2 = new ArrayList<>();
        //names.add("crepe");
        //names.add("gaufre");
        //names.add("levrette");
        //names.add("missionaire");
        names2.add("macron");
        names2.add("marine");
        //names.add("pain chocolat");
        //names.add("chocolatine");
        //names.add("yaourt fraise");
        //names.add("yaourt peche");
        names1.add("slip");
        names1.add("calecon");
        RealTimeTweet mySession= new RealTimeTweet(names2);
        
        
        // Mode via les fichiers
        /*tweetAnalyze ta = new tweetAnalyze();
        ta.setRealTime(false);
        setPeriod("2017-04-23", "2017-04-30");
        ta.setPeriod(period);

        ta.addSubject("macron");
        ta.addSubject("MLP");
        
        ta.launchAnalyze();
        ta.launchGraph();*/

        try {
            /*BagOfWords bog = new BagOfWords();
            //bog = new BagOfWords(bog.deserialize());
            bog.learnReviews("rates.txt", "reviews.txt");
            bog.learnReviews("rates2.txt", "reviews2.txt");
            bog.learnReviews("rates3.txt", "reviews3.txt");
            bog.addModifier("pas", -1);
            bog.addModifier("très", 2);
            bog.addModifier("trop", 2);*/
            //bog = new BagOfWords(bog.deserialize());
            //bog.addModifier("tres", 2);
            //bog.addModifier("pas", -1);
            //System.out.println(bog.evaluateAccuracy("rates2.txt", "reviews2.txt"));*/
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
