package kalkulator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.mariuszgromada.math.mxparser.*;

public class Frame extends JFrame{
    private JButton button;
    private JPanel panel1;
    private JTextField text;
    private JList list1;
    private JTextArea textArea1;
    private JMenuBar menuBar;
    private JMenu menuOptions;
    private JMenuItem reset,exit;
    private List <String> a=new ArrayList<>();
    private int x;
    private Double result;

    public Frame() {

    ActionListener aaa = (e) -> {
try {


    Expression xxx = new Expression(text.getText());
    if (text.getText().contains("/0") || text.getText().contains("/-0")) {
        text.setText("");
       throw new Exception("NIE MOZNA DZIELIC PRZEZ 0!");
    } else if
    (xxx.checkSyntax()) {
        result = xxx.calculate();
        textArea1.append(text.getText() + "\t=\t" + xxx.calculate() + "\n");
        a.add(text.getText());
        x = a.size() - 1;
    } else {
        String errorMessage = xxx.getErrorMessage();
        JOptionPane.showMessageDialog(null, errorMessage, "ERROR", JOptionPane.ERROR_MESSAGE);
    }


    text.setText("");
    text.requestFocus();
}catch(Exception xd){JOptionPane.showMessageDialog(null, xd, "ERROR", JOptionPane.ERROR_MESSAGE);}
    };
    button.addActionListener(aaa);

    Lista l1 = new Lista();
    list1.setModel(l1.listModel);
    add(panel1);


    list1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            JList theList = (JList) e.getSource();
            if (e.getClickCount() == 2) {
                int index = theList.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Object o = theList.getModel().getElementAt(index);
                    if (o.toString() == l1.listModel.elementAt(l1.listModel.size()-1)) {
                        try{
                        text.setText(result.toString());}catch(Exception el){
                            JOptionPane.showMessageDialog(null, el, "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        if (o.toString().contains("()")) {
                            text.setText(text.getText() + o.toString());
                            text.requestFocus();
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    text.setCaretPosition(text.getText().length()-1);
                                }
                            });


                        }else {
                            text.setText(text.getText() + o.toString());
                            text.requestFocus();
                        }

                    }
                }
            }
        }
    });

    menuBar = new JMenuBar();
    menuOptions = new JMenu("Options");
    reset = new JMenuItem("reset");
    exit = new JMenuItem("exit");
    textArea1.setEditable(false);
    menuBar.add(menuOptions);
    menuOptions.add(reset);
    menuOptions.add(exit);
    setJMenuBar(menuBar);

    reset.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            textArea1.setText("");
            text.setText("");
            a.clear();
        }
    });

    text.addKeyListener(new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {

            try {
                if (e.getKeyCode() == 10) {
                    aaa.actionPerformed(null);
                }
if(!a.isEmpty()) {
    if (e.getKeyCode() == 38) {
        if (x > 0) {
            text.setText(a.get(x));
            x--;
        } else if (x == 0) {
            text.setText(a.get(x));
        }
    }

    if (e.getKeyCode() == 40) {
        if (x < a.size() - 1) {
            x++;
            text.setText(a.get(x));
        } else if (x == a.size() - 1) {
            text.setText(a.get(x));

        }
    }
}
            }catch(Exception xd){JOptionPane.showMessageDialog(null, xd, "ERROR", JOptionPane. ERROR_MESSAGE);}
        }
    });
    exit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    });
        text.requestFocus();

    }

    public static void main(String[] args) {

            JFrame frame = new Frame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setTitle("Kalkulator");

            frame.setVisible(true);


    }



}
