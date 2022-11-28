package com.movie.movieonline.util;

import lombok.*;

@Getter @Setter @NoArgsConstructor
public class TokenRequest {
    private String token;

    public TokenRequest(String token){
        this.token=token;
    }

}
