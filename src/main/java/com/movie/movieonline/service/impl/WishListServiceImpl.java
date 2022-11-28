package com.movie.movieonline.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.movie.movieonline.domain.WishList;
import com.movie.movieonline.exception.ResourceNotFoundException;
import com.movie.movieonline.repository.WishListRepository;
import com.movie.movieonline.service.WishListService;

@Service
public class WishListServiceImpl implements WishListService {

    @Autowired
    WishListRepository wishListRepository;


    @Override
    public void addToWishList(WishList wishList) {
        wishListRepository.save(wishList);
        
    }

    @Override
    public void deleteFromWishList(Long id) {
        wishListRepository.findById(id).orElseThrow(() -> 
                                new ResourceNotFoundException("WishList", "Id", id));
        wishListRepository.deleteById(id);
        
    }
    
}
