/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author arthur
 */
public class Menu extends JPanel {
    GridLayout grid;

    public Menu() {
        super();
        grid = new GridLayout(2, 1);
        this.setLayout(grid);
        
        this.add(new RealTimeForm());
        this.add(new PeriodForm());
    }

}
