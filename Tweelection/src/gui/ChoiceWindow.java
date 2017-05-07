/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author gerald
 */
public class ChoiceWindow extends JFrame {
    
    SubjectsForm sf;
    MethodForm mf;
    JButton submit;
    
    public ChoiceWindow() {
        super("Tweelection");
        
        this.setSize(375, 600);
        this.setLayout(new GridLayout(3, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        sf = new SubjectsForm();
        mf = new MethodForm();
        submit = new JButton("Validate");
        
        this.add(sf);
        this.add(mf);
        this.add(submit);
        
        this.setVisible(true);
    }
    
    public void addListener(ActionListener o) {
        submit.addActionListener(o);
    }
    
    public String getSubject1() { return sf.getSubject1(); }
    public String getSubject2() { return sf.getSubject2(); }
    
    public int getMethod() { return mf.getMethod(); }
    
    public String getStart() { return mf.getStart(); }
    public String getEnd() { return mf.getEnd(); }
}
