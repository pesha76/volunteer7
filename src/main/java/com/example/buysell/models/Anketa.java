package com.example.buysell.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "anketas")
@Data
public class Anketa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Добавлено явное указание обязательности поля
    private String fio;

    @Column(nullable = false) // Добавлено явное указание обязательности поля
    private String description;

    @Column(nullable = false) // Добавлено явное указание обязательности поля
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // Явное указание имени столбца и его обязательности
    private User user;
}


