package com.BookStoreApplication.controller;

import com.BookStoreApplication.dto.CartDTO;
import com.BookStoreApplication.dto.ResponseDTO;
import com.BookStoreApplication.model.Cart;
import com.BookStoreApplication.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

    @Autowired
    ICartService cartService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> insertItem(@Valid @RequestBody CartDTO cartdto) {
        Cart newCart = cartService.insertItems(cartdto);
        ResponseDTO responseDTO = new ResponseDTO("User registered successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseDTO getAllCartDetails() {
        ResponseDTO responseDTO = cartService.getCartDetails();
        return responseDTO;
    }

    @GetMapping("/getById/{cartId}")
    public ResponseEntity<ResponseDTO> getCartDetailsById(@PathVariable Integer cartId){
        Optional<Cart> specificCartDetail=cartService.getCartDetailsById(cartId);
        ResponseDTO responseDTO=new ResponseDTO("Cart details retrieved successfully",specificCartDetail);
        return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
    }
    @GetMapping("/retrieveCartByBookId/{bookID}")
    public ResponseEntity<ResponseDTO> getCartRecordByBookId(@PathVariable Integer bookID){
        Cart newCart = cartService.getCartRecordByBookId(bookID);
        ResponseDTO dto = new ResponseDTO("Record retrieved successfully !",newCart);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @GetMapping("/increaseQuantity/{id}")
    public ResponseEntity<ResponseDTO> increaseQuantity(@PathVariable Integer id){
        Cart newCart = cartService.increaseQuantity(id);
        ResponseDTO dto = new ResponseDTO("Quantity for book record updated successfully !",newCart);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @GetMapping("/decreaseQuantity/{id}")
    public ResponseEntity<ResponseDTO> decreaseQuantity(@PathVariable Integer id){
        Cart newCart = cartService.decreaseQuantity(id);
        ResponseDTO dto = new ResponseDTO("Quantity for book record updated successfully !",newCart);
        return new ResponseEntity(dto,HttpStatus.OK);
    }
//    @DeleteMapping("/delete/{cartId}")
//    public ResponseEntity<ResponseDTO> deleteCartById(@PathVariable Integer cartId){
////        Optional<Cart> delete=cartService.deleteCartItemById(cartId);
//        ResponseDTO responseDTO=new ResponseDTO("Cart delete successfully", cartService.deleteCartItemById(cartId));
//        return new ResponseEntity(responseDTO,HttpStatus.OK);
//    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ResponseDTO> deleteCartById(@PathVariable Integer cartId) {
        Optional<Cart> delete = cartService.deleteCartItemById(cartId);
        ResponseDTO responseDTO = new ResponseDTO("Cart delete successfully", delete);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/updateById/{cartId}")
    public ResponseEntity<ResponseDTO> updateCartById(@PathVariable Integer cartId,@Valid @RequestBody CartDTO cartDTO){
        Cart updateRecord = cartService.updateRecordById(cartId,cartDTO);
        ResponseDTO dto = new ResponseDTO(" Cart Record updated successfully by Id",updateRecord);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }
    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable Integer id,@RequestParam Integer quantity){
        Cart newCart = cartService.updateQuantity(id,quantity);
        ResponseDTO dto = new ResponseDTO("Quantity for book record updated successfully !",newCart);
        return new ResponseEntity(dto,HttpStatus.OK);
    }
}
