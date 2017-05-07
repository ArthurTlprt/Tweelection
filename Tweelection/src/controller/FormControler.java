/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author gerald
 */
public class FormControler {

    public FormControler() {
        
    }
    
    public boolean isDate(String date) {
        date = date.replaceAll("201[1-9]-(([0][1-9])|([1][0-2]))-(([0][1-9])|[1-2][0-9]|[3][0-1])", "");

        if(date.length() !=  0)
            System.out.println("La date n'est pas du bon format !");
        return date.length() == 0;
    }
    
    public boolean goodOrder(String date1, String date2) {
        int number1, number2;
        int index1, index2, m1 = 0, m2 = 0;
        boolean result = true;
        
        for(int i = 0; i < 2; i++) {
            index1 = date1.indexOf('-');
            index2 = date2.indexOf('-');

            number1 = Integer.parseInt(date1.substring(0, index1));
            number2 = Integer.parseInt(date2.substring(0, index2));

            if(number1 > number2)
                result = false;

            date1 = date1.substring(index1+1);
            date2 = date2.substring(index1+1);
            
            m1 = number1;
            m2 = number2;
        }
        
        number1 = Integer.parseInt(date1);
        number2 = Integer.parseInt(date2);
        
        if(m1 == m2 && number1 >= number2)
            result = false;
        
        if(!result)
            System.out.println("Les dates ne sont pas dans le bon ordre !");
        
        return result;
    }
    
    public boolean isPeriodGood(String date1, String date2) {
        if(date1.length() == 0 || date2.length() == 0)
            return false;
        
        if(!isDate(date1) || !isDate(date2))
            return false;
        
        if(!goodOrder(date1, date2))
            return false;
        
        return true;
    }
    
    public boolean areSubjectsGood(String sub1, String sub2) {
        if(sub1.equals(sub2))
            return false;
        
        if(sub1.length() == 0 || sub2.length() == 0)
            return false;
        
        return true;
    }
    
    public boolean allIsGood(String start, String end, String sub1, String sub2) {
        return isPeriodGood(start, end) && areSubjectsGood(sub1, sub2);
    }
}
