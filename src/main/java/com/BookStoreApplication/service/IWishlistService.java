package com.BookStoreApplication.service;

import com.BookStoreApplication.dto.WishlistDTO;
import com.BookStoreApplication.model.Wishlist;

import java.util.List;

public interface IWishlistService {
    public Wishlist addToWishlist(WishlistDTO dto);

    public List<Wishlist> getAllWishlists();

    public List<Wishlist> getWishlistRecordById(Integer id);

    public List<Wishlist> getWishlistRecordByBookId(Integer bookId);

    public List<Wishlist> getWishlistRecordByUserId(Integer userId);

    public Wishlist deleteWishlistRecordById(Integer id);
}
