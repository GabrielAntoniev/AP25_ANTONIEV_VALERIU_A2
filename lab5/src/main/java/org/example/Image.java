package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image implements Serializable { //am facut o clasa pentru ca daca era record nu mai puteam sa fac update la imagini
    private String name;
    private LocalDate date;
    private String path;
}
