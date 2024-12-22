package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Генерує геттери, сеттери, метод toString(), equals(), hashCode()
@AllArgsConstructor // Генерує конструктор з усіма полями
@NoArgsConstructor  // Генерує конструктор без параметрів
@Entity //сушність бази даних - Воно саме знає при компіляції
@Table(name="tblCats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //Id - кота

    @Column(length = 150, nullable = false)
    private String name;   // Ім'я котика
    private int age;       // Вік котика
    @Column(length = 100, nullable = true)
    private String color;  // Колір котика
}
