package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //int, double, String, float, short, long, char
        System.out.println("Вкажіть розмір масиву:");
        int n;
        String str = in.nextLine();
        n = Integer.parseInt(str);
        int [] mas = new int[n];

        for (int i=0;i<n;i++) {
            mas[i] = getRandom(18,45);
        }

        for (int item : mas) {
            System.out.print(item+"\t");
        }
        System.out.println();
    }

    public static int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}