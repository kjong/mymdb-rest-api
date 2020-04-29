package me.justinkim.mymdbrestapi.controllers;

import me.justinkim.mymdbrestapi.exceptions.ActorNotFoundException;
import me.justinkim.mymdbrestapi.models.Actor;
import me.justinkim.mymdbrestapi.repositories.ActorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/actors")
public class ActorController {

    private ActorRepository actorRepository;

    ActorController(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @GetMapping
    List<Actor> all() {
        return actorRepository.findAll();
    }

    @PostMapping
    Actor newActor(@RequestBody Actor newActor) {
        return actorRepository.save(newActor);
    }

    @GetMapping("/{id}")
    Actor one(@PathVariable Long id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(id));
    }

    @PutMapping("/{id}")
    Actor replaceActor(@RequestBody Actor newActor, @PathVariable Long id) {
        return actorRepository.findById(id)
                .map(actor -> {
                    actor.setName(newActor.getName());
                    actor.setMovies(newActor.getMovies());
                    return actorRepository.save(actor);
                })
                .orElseGet(() -> {
                    newActor.setId(id);
                    return actorRepository.save(newActor);
                });
    }

    @DeleteMapping("/{id}")
    void deleteActor(@PathVariable Long id) {
        actorRepository.deleteById(id);
    }
}
