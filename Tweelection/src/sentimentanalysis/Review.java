/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentimentanalysis;

import java.util.ArrayList;

/**
 * Contient un texte, déjà noté ou à noter
 */
public class Review {
    private int classe; /* The class of the review */
    private String text; /* The text of the review */
    private ArrayList<String> parsedText;
    
    public Review() {
        
    }
    
    /* Setters */
    public void setText(String text) { this.text = text; }
    public void setClasse(int classe) { this.classe = classe; }
    
    /* Getters */
    public String getText() { return this.text; }
    public int getClasse() { return classe; }
    public String getWordByIndex(int index) { return parsedText.get(index); }
    public int getSize() { return parsedText.size(); }
    
    /* Affiche ma review */
    public void printParsedText() {
        parsedText.forEach((String s) -> { System.out.print(s + " "); });
        System.out.println("");
    }
    
    /* Passe du texte en une seule String */
    /* A un ArrayList contenant chaque mot à la suite */
    /* Plus pratique pour l'analyse */
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
