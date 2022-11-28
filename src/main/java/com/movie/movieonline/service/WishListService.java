package com.movie.movieonline.service;
import com.movie.movieonline.domain.WishList;

public interface WishListService {
    void addToWishList(WishList wishList);
    void deleteFromWishList(Long id);
}
