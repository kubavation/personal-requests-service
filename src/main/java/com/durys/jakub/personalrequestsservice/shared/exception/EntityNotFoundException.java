package com.durys.jakub.personalrequestsservice.shared.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> clazz, Object id) {
        super("Entity of class %s with id %s not found".formatted(clazz.getSimpleName(), id.toString()));
    }
}
