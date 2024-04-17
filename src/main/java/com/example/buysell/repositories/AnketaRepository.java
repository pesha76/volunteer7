package com.example.buysell.repositories;

import com.example.buysell.models.Anketa;
import com.example.buysell.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnketaRepository extends JpaRepository<Anketa, Long> {
    List<Anketa> findByfio(String fio);
    // Ваш репозиторий может содержать дополнительные методы, если необходимо
}