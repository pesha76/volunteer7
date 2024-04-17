package com.example.buysell.controllers;

import com.example.buysell.models.Product;
import com.example.buysell.models.Anketa;
import com.example.buysell.services.AnketaService;

import com.example.buysell.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
public class AnketaController {
    private final AnketaService anketaService;

    public AnketaController(AnketaService anketaService) {
        this.anketaService = anketaService;
    }

    @PostMapping("/anketa/create")
    public String createAnketa(Anketa anketa, Principal principal) throws IOException {
        anketaService.saveAnketa(principal, anketa);

        return "redirect:/profile";
    }
}