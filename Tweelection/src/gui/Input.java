/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Une classe composé d'un label et d'un textfield
 * Pure practicité
 */
public class Input extends JPanel {
    
    private final JLabel label;
    private final JTextField text;
    
    public Input(String label) {
        super();
        this.setLayout(new GridLayout(1, 2));
        
        this.label = new JLabel(label);
        this.text = new JTextField();
        
        this.add(this.label);
        this.add(text);
    }
    
    public String getText() { return text.getText(); }
    
}
