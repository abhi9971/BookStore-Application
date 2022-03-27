package com.BookStoreApplication.repository;

import com.BookStoreApplication.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    @Query(value="select * from wishlist where wishlist_id =:id",nativeQuery = true)
    public List<Wishlist> findByWishlistId(Integer id);

    @Query(value="select * from wishlist where book_id =:bookId",nativeQuery = true)
    public List<Wishlist> findByBookId(Integer bookId);

    @Query(value="select * from wishlist where user_id =:userId",nativeQuery = true)
    public List<Wishlist> findByUserId(Integer userId);
}
