package com.example.buysell.services;

import com.example.buysell.models.Anketa;
import com.example.buysell.models.User;
import com.example.buysell.repositories.AnketaRepository;
import com.example.buysell.repositories.UserRepository;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.security.Principal;

import java.util.List;

@Slf4j
@Service

public class AnketaService {
    private final UserRepository userRepository;
    private final AnketaRepository anketaRepository;


    public AnketaService(UserRepository userRepository, AnketaRepository anketaRepository) {
        this.userRepository = userRepository;
        this.anketaRepository = anketaRepository;

    }
    public List<Anketa> listAnketas(String fio) {
        if (fio != null) return anketaRepository.findByfio(fio);
        return anketaRepository.findAll();
    }


    public void saveAnketa(Principal principal, Anketa anketa) throws IOException {
        User user = getUserByPrincipal(principal);
        anketa.setUser(user);
        log.info("Saving new tanker.");
        // Проверяем роли пользователя из базы данных и устанавливаем роль ROLE_ORG, если она еще не установлена
        user.setOrg(true);
        try {
            anketaRepository.save(anketa);
        } catch (Exception e) {
            log.error("Error occurred while saving tanker: " + e.getMessage());
            throw new IOException("Error occurred while saving tanker", e);
        }
    }
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }
}
