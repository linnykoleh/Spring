package com.ps.exs;

public class JsonError {
    private String error;

    public JsonError(){}

    public JsonError(String message) {
        this.error = message;
    }

    public String getError() {
        return error;
    }


    public void setError(String error) {
        this.error = error;
    }
}
