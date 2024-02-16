package com.example.buysell.repositories;

import com.example.buysell.models.FavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long> {
    List<FavoriteProduct> findByTitle(String title);
    Optional<FavoriteProduct> findByPreviewImageId(Long previewImageId);
    List<FavoriteProduct> getFavProductsByPreviewImageId(Long previewImageId);
}

