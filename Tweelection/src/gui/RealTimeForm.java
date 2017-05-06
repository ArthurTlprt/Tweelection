/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author arthur
 */
public class RealTimeForm extends JPanel{
    private GridLayout grid;
    Input subject1, subject2;
    JButton submit;
    JLabel label;
    
    public RealTimeForm() {
        super();
        grid = new GridLayout(4, 1);
        label = new JLabel("Mode temps réel", JLabel.CENTER);
        subject1 = new Input("Candidat 1:");
        subject2 = new Input("Candidat 2:");
        JPanel panelButton = new JPanel();
        submit = new JButton("submit");
        panelButton.add(submit);
        this.setLayout(grid);
        this.add(label);
        this.add(subject1);
        this.add(subject2);
        this.add(panelButton);
        
    }
    
}
