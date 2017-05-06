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
     * @throws java.text.ParseException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws ParseException, InterruptedException {
        
        // Mode temps réel
        /*ArrayList<String> names = new ArrayList<>();
        names.add("macron");
        names.add("MLP");
        RealTimeTweet mySession= new RealTimeTweet(names);
        //*/
        
        // Mode via les fichiers sur une période
        setPeriod("2017-04-30", "2017-05-06");
        String subject1 = "MLP", subject2 = "macron";
        TweetAboutSubject tweetAboutChoco = new TweetAboutSubject(subject1, period);
        TweetAboutSubject tweetAboutPain = new TweetAboutSubject(subject2, period);
        
        
        tweetAnalyze ta = new tweetAnalyze();
        ta.setRealTime(false);
        ta.setPeriod(period);

        ta.addSubject(subject1);
        ta.addSubject(subject2);
        
        ta.launchAnalyze();
        ta.launchGraph();
        
        ta.save();
        //*/

    }

}
