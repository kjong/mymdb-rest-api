package me.justinkim.mymdbrestapi.repositories;

import me.justinkim.mymdbrestapi.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
