package com.movie.movieonline.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.*;

@Entity
@Table(name="users")
@JsonIdentityInfo(
   generator = ObjectIdGenerators.PropertyGenerator.class,
   property = "username")
@Getter @Setter @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private Long id;

    @Column(name="username")
    private String username;

    @JsonIgnore
    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;

    @Column(name="address")
    private String address;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="birth")
    private Date birth;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", targetEntity = WishList.class, cascade = CascadeType.ALL)
    private List<WishList> wishLists;

    @JsonIgnore
    @OneToMany(mappedBy = "user", targetEntity = Comment.class, cascade = CascadeType.ALL)
    private List<Comment> comments;

    public User(String username, String password){
        this.username=username;
        this.password=password;
    }
}
