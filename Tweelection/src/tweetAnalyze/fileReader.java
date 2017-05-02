/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetAnalyze;

import java.util.ArrayList;

/**
 *
 * @author gerald
 */
public class fileReader {
    private ArrayList<double[]> rates;
    private ArrayList<double[]> numberAnalyzed;
    private ArrayList<Double> dates; 
    private ArrayList<String> names;
    
    private CustomDate startDate;
    private CustomDate endDate;
    private int scale;
    private int gap;
    
    public fileReader() {
        
    }
    
    public void addName(String newName) {
        names.add(newName);
    }
    
    public void setStart(CustomDate startDate) {
        this.startDate = new CustomDate(startDate);
    }
    
    public void setEnd(CustomDate endDate) {
        this.endDate = new CustomDate(endDate);
    }
    
    
}
