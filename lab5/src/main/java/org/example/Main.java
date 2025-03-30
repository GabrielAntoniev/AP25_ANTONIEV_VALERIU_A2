package org.example;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        compulsory();
    }

    public static void compulsory(){
        String path = "/home/gabi/Pictures/image.jpeg";
        var img = new Image("image",null, path);
        var repo = new Repository();
        repo.addImage(img);
        try {
            repo.viewImage("image.jpeg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}