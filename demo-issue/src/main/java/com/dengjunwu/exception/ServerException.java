package com.dengjunwu.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author unknown
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServerException extends RuntimeException {
    private RestResponseStatus restResponseStatus;

    public ServerException(RestResponseStatus restResponseStatus) {
        super(restResponseStatus.getDesc());
        this.restResponseStatus = restResponseStatus;
    }

    public ServerException(RestResponseStatus restResponseStatus, Exception e) {
        super(e);
        this.restResponseStatus = restResponseStatus;
    }

    public ServerException(RestResponseStatus restResponseStatus, String e) {
        super(e);
        this.restResponseStatus = restResponseStatus;
    }

    public ServerException(RestResponseStatus restResponseStatus, Throwable e) {
        super(e);
        this.restResponseStatus = restResponseStatus;
    }

    public ServerException(String message) {
        super(message);
        this.restResponseStatus = RestResponseStatus.COMMON_ERROR_STATUS;
    }

    public ServerException() {
        this.restResponseStatus = RestResponseStatus.COMMON_ERROR_STATUS;
    }

    public static ServerException wrongParam() {
        return new ServerException(RestResponseStatus.PARAM_ERROR);
    }

    public static ServerException wrongParam(String msg) {
        return new ServerException(RestResponseStatus.PARAM_ERROR, msg);
    }

    public static ServerException serverFailed(String msg) {
        return new ServerException(RestResponseStatus.COMMON_ERROR_STATUS, msg);
    }

    public static ServerException serverFailed() {
        return new ServerException(RestResponseStatus.COMMON_ERROR_STATUS);
    }
}
