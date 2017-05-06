/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 *
 * @author arthur
 */
public class Window extends JFrame {
    
    private Menu menu;
    public Window() {
        super("Tweelection");
        this.setSize(375, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        menu = new Menu();
        this.add(menu);
    }

}
