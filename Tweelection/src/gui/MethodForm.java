/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author gerald
 */
public class MethodForm extends JPanel {
    
    ButtonGroup group;
    JRadioButton realTime, periodTime;
    Input period1, period2;
    
    public MethodForm() {
        super();
        setLayout(new GridLayout(4,1));
        
        realTime = new JRadioButton("Real time analysis");
        periodTime = new JRadioButton("Period analysis");
        group = new ButtonGroup();
        group.add(realTime);
        group.add(periodTime);
        
        period1 = new Input("Start : ");
        period2 = new Input("End : ");
        
        this.add(realTime);
        this.add(periodTime);
        this.add(period1);
        this.add(period2);
    }
    
    public int getMethod() {
        if(realTime.isSelected())
            return 0;
        
        if(periodTime.isSelected())
            return 1;
        
        return -1;
    }
    
    public String getStart() { return period1.getText(); }
    public String getEnd() { return period2.getText(); }
    
}
