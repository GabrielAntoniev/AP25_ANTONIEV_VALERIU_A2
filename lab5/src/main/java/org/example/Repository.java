package org.example;

import lombok.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import java.lang.reflect.Array;
import java.util.LinkedList;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Repository {

    private String name;
    private List<Image> content = new LinkedList<>();

    public void addImage(Image img){
        content.add(img);
    }

    public void viewImage(String name) throws IOException {
        Desktop d = Desktop.getDesktop();
        Image image =null;
        for(var i : content){
            if(i.name().equals(name)) {
                image = i;
                break;
            }
        }
        if(image!=null) {
            File img = new File(image.path());
            d.open(img);
        }
    }
}
