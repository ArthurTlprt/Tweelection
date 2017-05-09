/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * La liste des sujets déjà entrés dans la fenêtre
 */
public class SubjectList extends JPanel implements MouseListener {
    private final ArrayList<String> list;
    private final ArrayList<JLabel> textList;
            
    public SubjectList() {
        list = new ArrayList<>();
        textList = new ArrayList<>();
    }
    
    public void addSubject(String newSubject) { 
        list.add(newSubject);
        textList.add(new JLabel("- " + newSubject));
        textList.get(textList.size()-1).addMouseListener(this);
        refresh();
    }
    
    public void refresh() {
        this.removeAll();
        this.setLayout(new GridLayout(list.size(), 1));
        textList.forEach((sub) -> {
            this.add(sub);
        });
        
        this.revalidate();
        this.repaint();
    }
    
    public String getSubject(int index) { return list.get(index); }
    public ArrayList<String> getSubjects() { return list; }
    public int getNumberOfSubjects() { return list.size(); }

    public int getIndex(String s) {
        for(int i = 0; i < list.size(); i++) {
            if(textList.get(i).getText().equals(s))
                return i;
        }
        
        return -1;
    }
    
    public void removeFromList(int index) {
        list.remove(index);
        textList.remove(index);
        refresh();
    }
    
    @Override
    public void mouseClicked(MouseEvent me) { removeFromList(getIndex(((JLabel) me.getSource()).getText())); }

    @Override
    public void mousePressed(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}
}
