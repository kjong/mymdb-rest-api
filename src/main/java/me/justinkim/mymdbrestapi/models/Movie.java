package me.justinkim.mymdbrestapi.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Entity
public class Movie {

    @Id
    private String id;

    @NotBlank
    private String title;

    @ManyToMany(mappedBy = "movies")
    private Set<Actor> actors;

    private String[] tags;

    public Movie() {
    }

    public Movie(String id, String title, Set<Actor> actors, String[] tags) {
        this.id = id;
        this.title = title;
        this.actors = actors;
        this.tags = tags;
    }
}
