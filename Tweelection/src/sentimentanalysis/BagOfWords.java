/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentimentanalysis;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import static java.lang.Math.abs;
import static java.lang.Math.round;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author gerald
 */
public class BagOfWords implements Serializable {
    private ArrayList<String> words;
    private ArrayList<Integer>[] occurencesByClasses;
    private ArrayList<Modifier> modifiers;
    private String fileName = "bog.tl";
    
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
        
        modifiers = new ArrayList<>();
    }
    
    public BagOfWords(BagOfWords bog) {
        //new BagOfWords(this);
        
        this.numberOfClasses = bog.numberOfClasses;
        this.words  = bog.words;
        this.occurencesByClasses = bog.occurencesByClasses;
        this.modifiers = new ArrayList<>();
        if(bog.modifiers != null) {
            for(int i = 0; i < bog.modifiers.size(); i++)
                this.modifiers.add(new Modifier(bog.modifiers.get(i)));
        }
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
    public void setFileName(String fileName) { this.fileName = fileName; }
    
    /* Miscellaneous */
    
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
    
    
    /* Computes a word to use it */
    public String computeWord(String word) {
        word = Normalizer.normalize(word, Normalizer.Form.NFD);
        word = word.replaceAll("(@[A-Za-z_]+)", "");
        word = word.replaceAll("(https?:\\/\\/)?t.co(\\/[A-Za-z\\d]+)", "");
        word = word.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        word = word.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        word = word.replaceAll(" ", "");
        return word;
    }
    
    
    /* Learning */
    /* Adds a word to the database */
    public void addWord(String word, int classe) {
        word = computeWord(word);
        if(word.length() <= 2) 
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
    
    
    /* Reads all the reviews from specified files */
    public void learnReviews(String rateFile, String reviewFile) {
        try { 
            BufferedReader brRate = new BufferedReader(new FileReader(rateFile));
            BufferedReader brReview = new BufferedReader(new FileReader(reviewFile));
            
            String lineRate, lineReview;
            lineRate = brRate.readLine();
            lineReview = brReview.readLine();
            while(lineRate != null && lineReview != null) {
                Review review = new Review();
                review.setClasse(Integer.parseInt(lineRate.substring(0, 1)));
                review.setText(lineReview);
                review.parseReview();

                for(int i = 0; i < review.getSize(); i++) {
                    if(isModifier(review.getWordByIndex(i)) == 0) {
                        addWord(review.getWordByIndex(i), review.getClasse());
                    }
                }
                
                lineRate = brRate.readLine();
                lineReview = brReview.readLine();
            }
            
        } catch(FileNotFoundException e) {
            System.out.println("Failed to load reviews");
        } catch (IOException ex) {
            //System.out.println("Prout");
            Logger.getLogger(BagOfWords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    /* Reading a modifier database */
    public void addModifier(String word, int multiplier) {
        Modifier m = new Modifier(word, multiplier);
        modifiers.add(m);
    }
    
    /* Analyzing a sentence */
    
    /* Tells if the word is a modifier */
    public int isModifier(String word) {
        word = computeWord(word);
        if(word.length() <= 2)
            return 0;
        
        if(modifiers.isEmpty())
            return 0;
        
        for(int i = 0; i < modifiers.size(); i++) {
            if(modifiers.get(i).getWord().equals(word))
                return i;
        }
        
        return 0;
    }
    
    
    /* Gets the probable class of a specified word */
    public double getWordClasse(String word) {
        word = computeWord(word);
        int index = getIndexByWord(word);
        if(index != -1) {
            double probableClasse = 0;
            int number = 0;
            int i = 0;
            for(ArrayList<Integer> ocb : occurencesByClasses) {
                probableClasse += ocb.get(index)*i;
                number += ocb.get(index);
                i++;
            }
            
            return probableClasse/number;
        } else 
            return -1;
    }
    
    /* Analyzes a sentence to get it's subjectivity */
    public double analyzeReview(Review review) {
        float classe = 0;
        double previousClasse =-1;
        int numberOfWords = 0;
        int multiplier = 1;
        boolean known = false;
        
        for(int i = 0; i < review.getSize(); i++) {
            int isModifier = isModifier(review.getWordByIndex(i));
            if(isModifier != 0)
                multiplier *= modifiers.get(isModifier).getMultiplier();    
            else {
                double probableWordClasse = getWordClasse(review.getWordByIndex(i));
                
                if(multiplier != 1) {
                    classe -= previousClasse;
                    float toAdd = (float) (multiplier*previousClasse);
                    if(toAdd < 0)
                        toAdd = 0;
                    if(toAdd > 5)
                        toAdd = 5;
                    classe += toAdd;
                }
                
                if(probableWordClasse != -1) {
                    double toAdd = (multiplier*probableWordClasse);
                    if(toAdd < 0)
                        toAdd = 0;
                    if(toAdd > 5)
                        toAdd = 5;
                    classe += toAdd;
                    numberOfWords++;
                    known = true;
                    previousClasse = toAdd;
                }

                multiplier = 1;
            }
        }
        
        if(multiplier != 1) {
            classe -= previousClasse;
            float toAdd = (float) (multiplier*previousClasse);
            if(toAdd < 0)
                toAdd = 0;
            if(toAdd > 5)
                toAdd = 5;
            classe += toAdd;
        }
        
        classe /= numberOfWords;
        
        if(!known)
            return 3;
        
        double threshold = 0.1;
        if(classe <= threshold)
            classe = 0;
        
        review.setClasse(round(classe));
        for(int i = 0; i < review.getSize(); i++) {
            if(isModifier(review.getWordByIndex(i)) == 0) {
                addWord(review.getWordByIndex(i), review.getClasse());
            }
        }
                
        return classe;
    }
    
    
    /* Self evaluates it's accuracy via a given database */
    public double evaluateAccuracy(String rateFile, String reviewFile) {
        int numberOfReviews = 0;
        double actual, calculated;
        double sum = 0;
        
        try { 
            BufferedReader brRate = new BufferedReader(new FileReader(rateFile));
            BufferedReader brReview = new BufferedReader(new FileReader(reviewFile));
            
            String lineRate, lineReview;
            lineRate = brRate.readLine();
            lineReview = brReview.readLine();
            while(lineRate != null && lineReview != null) {
                Review review = new Review();
                review.setClasse(Integer.parseInt(lineRate.substring(0, 1)));
                review.setText(lineReview);
                review.parseReview();

                actual = review.getClasse();
                calculated = analyzeReview(review);
                
                if(Double.isNaN(abs(actual-calculated)))
                    sum += 0;
                else
                    sum += abs(actual - calculated);
                numberOfReviews++;
                
                lineRate = brRate.readLine();
                lineReview = brReview.readLine();
            }
            
            sum /= (5*numberOfReviews);
            sum = 1 - sum;
            sum *= 100;
            return sum;
            
        } catch(FileNotFoundException e) {
            System.out.println("Failed to load reviews");
        } catch (IOException ex) {
            System.out.println("jsp");
            Logger.getLogger(BagOfWords.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception e) {
            System.out.println("Prout");
            System.out.println(e.getMessage());
        }
        
        return -1;
    }
    
    
    /* Serialization */
    public void serialize() {
        try {
            FileOutputStream file;
            file = new FileOutputStream(fileName);
            
            ObjectOutputStream obj = new ObjectOutputStream(file);
            obj.writeObject(this);
            
            obj.close();
            file.close();
            
        } catch(Exception e) {
            System.out.println("Serialzation has failed");
        }
    }
    
    public BagOfWords deserialize() {
        BagOfWords bog = null;
        try {
           
           //FileInputStream fileIn = new FileInputStream("bog.tl"); 
           FileInputStream fileIn = new FileInputStream(fileName);
           ObjectInputStream in = new ObjectInputStream(fileIn);
           
           bog = (BagOfWords) in.readObject();
           
           in.close();
           fileIn.close();
           
        }catch(Exception e) {
            System.out.println("Deserialization has failed");
        }
        
        return bog;
    }
}
