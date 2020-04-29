package me.justinkim.mymdbrestapi.controllers;

import me.justinkim.mymdbrestapi.exceptions.MovieNotFoundException;
import me.justinkim.mymdbrestapi.models.Actor;
import me.justinkim.mymdbrestapi.models.Movie;
import me.justinkim.mymdbrestapi.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {

    // @Autowired
    private MovieRepository movieRepository;

    MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    List<Movie> all() {
        return movieRepository.findAll();
    }

    @PostMapping
    Movie newMovie(@RequestBody Movie newMovie) {
        return movieRepository.save(newMovie);
    }

    @GetMapping("/{id}")
    Movie one(@PathVariable String id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    @PutMapping("/{id}")
    Movie replaceMovie(@RequestBody Movie newMovie, @PathVariable String id) {
        return movieRepository.findById(id)
                .map(movie -> {
                    movie.setTitle(newMovie.getTitle());
                    movie.setActors(newMovie.getActors());
                    movie.setTags(newMovie.getTags());
                    return movieRepository.save(movie);
                })
                .orElseGet(() -> {
                    newMovie.setId(id);
                    return movieRepository.save(newMovie);
                });
    }

    @DeleteMapping("/{id}")
    void deleteMovie(@PathVariable String id) {
        movieRepository.deleteById(id);
    }
}
