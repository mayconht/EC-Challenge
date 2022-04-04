package com.ecore.challenge.resource.exception;

import java.io.Serializable;

public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer status;
    private String message;
    private Long timeStamp;

    public StandardError(final Integer status, final String message, final Long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(final Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
