package com.example.segundoauqui.wnkedapp;

/**
 * Created by segundoauqui on 8/15/17.
 */

class MessageEvent {


    String Action;
    String message;

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageEvent(String action, String message) {
        Action = action;
        this.message = message;
    }
}
