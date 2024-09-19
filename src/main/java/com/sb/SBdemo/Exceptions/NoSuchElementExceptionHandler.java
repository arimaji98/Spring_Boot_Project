package com.sb.SBdemo.Exceptions;

import lombok.*;

@Data
public class NoSuchElementExceptionHandler extends RuntimeException {
    String resourceName;
    String fieldName;
    int fieldValue;
    String fieldStringValue;

    public NoSuchElementExceptionHandler(String resourceName, String fieldName, int fieldValue) {
        super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public NoSuchElementExceptionHandler(String resourceName, String fieldName, String fieldStringValue) {
        super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldStringValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldStringValue = fieldStringValue;
    }
}
