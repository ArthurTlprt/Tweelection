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
public class Word {
    private String word; /* The word */
    private ArrayList<Integer> classes; /* Default number of classes is 6 */
    private int number; /* the total numebr of occurrences of this word */
    
    public Word() {
        this.resetClasses(6);
    }
    
    public Word(String word) {
        this.setWord(word);
        this.resetClasses(6);
    }
    
    /* Setters */
    public void setWord(String word) {
        this.word = word;
    }
    
    public void resetClasses(int numberOfClasses) {
        classes = new ArrayList(numberOfClasses);
        for(int i = 0; i < classes.size(); i++) 
            classes.add(0);
        number = 0;
    }
    
    public void addOccurrence(int classe) {
        classes.set(classe, classes.get(classe)+1);
        number += 1;
    }
    
    /* Getters */
    public String getWord() {
        return this.word;
    }
    
    /* Returns the number of occurences of the word in the given class */
    public int getClassOccurence(int classe) {
        if(classe >= classes.size() || classe < 0)
            return -1;
        else
            return this.classes.get(classe);
    }
    
    
}
