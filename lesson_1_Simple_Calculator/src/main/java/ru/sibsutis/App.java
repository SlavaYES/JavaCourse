package ru.sibsutis;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        Integer a = in.nextInt(),
                b = in.nextInt();

        String symbol = in.next();

        switch (symbol) {
            case "+":
                System.out.println(a + b);
                break;
            case "-":
                System.out.println(a - b);
                break;
            case "/":
                System.out.println(a / b);
                break;
            case "*":
                System.out.println(a * b);
                break;
        }

        in.close();
    }
}
