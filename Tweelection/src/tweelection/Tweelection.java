/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweelection;

import sentimentanalysis.BagOfWords;

/**
 *
 * @author gerald
 */
public class Tweelection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BagOfWords bog = new BagOfWords();
        bog.addWord("Bonjour", 3);
        bog.addWord("nul", 0);
        bog.addWord("parfait", 5);
        for(int i = 0; i < 125; i++)
            bog.addWord("capitalisme", 0);
        bog.print(5);
        bog.save("bog.txt");
    }
    
}
