package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class ControlPanel extends JPanel {
    final MainFrame frame;

    JButton loadButton = new JButton("Load");
    JButton saveButton = new JButton("Save");
    JButton exitButton = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));

        add(loadButton);
        add(saveButton);
        add(exitButton);
        loadButton.addActionListener(this::loadGame);
        saveButton.addActionListener(this::saveGame);
        exitButton.addActionListener(this::exitGame);
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

    private void loadGame(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Inca nu am facut load");
    }

    private void saveGame(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "inca nu am facut save");
    }

}
