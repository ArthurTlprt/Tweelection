/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * La fenêtre s'affichant au lancement du programme 
 */
public class ChoiceWindow extends JFrame {
    
    private final SubjectsForm sf;
    private final MethodForm mf;
    private final JButton submit;
    
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
    
    public String getSubject(int index) { return sf.getSubject(index); }
    public ArrayList<String> getSubjects() { return sf.getSubjects(); }
    public int getNumberOfSubjects() { return sf.getNumberOfSubjects(); }
    
    public int getMethod() { return mf.getMethod(); }
    
    public String getStart() { return mf.getStart(); }
    public String getEnd() { return mf.getEnd(); }
}
