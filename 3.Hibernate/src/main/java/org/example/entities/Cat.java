package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Генерує геттери, сеттери, метод toString(), equals(), hashCode()
@AllArgsConstructor // Генерує конструктор з усіма полями
@NoArgsConstructor  // Генерує конструктор без параметрів
@Entity //сушність бази даних - Воно саме знає при компіляції
@Table(name="tblCats")
public class Cat {
    private String name;   // Ім'я котика
    private int age;       // Вік котика
    private String color;  // Колір котика
}
