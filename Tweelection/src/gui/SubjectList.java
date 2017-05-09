/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author gerald
 */
public class SubjectList extends JPanel {
    private ArrayList<String> list;
    private ArrayList<JLabel> textList;
            
    public SubjectList() {
        list = new ArrayList<>();
        textList = new ArrayList<>();
    }
    
    public void addSubject(String newSubject) { 
        list.add(newSubject);
        textList.add(new JLabel("- " + newSubject));
        refresh();
    }
    
    public void refresh() {
        this.removeAll();
        this.setLayout(new GridLayout(list.size(), 1));
        for(JLabel sub : textList) {
            this.add(sub);
        }
        
        this.revalidate();
        this.repaint();
    }
    
    public String getSubject(int index) { return list.get(index); }
    public ArrayList<String> getSubjects() { return list; }
    public int getNumberOfSubjects() { return list.size(); }
}
