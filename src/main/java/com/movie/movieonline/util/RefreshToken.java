package com.movie.movieonline.util;

import java.time.Instant;

import javax.persistence.*;

import com.movie.movieonline.domain.User;

import lombok.*;
@Getter @Setter @NoArgsConstructor
@Entity(name = "refreshtoken")
public class RefreshToken {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @Column(nullable = false, unique = true)
  private String token;

  @Column(nullable = false)
  private Instant expiryDate;

  //getters and setters

}