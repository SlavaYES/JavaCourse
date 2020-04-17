package ru.sibsutis;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        Developer dev = new Developer("Slava", "voen199@gmail.com", "8(913)487-08-34");
        dev.fillCSV();

        Manager man = new Manager("Slava", "voen199@gmail.com", "8(913)487-08-34");
        man.fillCSV();
        */
        Scanner in = new Scanner(System.in);

        Integer console, choice, haveEmail;

        String email = "", name = "", language = "", number = "";
        System.out.println("Console:");
        System.out.println("    1. Developer");
        System.out.println("    2. Manager");
        System.out.println("    3. Quit");

        console = in.nextInt();

        switch (console) {
            case 1: // Developer
                System.out.println("Email create?");
                System.out.println("    Yes[1], No[2]");
                haveEmail = in.nextInt();

                switch (haveEmail) {
                    case 1:
                        System.out.print("\nName: "); name = in.next();
                        System.out.print("\nEmail: "); email = in.next();
                        System.out.print("\nLanguage: "); language = in.next();
                        break;
                    case 2:
                        System.out.print("Name"); name = in.next();
                        System.out.print("\nLanguage: "); language = in.next();
                        break;
                    default:
                        System.out.println("Error command");
                        break;
                }

                System.out.println("Console Developers");
                System.out.println("    1. Add");
                System.out.println("    2. Find and Remove");
                System.out.println("    3. Cancel");

                choice = in.nextInt();
                switch (choice) {
                    case 1:
                        Developer developer = new Developer(name, email, language);
                        developer.toCSV();
                        break;
                    case 2:
                        Developer developer1 = new Developer(name, email, language);
                        developer1.Remove();
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Error command");
                        break;
                }
                break;
            case 2: // Manager
                System.out.println("Create email?");
                System.out.println("    Yes[1], No[2]");

                haveEmail = in.nextInt();
                switch (haveEmail) {
                    case 1:
                        System.out.println("Name: "); name = in.next();
                        System.out.println("Email: "); email = in.next();
                        System.out.println("Number"); number = in.next();
                        break;
                    case 2:
                        System.out.println("Name: "); name = in.next();
                        System.out.println("Number"); number = in.next();
                        break;
                    default:
                        System.out.println("Error command");
                        break;
                }

                System.out.println("Console Managers");
                System.out.println("    1. Add");
                System.out.println("    2. Find and Remove");
                System.out.println("    3. Cancel");

                choice = in.nextInt();
                switch (choice) {
                    case 1:
                        Manager manager = new Manager(name, email, number);
                        manager.toCSV();
                        break;
                    case 2:
                        Manager manager1 = new Manager(name, email, number);
                        manager1.remove();
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Error command");
                        break;
                }

                break;
            case 3: // Quit
                break;
            default:
                System.out.println("Error command");
        }
    }
}