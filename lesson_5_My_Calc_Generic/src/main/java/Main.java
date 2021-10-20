// Калькулятор с Generic / Шаблоны

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int ch;
        Double a, b;
        Cal<Double> c = new Cal<>();

        System.out.println("Калькулятор");
        System.out.print("[Number #1]-> "); a = in.nextDouble();
        System.out.print("[Number #2]-> "); b = in.nextDouble();
        System.out.println("[Symbol]-> ");
        System.out.print("[+: 1]\n[-: 2]\n[/: 3]\n[*: 4]\n->");
        ch = in.nextInt();

        System.out.print(": ");
        switch (ch) {
            case 1 -> System.out.print(c.plus(a, b));
            case 2 -> System.out.print(c.minus(a, b));
            case 3 -> System.out.print(c.del(a, b));
            case 4 -> System.out.print(c.mult(a, b));
            default -> System.out.println("ERROR INPUT DATA");
        }
    }
}
