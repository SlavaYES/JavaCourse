public class FactorialException extends Exception {
    private int number;
    public int getNumber() {
        return number;
    }
    public FactorialException(String msg, int n) {
        super(msg);
        number = n;
    }
}
