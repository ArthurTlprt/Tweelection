/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * La partie de la fenêtre qui recueille les noms des sujets à étudier
 */
public class SubjectsForm extends JPanel implements ActionListener {

    private final Input newSubject;
    private final JButton addSubject;
    private final SubjectList list;
    
    public SubjectsForm() {
        super();
        
        setLayout(new GridLayout(3, 1));
        newSubject = new Input("Add subject : ");
        addSubject = new JButton("Add");
        list = new SubjectList();
        
        addSubject.addActionListener(this);
        
        this.add(newSubject);
        this.add(addSubject);
        this.add(list);
    }

    public String getSubject(int index) { return list.getSubject(index); }
    public ArrayList<String> getSubjects() { return list.getSubjects(); }
    public int getNumberOfSubjects() { return list.getNumberOfSubjects(); }
    
    /* Lorsqu'on appuie sur le bouton pour ajouter un sujet */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(newSubject.getText() != null && newSubject.getText().length() != 0)
            list.addSubject(newSubject.getText());
    }
}
