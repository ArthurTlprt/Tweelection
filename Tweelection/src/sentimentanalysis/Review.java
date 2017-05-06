/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentimentanalysis;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.ListIterator;

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
    
    public String getWordByIndex(int index) {
        return parsedText.get(index);
    }
    
    public int getSize() {
        return parsedText.size();
    }
    
    /* Miscellaneous */
    public void printParsedText() {
        for(String s : parsedText) {
            System.out.print(s + " ");
        }
        
        System.out.println("");
    }
    
    public void parseReview() {
        parsedText = new ArrayList<>();
        
        int space;
        do {
            space = text.indexOf(" ");
            if(space != -1) {
                parsedText.add(text.substring(0, space));
                text = text.substring(space+1);
            } else {
                parsedText.add(text);
            }

        } while(space != -1);         

    }
}
