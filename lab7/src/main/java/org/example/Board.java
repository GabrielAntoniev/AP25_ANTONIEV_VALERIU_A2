package org.example;
import lombok.*;
import java.util.*;

@ToString
public class Board {

    private final List<String> words = new ArrayList<>();

    public synchronized void addWord(Player player, String word) {
        words.add(word);
        System.out.println(player.getName() + ": " + word);
    }

}