package v3;

public enum Operator {
    PLUS("+", "+"),
    MINUS("-", "-"),
    MULTIPLY("*", "✕"),
    DIVIDE("/", "÷"),
    ;

    String input;
    String symbol;

    Operator(String input, String symbol) {
        this.input = input;
        this.symbol = symbol;
    }

    public String getInput() {
        return input;
    }

    public String getSymbol() {
        return symbol;
    }


    public static Operator getValidOperator(String input) {
        Operator[] operators = Operator.values();
        for (Operator operator : operators) {
            if (operator.getInput().equals(input)) {
                return operator;
            }
        }
        return null;
    }
}
