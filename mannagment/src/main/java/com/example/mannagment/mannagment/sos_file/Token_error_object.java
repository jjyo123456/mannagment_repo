package com.example.mannagment.mannagment.sos_file;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public class Token_error_object {
    private String message;
    private HttpStatus status;
    private Timestamp timestamp;

    public Token_error_object(String message, HttpStatus status, Timestamp timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
