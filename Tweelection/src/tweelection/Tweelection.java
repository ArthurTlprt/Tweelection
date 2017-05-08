/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweelection;

import gui.ChoiceWindow;
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
    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws ParseException, InterruptedException {

        tweetAnalyze ta = new tweetAnalyze();
        //ChoiceWindow cw = new ChoiceWindow();
    }

}
