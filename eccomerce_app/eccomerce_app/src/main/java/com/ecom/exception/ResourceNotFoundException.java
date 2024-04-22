package com.ecom.exception;


public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException()
    {
        super("Resource you're looking is not found on server...!");
    }

    public ResourceNotFoundException(String message)
    {
        super(message); ;
    }

}
