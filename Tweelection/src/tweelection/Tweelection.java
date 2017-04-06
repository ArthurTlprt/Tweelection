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
        System.out.println(bog.computeWord("??????").length());
    }
    
}
