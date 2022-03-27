package com.BookStoreApplication.service;

import com.BookStoreApplication.dto.WishlistDTO;
import com.BookStoreApplication.exception.BookStoreException;
import com.BookStoreApplication.model.Book;
import com.BookStoreApplication.model.UserRegistration;
import com.BookStoreApplication.model.Wishlist;
import com.BookStoreApplication.repository.BookStoreRepository;
import com.BookStoreApplication.repository.UserRegistrationRepository;
import com.BookStoreApplication.repository.WishlistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class WishlistService implements IWishlistService {
    @Autowired
    private WishlistRepository repo;

    @Autowired
    private UserRegistrationRepository userRepo;

    @Autowired
    private BookStoreRepository bookRepo;

    @Override
    public Wishlist addToWishlist(WishlistDTO dto) {
        // TODO Auto-generated method stub
        Optional<UserRegistration> user = userRepo.findById(dto.getUserId());
        Optional<Book> book = bookRepo.findById(dto.getBookId());
        if(user.isPresent() && book.isPresent()) {
            Wishlist newWishList = new Wishlist(user.get(),book.get());
            repo.save(newWishList);
            log.info("Wishlist added successfully");
            return newWishList;
        }
        else {
            throw new BookStoreException("User or Book doesn't exists");
        }
    }

    @Override
    public List<Wishlist> getAllWishlists() {
        List<Wishlist> list = repo.findAll();
        return list;
    }
    public List<Wishlist> getWishlistRecordById(Integer id){
        List<Wishlist> list = repo.findByWishlistId(id);
        if(list.isEmpty()) {
            throw new BookStoreException("Wishlist doesn't exists for id "+id);
        }
        else {
            return list;
        }
    }

    @Override
    public List<Wishlist> getWishlistRecordByBookId(Integer bookId) {
        List<Wishlist> list = repo.findByBookId(bookId);
        if(list.isEmpty()) {
//			throw new BookStoreException("Wishlist doesn't exists for book id "+bookId);
            return null;
        }
        else {
            return list;
        }
    }
    @Override
    public List<Wishlist> getWishlistRecordByUserId(Integer userId) {
        List<Wishlist> list = repo.findByUserId(userId);
//		if(list.isEmpty()) {
////			throw new BookStoreException("Wishlist doesn't exists for book id "+bookId);
//			return null;
//		}
//		else {
        return list;

    }

    public Wishlist deleteWishlistRecordById(Integer Id) {
        Optional<Wishlist> list = repo.findById(Id);
        repo.deleteById(Id);
        return list.get();
    }


}
