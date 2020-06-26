package com.dengjunwu.exception;

/**
 * @author dengjunwu
 */
public class ResourceNotFoundException extends RuntimeException {
    private RestResponseStatus restResponseStatus;

    public ResourceNotFoundException(RestResponseStatus restResponseStatus, String e) {
        super(e);
        this.restResponseStatus = restResponseStatus;
    }

    public ResourceNotFoundException(String e) {
        super(e);
    }

    public ResourceNotFoundException(Exception e) {
        super(e);
    }
}
