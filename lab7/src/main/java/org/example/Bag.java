package org.example;
import java.util.Collections.*;
import java.util.*;
import lombok.*;


@Getter
public class Bag {
    private final List<Tile> bagContent = new LinkedList<>();

    public Bag() {
        Random r = new Random();
        for (char c = 'a'; c <= 'z'; c++) {
            int value =  r.nextInt(10);
            for(int i = 0; i < 100; i++) {
                bagContent.add(new Tile(c, value));
            }
        }
    }
    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();


        for(int i = 0; i < howMany && !bagContent.isEmpty(); i++) {
            int pos = (int)(Math.random()*10%bagContent.size());
            extracted.add(bagContent.get(pos));
            bagContent.remove(pos);
        }
        return extracted;
    }
}
