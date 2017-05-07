/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

/**
 *
 * @author Iberos
 */
public class Graph {
    private XYChart[] charts;
    private JFrame frame;
    
    public Graph() {
        this.charts = new XYChart[2];
        
        // Rate chart
        this.charts[0] = new XYChartBuilder().width(800).height(300).title("Note").build();
        
        this.charts[0].getStyler().setLegendPosition(LegendPosition.InsideNE);
        this.charts[0].getStyler().setMarkerSize(0);
        this.charts[0].getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);
        
        // Tweet count chart
        this.charts[1] = new XYChartBuilder().width(800).height(300).title("Nombre de tweets").build();
        
        this.charts[1].getStyler().setLegendPosition(LegendPosition.InsideNE);
        this.charts[1].getStyler().setMarkerSize(0);
    }
    
    public void addData(String name, double[] day, double[] rates, double[] number) {
        this.charts[0].addSeries(name, day, rates);
        this.charts[1].addSeries(name, day, number);
    }
    
    public void removeData(String name) {
        this.charts[0].removeSeries(name);
        this.charts[1].removeSeries(name);
    }
    
    public void display() {
        frame = new JFrame("Tweelection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setLayout(new GridLayout(2, 1, 0, 0));
        
        frame.add(new XChartPanel(this.charts[0]));
        frame.add(new XChartPanel(this.charts[1]));
        
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
    
    public void refresh() {
        frame.getContentPane().removeAll();
        
        frame.setLayout(new GridLayout(2, 1, 0, 0));
        
        frame.add(new XChartPanel(this.charts[0]));
        frame.add(new XChartPanel(this.charts[1]));
        frame.validate();
    }
}
