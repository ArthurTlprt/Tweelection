/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentimentanalysis;

import java.util.ArrayList;

/**
 *
 * @author gerald
 */
public class Review {
    private int classe; /* The class of the review */
    private String text; /* The text of the review */
    private ArrayList<String> parsedText;
    
    public Review() {
        
    }
    
    /* Setters */
    public void setText(String text) {
        this.text = text;
    }
    
    public void setClasse(int classe) {
        this.classe = classe;
    }
    
    /* Getters */
    public String getText() {
        return this.text;
    }
    
    public int getClasse() {
        return classe;
    }
    
    /* Miscellaneous */
    public void parseReview() {
        
    }
}