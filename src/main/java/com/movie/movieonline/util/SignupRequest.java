package com.movie.movieonline.util;

import java.util.Set;

public class SignupRequest {
    private String username;
    private String password;
    private Set<String> role;

    public String getPassword() {
        return password;
    }

    public Set<String> getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }
}
