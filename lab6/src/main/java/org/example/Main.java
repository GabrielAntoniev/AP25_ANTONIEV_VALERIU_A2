package org.example;

import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {
        //compulsory
        SwingUtilities.invokeLater(MainFrame::new);
    }
}