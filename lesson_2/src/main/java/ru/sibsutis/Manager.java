package ru.sibsutis;

import java.io.*;
import java.util.Scanner;

public class Manager extends User implements CSV {

    protected String number;

    public Manager(String name, String email, String number) {
        super(name, email);
        this.number = number;
    }

    @Override
    public String toCSV() {

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("res/manager.csv"));
            writer.write(getName() + " " + getEmail() + " " + this.number + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void remove() {
        fromCSV(getName(), getEmail(), this.number);
    }

    @Override
    public void fromCSV(String name, String email, String number) {
        try {
            FileWriter writer = new FileWriter("res/manager1.csv");
            FileReader reader = new FileReader("res/manager.csv");

            Scanner in = new Scanner(reader);

            while (in.hasNext()) {
                String str = in.nextLine();
                String a[] = str.split(" ");
                if (!a[0].equals(name) && !a[1].equals(email) && !a[2].equals(number)) {
                    writer.write(str + "\n");
                }
            }


            File manager = new File("res/manager.csv");
            manager.delete();
            File manager1 = new File("res/manager1.csv");
            manager1.renameTo(manager);

            writer.close(); reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void fillCSV() {

        String[] arName = new String[1000];
        String[] arEmail = new String[1000];
        String[] arNumber = new String[1000];

        arrayName(arName);
        arrayEmail(arEmail);
        arrayLanguage(arNumber);

        try {

            FileWriter writer = new FileWriter("res/manager.csv");

            for (Integer i = 0; i < 1000; i++) {
                writer.write(arName[i] + " " + arEmail[i] + " " + arNumber[i] + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void arrayLanguage(String[] number) {

        String[] cod = new String[10];
        String[] end1 = new String[10];
        String[] end2 = new String[10];
        String[] begin = new String[2];
        String[] numbers = new String[10];

        begin[0] = "+8"; begin[1] = "+7";
        end1[0] = "-23"; end1[1] = "-23"; end1[2] = "-85";
        end1[3] = "-31"; end1[4] = "-34"; end1[5] = "-43";
        end1[6] = "-34"; end1[7] = "-23"; end1[8] = "-65";
        end1[9] = "-64";

        end2[0] = "-25"; end2[1] = "-43"; end2[2] = "-35";
        end2[3] = "-31"; end2[4] = "-24"; end2[5] = "-43";
        end2[6] = "-35"; end2[7] = "-43"; end2[8] = "-74";
        end2[9] = "-66";

        cod[0] = "213"; cod[1] = "423"; cod[2] = "865";
        cod[3] = "321"; cod[4] = "234"; cod[5] = "423";
        cod[6] = "345"; cod[7] = "423"; cod[8] = "654";
        cod[9] = "646";

        numbers[0] = "(913)"; numbers[1] = "(987)"; numbers[2] = "(911)";
        numbers[3] = "(999)"; numbers[4] = "(988)"; numbers[5] = "(989)";
        numbers[6] = "(923)"; numbers[7] = "(983)"; numbers[8] = "(898)";
        numbers[9] = "(993)";

        for (Integer i = 0; i < 1000; i++) {
            number[i] = begin[(int) (Math.random() * 2)]
                                + numbers[(int) (Math.random() * 10)]
                                + cod[(int) (Math.random() * 10)]
                                + end1[(int) (Math.random() * 10)]
                                + end2[(int) (Math.random() * 10)];
        }
    }

    public void arrayName(String[] name) {
        name[0] = "Катерина"; name[1] = "Андрей"; name[2] = "Семен";
        name[3] = "Святослав"; name[4] = "Федер"; name[5] = "Даниил";
        name[6] = "Михаил"; name[7] = "Никита"; name[8] = "Павел";
        name[9] = "Марина";

        for (Integer i = 10; i < 1000; i++) {
            name[i] = name[(int) (Math.random() * 10)];
        }
    }

    public void arrayEmail(String[] email) {


        String[]  mail = new String[10];
        String[]  preffix = new String[10];

        preffix[0] = ".ru"; preffix[1] = ".com"; preffix[2] = ".uk";
        preffix[3] = ".org"; preffix[4] = ".ue"; preffix[5] = ".kz";
        preffix[6] = ".eu"; preffix[7] = ".iu"; preffix[8] = ".ua";
        preffix[9] = ".er";

        mail[0] = "@mail"; mail[1] = "@cmail"; mail[2] = "@gmail";
        mail[3] = "@fgraf"; mail[4] = "@crovet"; mail[5] = "@tuble.";
        mail[6] = "@mkngj"; mail[7] = "@jgons"; mail[8] = "@grafew";
        mail[9] = "@grerg";

        email[0] = "eg234wmail";        email[1] = "kuyposhta34";
        email[2] = "kyuda23lee";       email[3] = "wefemail43";
        email[4] = "yukotpr234avka";    email[5] = "kyubline12321e";
        email[6] = "fwemai345l";        email[7] = "yukpostupka1999";
        email[8] = "kuygravib123";      email[9] = "uykcmai1234l";

        for (Integer i = 10; i < 1000; i++) {
            email[i] = email[(int) (Math.random() * 10)]
                    + mail[(int) (Math.random() * 10)]
                    + preffix[(int) (Math.random() * 10)];
        }
        for (Integer i = 0; i < 10; i++) {
            email[i] = email[(int) (Math.random() * 1000 + 10)];
        }
    }
}
