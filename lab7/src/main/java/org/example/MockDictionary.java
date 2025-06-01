package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.*;
@Getter
@Setter
public class MockDictionary {

    private final List<String> dictionary = new ArrayList<>();

    public int[][] getFrequencyTable() {

        int[][] table = new int[dictionary.size()][26];
        int i = 0;
        for(var s : dictionary){
            //System.out.println(s);
            for(int j = 0; j < s.length(); j++){
                table[i][s.charAt(j) - 'a'] ++;
            }
            i++;
        }
        return table;
    }

    public MockDictionary() throws IOException {

        try (BufferedReader br = Files.newBufferedReader(Paths.get("src/main/resources/dictionary.txt"  ))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(!line.isEmpty()){
                    dictionary.add(line.toLowerCase());
                }
            }
        }
        catch(IOException e){e.printStackTrace();}
    }

    public boolean isWord(String str) {
        return dictionary.contains(str);
    }
    /*@Override
    public int size(){

    }*/
}
