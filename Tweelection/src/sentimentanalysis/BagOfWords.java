/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentimentanalysis;

import static java.lang.Math.abs;
import java.util.ArrayList;


/**
 *
 * @author gerald
 */
public class BagOfWords {
    private ArrayList<String> words;
    private ArrayList<Integer>[] occurencesByClasses;
    
    private int numberOfClasses;
    /* Représentation :
       Classe : 0   1   2   3   4   5
        mot 1 : x   x   x   x   x   x
        [0].get(index): occurences du mot index en classe 0
    */
    public BagOfWords() {
        numberOfClasses = 6;
        
        words = new ArrayList<>();
        occurencesByClasses = new ArrayList[numberOfClasses];
        for(int i = 0; i < occurencesByClasses.length; i++)
            occurencesByClasses[i] = new ArrayList<>();
    }
    
    /* Getters */
    public String getWordByIndex(int index) {
        return words.get(index);
    }
    
    public int getIndexByWord(String word) {
        if(words.isEmpty())
            return -1;
        return words.indexOf(word);
    }
    
    public int getTotalOccurences(int wordIndex) {
        int result = 0;
        for (ArrayList<Integer> obc : occurencesByClasses) {
            result += obc.get(wordIndex);
        }
        
        return result;
    }
    
    public void addOccurenceByIndex(int wordIndex, int classe) {
        if(wordIndex >= 0 && wordIndex < words.size()) {
            
        }
    }
    
    public void addOccurenceByWord(String word, int classe) {
        
    }
    /* Setters */
    
    
    /* Miscellaneous */
    
    /* Adds a word to the database */
    public void addWord(String word, int classe) {
        int wordIndex = getIndexByWord(word);
        if(wordIndex == -1) {
            /* Word unknown for now */
            words.add(word);
            for(ArrayList<Integer> obc : occurencesByClasses)
                obc.add(0);
            addWord(word, classe);
        } else {
            /* Word known */
            int actual = occurencesByClasses[classe].get(wordIndex);
            occurencesByClasses[classe].set(wordIndex, actual+1);
        }
    }
    
    /* Prints n lines of my BagOfWord */
    public void print(int n) {
        System.out.print("Classe");
        for(int i = 0; i < 9; i++)
            System.out.print(" ");
        
        for(int i = 0; i < occurencesByClasses.length; i++) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
        
        for(int i = 0; (i < words.size() && i < n); i++) {
            System.out.print(words.get(i));
            
            for(int j = 0; j < abs(15 - words.get(i).length()); j++)
                System.out.print(" ");
            
            for (ArrayList<Integer> obc : occurencesByClasses) {
                System.out.print(obc.get(i));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
