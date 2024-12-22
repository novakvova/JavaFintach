package org.example;


import org.example.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Привіт козаки");
        var session = HibernateUtil.getSession();
        HibernateUtil.shutdown();
    }
}