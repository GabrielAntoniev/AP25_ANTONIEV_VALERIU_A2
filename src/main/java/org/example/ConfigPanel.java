package org.example;

import javax.swing.*;

import java.awt.event.*;
import javax.swing.SpinnerNumberModel;

public class ConfigPanel extends JPanel {

    final MainFrame frame;
    JLabel label;
    JSpinner spinner;
    JButton newGameButton;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        label = new JLabel("Number of dots:");
        spinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));

        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this::createNewGame);
        add(label);
        add(spinner);
        add(newGameButton);
    }

    private void createNewGame(ActionEvent e) {
        int numDots = (Integer) spinner.getValue();
        frame.canvas.generateDots(numDots);
        frame.canvas.repaint();
    }
}
