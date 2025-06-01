package org.example;
import lombok.*;
import java.util.*;

@Getter
@Setter
public class Player implements Runnable{

    private String name;
    private Game game;
    private boolean running = true;
    private int score = 0;
    private int[][] dictionary_table;
    private List<Tile> extracted;

    public Player(String name, Game game) { this.name = name; this.game = game; dictionary_table = game.getDictionary().getFrequencyTable();
    }

    private void submitWord() throws InterruptedException {
        while (running) {
            extracted=game.getBag().extractTiles(20);

            if (extracted.isEmpty()) {
                running = false;
                return;
            }

            int[] letters_fr = new int[26];
            for (Tile tile : extracted) {
                letters_fr[tile.getLetter() - 'a']++;
            }

            String bestWord = null;
            int bestLen = 0;

            List<String> dictionary = game.getDictionary().getDictionary();

            for (String candidate : dictionary) {
                int[] candidateLetters = new int[26];
                for (char c : candidate.toCharArray()) {
                    candidateLetters[c - 'a']++;
                }
                boolean canForm = true;
                for (int i = 0; i < 26; i++) {
                    if (candidateLetters[i] > letters_fr[i]) {
                        canForm = false;
                        break;
                    }
                }
                if (canForm && candidate.length() > bestLen) {
                    bestWord = candidate;
                    bestLen = candidate.length();
                }
            }

            if (bestWord != null) {
                score++;
                game.getBoard().addWord(this, bestWord);
            }

            Thread.sleep(500);
        }
    }

    @Override
    public void run() {
        try {
            submitWord();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}