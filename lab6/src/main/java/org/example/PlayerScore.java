package org.example;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class PlayerScore extends JPanel {

    private int score;
    //private final JLabel playerLabel;
    private final JLabel scoreLabel;

    public PlayerScore(int score, JLabel playerLabel) {
        this.score = score;
        //this.playerLabel = playerLabel;
        this.scoreLabel = new JLabel(String.valueOf(score));

        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(playerLabel);
        add(scoreLabel);
    }

    public void setScore(int newScore) {
        this.score = newScore;
        scoreLabel.setText(String.valueOf(score));
    }

}
