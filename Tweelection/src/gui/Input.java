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
 *
 * @author arthur
 */
public class Input extends JPanel{
    
    public Input(String label) {
        super();
        this.setLayout(new GridLayout(1, 2));
        this.add(new JLabel(label));
        this.add(new JTextField());
    }
    
}
