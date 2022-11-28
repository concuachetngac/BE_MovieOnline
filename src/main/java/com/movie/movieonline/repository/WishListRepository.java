package com.movie.movieonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.movieonline.domain.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    
}
