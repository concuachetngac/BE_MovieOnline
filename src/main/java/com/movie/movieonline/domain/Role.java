package com.movie.movieonline.domain;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="roles")
@Getter @Setter @NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;
}
