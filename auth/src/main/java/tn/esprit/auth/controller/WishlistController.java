package tn.esprit.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.auth.entity.Livre;
import tn.esprit.auth.entity.WishList;
import tn.esprit.auth.model.Response;
import tn.esprit.auth.service.WishlistService;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService service;

    @Autowired
    public WishlistController(WishlistService wishlistSer) {
        this.service = wishlistSer;
    }

    //	------------------READ
    @GetMapping("/{wishlistId}")
    public Response<WishList> findById(@PathVariable Long wishlistId)
    {
        return service.findById(wishlistId);
    }

    @GetMapping("/{wishlistId}/livres")
    public List<Livre> findAllBooks(@PathVariable Long wishlistId)
    {
        return service.findAllBooks(wishlistId);
    }

    @GetMapping("/{wishlistId}/livre/{livreId}")
    public Response<Livre> findBookById(@PathVariable Long wishlistId, @PathVariable Long livreId )
    {
        return service.findBookById(wishlistId);
    }

    //	------------------ADD
    @PostMapping("")
    public WishList save(@RequestBody WishList wishlist)
    {
        return service.save(wishlist);
    }


    //	------------------UPDATE
    @PutMapping("/{wishlistId}")
    public Response<WishList> updateWishlistById(@PathVariable Long wishlistId, @RequestBody WishList wishlist)
    {
        return service.update(wishlist, wishlistId);
    }


    //	------------------DELETE
    @DeleteMapping("/{wishlistId}")
    public Response<Boolean> deleteById(@PathVariable Long wishlistId)
    {
        return service.deleteById(wishlistId);
    }



}
