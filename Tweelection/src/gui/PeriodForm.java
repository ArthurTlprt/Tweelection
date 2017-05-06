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
public class PeriodForm extends JPanel{
    private GridLayout grid;
    Input date1, date2, subject1, subject2;
    JLabel label;
    JButton submit;
    
    public PeriodForm() {
        super();
        grid = new GridLayout(6, 1);
        label = new JLabel("Selon une periode", JLabel.CENTER);
        date1 = new Input("Début:");
        date2 = new Input("Fin:");
        subject1 = new Input("Candidat 1:");
        subject2 = new Input("Candidat 2:");
        submit = new JButton("submit");
        JPanel panelButton = new JPanel();
        panelButton.add(submit);
        this.setLayout(grid);
        this.add(label);
        this.add(subject1);
        this.add(subject2);
        this.add(date1);
        this.add(date2);
        this.add(panelButton);
        
    }
}
