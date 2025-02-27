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


    public static void getValidOperator(String input) {
        Operator[] operators = Operator.values();
        for (Operator operator : operators) {
            if (!operator.getInput().equals(input)) {
                System.out.println("**** 연산 부호를 잘못 입력 하셨습니다. 다시 시작합니다 ****\n");
            }
        }
    }
}
