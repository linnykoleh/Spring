package com.demo.to;

public class UserTo {

    private String username;
    private String password;
    private boolean enabled;

    public UserTo(String username, String password, boolean enabled) {
        super();
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

}
