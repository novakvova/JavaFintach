package org.example;


import org.example.entities.Cat;
import org.example.util.HibernateUtil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Привіт козаки");
        Scanner in = new Scanner(System.in);
        var session = HibernateUtil.getSession();
        try {
            // Початок транзакції
            session.beginTransaction();
            var cat = new Cat();
            System.out.println("Вкажіть назву котика");
            String temp = in.nextLine();
            cat.setName(temp);
            System.out.println("Вкажіть вік котика");
            temp = in.nextLine();
            cat.setAge(Integer.parseInt(temp));
            System.out.println("Вкажіть колір котика");
            temp = in.nextLine();
            cat.setColor(temp);
            session.persist(cat);
//            session.save(cat);
            // Завершення транзакції
            session.getTransaction().commit();

            System.out.println("Збережений студент: " + cat);
        }catch(Exception ex) {
            System.out.println("Проблема при роботі із БД "+ ex.getMessage());
            session.close();
        }
        HibernateUtil.shutdown();
    }
}