/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentimentanalysis;

import java.io.Serializable;

/**
 *
 * @author gerald
 */
public class Modifier implements Serializable {
    private String word;
    private int multiplier;
    
    public Modifier() {
        
    }
    
    public Modifier(String word, int multiplier) {
        this.word = word;
        this.multiplier = multiplier;
    }
    
    public Modifier(Modifier m) {
        this.word = m.word;
        this.multiplier = m.multiplier;
    }
    
    /* Getters */
    public String getWord() { return word; }
    public int getMultiplier() { return multiplier; }
}
