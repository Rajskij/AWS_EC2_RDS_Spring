package com.crud.app.product.buisness.exception;

public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -324534242424324L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
