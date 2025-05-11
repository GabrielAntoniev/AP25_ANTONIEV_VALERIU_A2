package org.example;

import lombok.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import java.lang.reflect.Array;
import java.util.LinkedList;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Repository implements Serializable {

    private String name;
    private List<Image> content = new LinkedList<>();

    public void addImage(Image img){
        content.add(img);
    }

    public void viewImage(String name) throws IOException {
        Desktop d = Desktop.getDesktop();
        Image image =null;
        for(var i : content){
            if(i.getName().equals(name)) {
                image = i;
                break;
            }
        }
        if(image!=null) {
            File img = new File(image.getPath());
            d.open(img);
        }
    }
}
