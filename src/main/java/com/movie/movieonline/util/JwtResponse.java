package com.movie.movieonline.util;

import java.util.List;

import lombok.*;

@Getter @Setter @NoArgsConstructor
public class JwtResponse {

    private String token;
    private String username;
    private Long id;
    private List<String> roles;
    private String refreshToken;
    private String tokenType = "Bearer" ;

    public JwtResponse(String jwt, Long id, String username, List<String> roles, String refreshToken) {
        
        this.refreshToken = refreshToken;
        this.id=id;
        this.token= jwt;
        this.username = username;
        this.roles = roles;
    }
}
