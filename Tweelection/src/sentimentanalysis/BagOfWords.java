/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentimentanalysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.abs;
import java.text.Normalizer;
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
    
    /* Computes a word to save it */
    public String computeWord(String word) {
        word = Normalizer.normalize(word, Normalizer.Form.NFD);
        word = word.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        word = word.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        word = word.replaceAll(" ", "");
        return word;
    }
    
    /* Adds a word to the database */
    public void addWord(String word, int classe) {
        word = computeWord(word);
        if(word.length() == 0) 
            return;
        
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
    
    
    /* Load and save function */
    public void save(String fileName) {
        System.out.println("Saving...");
        PrintWriter writer = null;
        try{
            writer = new PrintWriter(fileName);
            writer.println(numberOfClasses);
            
            for(int i = 0; i < words.size(); i++) {
                writer.print(words.get(i));
                for(ArrayList<Integer> ocb : occurencesByClasses) {
                    writer.print(";");
                    writer.print(ocb.get(i));
                }
                writer.println();
            }

        } catch (IOException e) {
           System.out.println("Failed to save");
        } finally {
            if(writer != null)
                writer.close();
        }
        
        System.out.println("Saved !");
    }
    
    public void load(String fileName){        
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            this.numberOfClasses = Integer.parseInt(line);
            line = br.readLine();
            while (line != null) {
                read_line(line);
                line = br.readLine();
            }
        } catch(Exception e) {
            System.out.println("Failed to load");
        }
    }
    
    public void read_line(String line) {
        int semicolon = line.indexOf(";");
        String word = line.substring(0, semicolon);
        System.out.println(word);
        line = line.substring(semicolon+1);
        int number;
        for(int i = 0; i < numberOfClasses; i++) {
            semicolon = line.indexOf(";");
            
            if(semicolon != -1)
                number = Integer.parseInt(line.substring(0, semicolon));
            else
                number = Integer.parseInt(line);
            
            for(int j = 0; j < number; j++)
                this.addWord(word, i);
            
            line = line.substring(semicolon+1);
        }
    }
}
