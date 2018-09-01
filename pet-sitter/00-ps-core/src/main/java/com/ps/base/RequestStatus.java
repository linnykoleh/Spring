package com.ps.base;

public enum RequestStatus {
    NEW(1),
    SOLVED(2),
    ANULLED(3);

    private int code;

    RequestStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
