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
import java.util.List;
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
        
        bag = new BagOfWords();
    }
    
    /* My set of setters */
    public void setRealTime(boolean realTime) { this.realTime = realTime; }
    public void setPeriod(List<String> period) { this.period = period; }
    public void setBogFileName(String bogFileName) { this.bogFileName = bogFileName; }
    
    /* My set of getters */
    public boolean getRealTime() { return realTime; }
    public int getNumberOfSubjects() { return namesOfSubjects.size(); }
    public List<String> getPeriod() { return period; }
    public String getPeriodByIndex(int index) { return period.get(index); }
    public String getBogFileName() { return bogFileName; }
    
    /* Adds a new subject to search */
    public void addSubject(String subject) {
        namesOfSubjects.add(subject);
    }
    
    /* Sets the good parameters */
    public void setUp() {
        for(int i = 0; i < getNumberOfSubjects(); i++) {
            rates.add(new double[period.size()]);
            numberAnalyzed.add(new double[period.size()]);
        }
    }
    
    /* Fonction called to launch the analyze of the subjects */
    public void launchAnalyze() {
        if(realTime)
            launchAnalyzeRealTime();
        else
            launchAnalyzeFiles();
    }
    
    public void launchAnalyzeRealTime() {
        
    }
    
    public void launchAnalyzeFiles() {
        try {
            bag.setFileName(bogFileName);
            bag = new BagOfWords(bag.deserialize());
            
            setUp();
            
            for(int i = 0; i < getNumberOfSubjects(); i++) {
                for(int j = 0; j < period.size(); j++) {
                    String period1 = period.get(j);
                    String fileToRead = "tweets_files/" + namesOfSubjects.get(i) + "/" + period1;
                    //System.out.println(fileToRead + " : ");
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
    
    
    public void graphTest() {
        Graph g = new Graph();
        double[] day = new double[period.size()];
        System.out.println("Graph test : ");
        System.out.println("Number of guys : " + getNumberOfSubjects());
        
        for(int i = 0; i < period.size(); i++)
            day[i] = i;
        
        for(int i = 0; i < getNumberOfSubjects(); i++) {
            System.out.println("Guy " + i + ":");
            System.out.println("rates size : " + rates.get(i).length);
            System.out.println("numbers size : " + numberAnalyzed.get(i).length);
            System.out.println("day size : " + day.length);
            g.addData(namesOfSubjects.get(i), day, rates.get(i), numberAnalyzed.get(i));
        }
        
        g.display();
    }
    
}
