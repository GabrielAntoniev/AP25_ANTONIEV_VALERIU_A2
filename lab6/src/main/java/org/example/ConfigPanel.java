package org.example;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.SpinnerNumberModel;

@Getter
@Setter
public class ConfigPanel extends JPanel {

    final MainFrame frame;
    JLabel nrDotsLabel;

    PlayerScore red;
    PlayerScore blue;

    JSpinner spinner;
    JButton newGameButton;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        nrDotsLabel = new JLabel("Number of dots:");
        spinner = new JSpinner(new SpinnerNumberModel(10, 2, 200, 1));

        red = new PlayerScore(0, new JLabel("Red Player: "));
        blue = new PlayerScore(0, new JLabel("Blue Player: "));

        //player1Score.addAncestorListener();
        //player2Score.addAncestorListener();

        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this::createNewGame);

        add(nrDotsLabel);
        add(spinner);
        add(newGameButton);

        //add(red.getPlayer()); add(new JLabel(String.valueOf(red.getScore())));
        //add(blue.getPlayer());  add(new JLabel(String.valueOf(blue.getScore())));
        add(red);
        add(blue);
    }

    private void createNewGame(ActionEvent e) {
        int numDots = (Integer) spinner.getValue();
        frame.canvas.generateDots(numDots);
        frame.configPanel.red.setScore(0);
        frame.configPanel.blue.setScore(0);
        frame.canvas.repaint();
    }
}
