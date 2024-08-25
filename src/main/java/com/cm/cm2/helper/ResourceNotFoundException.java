package com.cm.cm2.helper;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String meg){
        super(meg);
    }
    public ResourceNotFoundException(){
        super("Resource not found");
    }

}
