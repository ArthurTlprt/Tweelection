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
 * @author gerald
 */
public class SubjectsForm extends JPanel {

    private Input subject1, subject2;
    
    public SubjectsForm() {
        super();
        
        setLayout(new GridLayout(2, 1));
        subject1 = new Input("Subject 1 : ");
        subject2 = new Input("Subject 2 : ");

        this.add(subject1);
        this.add(subject2);
    }
    
    public String getSubject1() { return subject1.getText(); }
    public String getSubject2() { return subject2.getText(); }
}
