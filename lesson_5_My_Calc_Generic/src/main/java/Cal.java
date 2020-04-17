public class Cal<N extends Number> {

    public Cal() {

    }

    public Number mult(N a, N b) {
        return (a.doubleValue() * b.doubleValue());
    }

    public Number plus(N a, N b) {
        return (a.doubleValue() + b.doubleValue());
    }

    public Number minus(N a, N b) {
        return (a.doubleValue() - b.doubleValue());
    }

    public Number del(N a, N b) {
        if (b.intValue() == 0) {
            return 0;
        } else {
            return (a.doubleValue() / b.doubleValue());
        }
    }
}
