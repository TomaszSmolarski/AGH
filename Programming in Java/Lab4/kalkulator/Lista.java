package kalkulator;

import javax.swing.*;

public class Lista extends JFrame {
   public DefaultListModel<String> listModel = new DefaultListModel<>();
    public Lista() {
        String sin = "sin()";
        String cos = "cos()";
        String tan = "tan()";
        String ctan = "ctan()";
        String asin = "asin()";
        String pi = "pi";
        String e = "e";
        String gam = "[gam]";
        String potega = "^";
        String silnia = "!";
        String modulo = "#";
        String last_result = "Last Result";


        //create the model and add elements

        listModel.addElement(sin);
        listModel.addElement(cos);
        listModel.addElement(tan);
        listModel.addElement(ctan);
        listModel.addElement(asin);
        listModel.addElement(pi);
        listModel.addElement(e);
        listModel.addElement(gam);
        listModel.addElement(potega);
        listModel.addElement(silnia);
        listModel.addElement(modulo);
        listModel.addElement(last_result);



    }

}