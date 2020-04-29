package me.justinkim.mymdbrestapi.exceptions;

public class ActorNotFoundException extends RuntimeException {

    public ActorNotFoundException(Long id) {
        super("Could not find actor " + id);
    }
}
