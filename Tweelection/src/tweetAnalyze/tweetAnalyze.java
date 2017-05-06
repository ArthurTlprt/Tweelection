/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetAnalyze;

import gui.Graph;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import sentimentanalysis.BagOfWords;
import sentimentanalysis.Review;

/**
 *
 * @author gerald
 */
public class tweetAnalyze {
    private boolean realTime;
    // private int numberOfSubjects = 0;
    private ArrayList<String> namesOfSubjects;
    private ArrayList<double[]> rates;
    private ArrayList<double[]> numberAnalyzed;
    private List<String> period;
    
    private int threshold = 60;
    
    private Graph g;
    /*
        rates.get(0) contient les notes de namesOfSubject.get(0) pour la periode entière
        Rates.get(0)[0] contient la note de names.get(0) pour period.get(0),
            calculé sur numberAnalyzed.get(0)[0]
    */
    
    private String bogFileName = "bog.tl";
    private BagOfWords bag;
    
    public tweetAnalyze() {
        namesOfSubjects = new ArrayList<>();
        rates = new ArrayList<>();
        numberAnalyzed = new ArrayList<>();
        //period = new List<>();
        g = new Graph();
        
        bag = new BagOfWords();
    }
    
    /* My set of setters */
    public void setRealTime(boolean realTime) { this.realTime = realTime; }
    public void setPeriod(List<String> period) { this.period = period; }
    public void setBogFileName(String bogFileName) { this.bogFileName = bogFileName; }
    public void setThreshold(int threshold) { this.threshold = threshold; }
    
    /* My set of getters */
    public boolean getRealTime() { return realTime; }
    public int getNumberOfSubjects() { return namesOfSubjects.size(); }
    public List<String> getPeriod() { return period; }
    public String getPeriodByIndex(int index) { return period.get(index); }
    public String getBogFileName() { return bogFileName; }
    
    public int getIndexByName(String name) {
        for(int i = 0; i < namesOfSubjects.size(); i++) {
            if(name.equals(namesOfSubjects.get(i)))
                return i;
        }
        
        return -1;
    }
    
    /* Adds a new subject to search */
    public void addSubject(String subject) {
        namesOfSubjects.add(subject);
    }
    
    /* Sets the good parameters */
    public void setUp(int size) {
        for(int i = 0; i < getNumberOfSubjects(); i++) {
            rates.add(new double[size]);
            numberAnalyzed.add(new double[size]);
        }
    }
    
    /* Fonction called to launch the analyze of the subjects */
    public void launchAnalyze() {
        if(realTime)
            System.out.println("Real time");
        else
            launchAnalyzeFiles();
    }
    
    public void launchAnalyzeRealTime(ArrayList<String> tweets) {
        try {
            bag.setFileName(bogFileName);
            bag = new BagOfWords(bag.deserialize());
            
            int subjectIndex = getIndexByName(tweets.get(0));
            if(rates.isEmpty())
                setUp(1);
            else if(rates.get(0).length == 0)
                setUp(1);

            int numberOfTweets = 0;
            double classe = 0;
            int toPlace = getPlaceRealTime(subjectIndex);
 
            for(int i = 1; i < tweets.size(); i++) {
                Review review = new Review();
                review.setText(tweets.get(i));
                review.parseReview();
                classe += bag.analyzeReview(review);
                
                numberOfTweets++;
            }
            
            classe /= numberOfTweets;
            rates.get(subjectIndex)[toPlace] = classe;
            numberAnalyzed.get(subjectIndex)[toPlace] = numberOfTweets;
            
            double[] day = new double[rates.get(0).length];
            for(int i = 0; i < rates.get(0).length; i++)
                day[i] = i;
            
            if(rates.get(subjectIndex).length != 1) {
                g.removeData(namesOfSubjects.get(subjectIndex));
                g.addData(namesOfSubjects.get(subjectIndex), day, rates.get(subjectIndex), numberAnalyzed.get(subjectIndex));
            }
            
        } catch(Exception e) {
            System.out.println("Real time analyze failed");
            System.out.println(e.getMessage());
        }
    }
    
    public void launchAnalyzeFiles() {
        try {
            bag.setFileName(bogFileName);
            bag = new BagOfWords(bag.deserialize());
            
            setUp(period.size());
            
            for(int i = 0; i < getNumberOfSubjects(); i++) {
                for(int j = 0; j < period.size(); j++) {
                    String period1 = period.get(j);
                    String name = namesOfSubjects.get(i);
                    name = name.replaceAll(" ", "\\ ");
                    
                    String fileToRead = "tweets_files/" + name + "/" + period1;
                    BufferedReader brReview = new BufferedReader(new FileReader(fileToRead));

                    String lineReview;
                    lineReview = brReview.readLine();
                    double classe = 0;
                    int numberOfReviews = 0;
                    
                    while(lineReview != null) {
                        Review review = new Review();
                        review.setText(lineReview);
                        review.parseReview();
                        
                        classe += bag.analyzeReview(review);

                        numberOfReviews++;
                        lineReview = brReview.readLine();
                    }
                    classe /= numberOfReviews;
                    rates.get(i)[j] = classe;
                    numberAnalyzed.get(i)[j] = numberOfReviews;
                }
            }
            
            
        } catch(FileNotFoundException e) {
            System.out.println("Failed to load reviews");
        } catch (IOException ex) {
            //System.out.println("Prout");
            Logger.getLogger(BagOfWords.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'analyze des fichiers");
            System.out.println(e.getMessage());
        }
    }
    
    
    public void launchGraph() {
        
        double[] day = new double[rates.get(0).length];
        for(int i = 0; i < period.size(); i++)
            day[i] = i;
        
        for(int i = 0; i < getNumberOfSubjects(); i++)
            g.addData(namesOfSubjects.get(i), day, rates.get(i), numberAnalyzed.get(i));
        
        g.display();
    }
    
    public void displayGraph() {
        g.display();
    }
    
    public void refreshGraph() {
        g.refresh();
    }
    
    public int getPlaceRealTime(int subjectIndex) {
        if(rates.get(subjectIndex).length == 1 && rates.get(subjectIndex)[0] == 0.0) {
            return 0;
        }
        
        if(rates.get(subjectIndex).length == threshold) {
            for(int j = 0; j < threshold-1; j++) {
                rates.get(subjectIndex)[j] = rates.get(subjectIndex)[j+1];
                numberAnalyzed.get(subjectIndex)[j] = numberAnalyzed.get(subjectIndex)[j+1];
            }
            
            return threshold-1;
        } else {
            int size = rates.get(subjectIndex).length;
            
            double[] newR = new double[size+1];
            double[] newN = new double[size+1];
            
            for(int i = 0; i < size; i++) {
                newR[i] = rates.get(subjectIndex)[i];
                newN[i] = numberAnalyzed.get(subjectIndex)[i];
            }
            
            ListIterator<double[]> itRates = rates.listIterator();
            ListIterator<double[]> itNumber = numberAnalyzed.listIterator();
            for(int i = 0; i <= subjectIndex; i++) {
                itRates.next();
                itNumber.next();
            }
            
            itRates.set(newR);
            itNumber.set(newN);
            
            return size;
            
        }
        
    }
    
    public void save() {
        bag.serialize();
        System.out.println("Done !");
    }
}
