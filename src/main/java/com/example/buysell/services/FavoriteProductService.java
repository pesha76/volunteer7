package com.example.buysell.services;

import com.example.buysell.models.FavoriteProduct;
import com.example.buysell.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.buysell.repositories.FavoriteProductRepository;
import com.example.buysell.models.User;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class FavoriteProductService {
    private final UserRepository userRepository;


    @Autowired

    private FavoriteProductRepository favoriteProductRepository;
    public List<FavoriteProduct> listFavoriteProducts(String title) {
        if (title != null) return favoriteProductRepository.findByTitle(title);
        return favoriteProductRepository.findAll();
    }

    public void saveFavoriteProduct(Principal principal, FavoriteProduct favoriteProduct) {
        User user = getUserByPrincipal(principal);

        favoriteProduct.setUser(user);

        favoriteProductRepository.save(favoriteProduct);
        log.info("Saving new favProduct");
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }
    public FavoriteProduct getFavoriteProductById(Long id) {
        return favoriteProductRepository.findById(id).orElse(null);
    }
    public void deleteFavoriteProduct(User user, Long id) {
        FavoriteProduct favoriteProduct = favoriteProductRepository.findById(id)
                .orElse(null);
        if (favoriteProduct != null) {
            if (favoriteProduct.getUser().getId().equals(user.getId())) {
                favoriteProductRepository.delete(favoriteProduct);
                log.info("Product with id = {} was deleted", id);
            } else {
                log.error("User: {} haven't this product with id = {}", user.getEmail(), id);
            }
        } else {
            log.error("Product with id = {} is not found", id);
        }    }
}