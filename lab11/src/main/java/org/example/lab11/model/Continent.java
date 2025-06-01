package org.example.lab11.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "continents")
@NamedQueries({
        @NamedQuery(name = "Continent.findAll", query = "SELECT e FROM Continent e"),
        @NamedQuery(name = "Continent.findByName", query = "SELECT e FROM Continent e WHERE e.name = :name")
})

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Continent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public Continent(String name) {
        this.name = name;
    }
}
