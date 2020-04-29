package me.justinkim.mymdbrestapi.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Entity
public class Actor {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany
    @JoinTable(
            name = "movies_starred_in",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> movies;

    public Actor() {
    }

    public Actor(Long id, String name, Set<Movie> movies) {
        this.id = id;
        this.name = name;
        this.movies = movies;
    }
}
