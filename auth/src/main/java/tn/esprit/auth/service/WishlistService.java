package tn.esprit.auth.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.auth.entity.Livre;
import tn.esprit.auth.entity.Offre;
import tn.esprit.auth.entity.WishList;
import tn.esprit.auth.model.Response;
import tn.esprit.auth.model.ResponseService;
import tn.esprit.auth.repository.LivreRepository;
import tn.esprit.auth.repository.OffreRepository;
import tn.esprit.auth.repository.WishlistRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {
    private static Logger logger = LogManager.getLogger(WishlistService.class);

    @Autowired
    private WishlistRepository WishlistRepo;

    @Autowired
    private LivreRepository livreRepo;

    //	------------------READ

    public List<Livre> findAllBooks(Long wishlistId) {
        if(existsById(wishlistId))
            return WishlistRepo.findById(wishlistId).get().getLivres();
        else
            return null;
    }

    public Response<Livre> findBookById(Long wishlistId) {
        if (livreRepo.existsByWishlistReference(wishlistId)) {
            return new ResponseService<Livre>().getSuccessResponse(livreRepo.findByWishlistReference(wishlistId));
        } else
            return new ResponseService<Livre>().getFailedResponse("The book does not exist in this wishlist ...");
    }

    public Response<WishList> findById(Long id) {
        if (existsById(id)) {
            return new ResponseService<WishList>().getSuccessResponse(WishlistRepo.findById(id).get());
        } else
            return new ResponseService<WishList>().getFailedResponse("This wishlist does not exist...");
    }

    public boolean existsById(Long id) {
        return (WishlistRepo.existsById(id) && WishlistRepo.findById(id).get().isCreated());
    }

    //	------------------ADD
    public <S extends WishList> S save(S wishlist) {
        return WishlistRepo.save(wishlist);
    }


    //	------------------UPDATE
    public Response<WishList> update(WishList wishlist, Long Wid) {
        if (existsById(Wid)) {
            wishlist.setId(Wid);
            return new ResponseService<WishList>().getSuccessResponse(WishlistRepo.save(wishlist));
        } else
            return new ResponseService<WishList>().getFailedResponse("this wishlist does not exist...");
    }

    //	------------------DELETE
    public Response<Boolean> deleteById(Long id) {
        if (existsById(id)) {
            WishlistRepo.deleteById(id);
            return new ResponseService<Boolean>().getSuccessResponse(true);
        } else
            return new ResponseService<Boolean>().getFailedResponse("this wishlist does not exist...");
    }


}
