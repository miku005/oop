package com.oop.payload;

import java.util.Date;

public class ErrorDetails {

    public Date date;
    public String message;
    public String request;

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getRequest() {
        return request;
    }

    public ErrorDetails(Date date, String message, String request) {
        this.date = date;
        this.message = message;
        this.request = request;
    }


}
