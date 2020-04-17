public class Main {
    public static void main(String[] args) {
        try {
            int fac = Factorial.getFactorial(18);
            System.out.println(fac);
        } catch (FactorialException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getNumber());
        }
    }
}
