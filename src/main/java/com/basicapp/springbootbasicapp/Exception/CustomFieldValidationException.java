package com.basicapp.springbootbasicapp.Exception;

import lombok.Getter;

@Getter
public class CustomFieldValidationException extends Exception{
    
    private String fieldName;

    public CustomFieldValidationException(String message, String fieldName){
        super(message);
        this.fieldName = fieldName;
    }
}
