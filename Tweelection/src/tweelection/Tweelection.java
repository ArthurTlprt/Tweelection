/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweelection;

import sentimentanalysis.BagOfWords;
import sentimentanalysis.Review;

/**
 *
 * @author gerald
 */
public class Tweelection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            BagOfWords bog = new BagOfWords();
            //bog.learnReviews("rates.txt", "reviews.txt");
            //bog.print(20);
            bog.load("bog.txt");
            System.out.println(bog.evaluateAccuracy("rates.txt", "reviews.txt"));
            //System.out.println(bog.getWordClasse("jusqua"));
            /*bog.addWord("Bonjour", 3);
            bog.addWord("nul", 0);
            bog.addWord("parfait", 5);
            for(int i = 0; i < 125; i++)
                bog.addWord("capitalisme", 0);
            bog.print(5);*/
            //bog.save("bog.txt");
        } catch (Exception e) {
            System.out.println("Ton père le chien");
        }
    }
    
}
