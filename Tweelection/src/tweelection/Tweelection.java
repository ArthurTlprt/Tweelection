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
            bog = new BagOfWords(bog.deserialize());
            //bog.learnReviews("rates.txt", "reviews.txt");
            //bog.learnReviews("rates2.txt", "reviews2.txt");
            //bog = new BagOfWords(bog.deserialize());
            //bog.addModifier("tres", 2);
            //bog.addModifier("pas", -1);
            System.out.println(bog.evaluateAccuracy("rates2.txt", "reviews2.txt"));
            //bog.print(20);
            //bog.load("bog.txt");
            //BagOfWords bog2 = new BagOfWords(bog);
            //bog.print(10);
            //bog2.print(10);
            //Review review = new Review();
            //review.setText("Parce que les étrangers coûtent moins cher que ma femme, je continuerai à voler en toute impunité");
            //review.parseReview();
            //System.out.println(bog.analyzeReview(review));
            //System.out.println(bog.getWordClasse("tres"));
            /*bog.addWord("Bonjour", 3);
            bog.addWord("nul", 0);
            bog.addWord("parfait", 5);
            for(int i = 0; i < 125; i++)
                bog.addWord("capitalisme", 0);
            bog.print(5);*/
            //bog.serialize();
        } catch (Exception e) {
            System.out.println("Ton père le chien");
            //System.out.println(e.getMessage());
        }
    }
    
}
