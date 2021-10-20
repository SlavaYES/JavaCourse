public class Factorial {
    public static int getFactorial(int n) throws FactorialException {
        int fac = 1;
        if (n < 1) throw new FactorialException("The number is less than 1", n);
        if (n > 17) throw new FactorialException("The number is more than 17", n);
        for (int i = 1; i < n; i++) {
            fac *= i;
        }
        return fac;
    }
}
