package org.example;
import java.io.IOException;
import java.util.*;
import java.util.Dictionary;
import lombok.*;

@Getter
@Setter
public class Game {

    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final MockDictionary dictionary = new MockDictionary();
    private final List<Player> players = new ArrayList<>();

    public Game() throws IOException {
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void play() {
        List<Thread> threads = new ArrayList<>();
        for(var player : players) {
            threads.add(new Thread(player));
        }

        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
