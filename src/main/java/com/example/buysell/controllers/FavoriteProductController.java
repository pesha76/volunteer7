package com.example.buysell.controllers;

import com.example.buysell.models.FavoriteProduct;
import com.example.buysell.models.Product;
import com.example.buysell.models.User;
import com.example.buysell.services.FavoriteProductService;
import com.example.buysell.services.ProductService;
import com.example.buysell.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FavoriteProductController {
    private final FavoriteProductService favoriteProductService;
    private final ProductService productService;
    private final UserService userService;

    @PostMapping("/my/favorite/add/{id}")
    public String addToFavorites(Principal principal, @PathVariable Long id) {
        Product product = productService.getProductById(id);

        FavoriteProduct favoriteProduct = new FavoriteProduct();
        favoriteProduct.setTitle(product.getName());
        favoriteProduct.setPrice(product.getPrice());
        favoriteProduct.setCity(product.getCity());
        favoriteProduct.setDescription(product.getDescription());
        favoriteProduct.setPreviewImageId(product.getPreviewImageId());

        favoriteProductService.saveFavoriteProduct(principal, favoriteProduct);

        return "redirect:/my/favorite";
    }

    @GetMapping("/my/favorite")
    public String userFavorite(Principal principal, Model model) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("favoriteproducts", user.getFavoriteProducts());
        return "my-favorite";


    }

    @GetMapping("/favorite/{id}")
    public String favoriteinfo(@PathVariable Long id, Model model, Principal principal) {
        FavoriteProduct favoriteProduct = favoriteProductService.getFavoriteProductById(id);

        model.addAttribute("user", favoriteProductService.getUserByPrincipal(principal));
        model.addAttribute("product", favoriteProduct);
        model.addAttribute("images", favoriteProduct.getImages());
        model.addAttribute("authorProduct", favoriteProduct.getUser());
        return "product-info";
    }
    @PostMapping("/favariteproduct/delete/{id}")
    public String deleteFavoriteProduct(@PathVariable Long id, Principal principal) {
        favoriteProductService.deleteFavoriteProduct(favoriteProductService.getUserByPrincipal(principal), id);
        return "redirect:/my/favorite";
    }

}

