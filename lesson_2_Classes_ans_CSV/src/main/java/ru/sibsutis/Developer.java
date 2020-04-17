package ru.sibsutis;

import java.io.*;
import java.util.Scanner;

public class Developer extends User implements CSV {

    protected String language;

    public Developer(String name, String email, String language) {
        super(name, email);
        this.language = language;
    }

    @Override
    public String toCSV() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("res/developer.csv", true));
            writer.write(getName() + " " + getEmail() + " " + this.language + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public void Remove() {
        fromCSV(this.getName(), this.getEmail(), this.language);
    }

    @Override
    public void fromCSV(String name, String email, String language) {
        try {
            FileWriter writer = new FileWriter("res/developer1.csv");
            FileReader reader = new FileReader("res/developer.csv");

            Scanner in = new Scanner(reader);
            while (in.hasNext()) {
                String str = in.nextLine();
                String a[] = str.split(" ");
                if (!a[0].equals(name) && !a[1].equals(email) && !a[2].equals(language)) {
                    writer.write(str + "\n");
                }
            }

            reader.close(); writer.close();

            File developer = new File("res/developer.csv");
            developer.delete();
            File developer1 = new File("res/developer1.csv");
            developer1.renameTo(developer);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void fillCSV() {

        String[] arName = new String[1000];
        String[] arEmail = new String[1000];
        String[] arLanguage = new String[1000];

        arrayName(arName);
        arrayEmail(arEmail);
        arrayLanguage(arLanguage);

        try {

            FileWriter writer = new FileWriter("res/developer.csv");

            for (int i = 0; i < 1000; i++) {
                writer.write(arName[i] + " " + arEmail[i] + " " + arLanguage[i] + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void arrayLanguage(String[] language) {

        language[0] = "C++"; language[1] = "C"; language[2] = "C#";
        language[3] = "Java"; language[4] = "JavaScript"; language[5] = "Pascal";
        language[6] = "Kotlin"; language[7] = "Rudy"; language[8] = "Lisp";
        language[9] = "Assambly";

        for (Integer i = 10; i < 1000; i++) {
            language[i] = language[(int) (Math.random() * 10)];
        }

    }

    public void arrayName(String[] name) {
        name[0] = "Владимир"; name[1] = "Владислав"; name[2] = "Надежда";
        name[3] = "Александра"; name[4] = "Александр"; name[5] = "Света";
        name[6] = "Вячеслав"; name[7] = "Егор"; name[8] = "Олег";
        name[9] = "Антон";

        for (Integer i = 10; i < 1000; i++) {
            name[i] = name[(int) (Math.random() * 10)];
        }
    }

    public void arrayEmail(String[] email) {


        String[]  mail = new String[10];
        String[]  preffix = new String[10];
        String[] name = new String[10];

        preffix[0] = ".ru"; preffix[1] = ".com"; preffix[2] = ".uk";
        preffix[3] = ".org"; preffix[4] = ".ue"; preffix[5] = ".kz";
        preffix[6] = ".eu"; preffix[7] = ".iu"; preffix[8] = ".ua";
        preffix[9] = ".er";

        mail[0] = "@mail"; mail[1] = "@cmail"; mail[2] = "@gmail";
        mail[3] = "@fgraf"; mail[4] = "@crovet"; mail[5] = "@tuble.";
        mail[6] = "@mkngj"; mail[7] = "@jgons"; mail[8] = "@grafew";
        mail[9] = "@grerg";

        name[0] = "efwmail";        name[1] = "kuyposhta";
        name[2] = "kyudalee";       name[3] = "wefname";
        name[4] = "yukotpravka";    name[5] = "kyublinee";
        name[6] = "fwname";        name[7] = "yukpostupka";
        name[8] = "kuygravib";      name[9] = "uykcmail";

        for (Integer i = 0; i < 1000; i++) {
            email[i] = name[(int) (Math.random() * 10)]
                    + mail[(int) (Math.random() * 10)]
                    + preffix[(int) (Math.random() * 10)];
        }
    }
}
