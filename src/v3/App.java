package v3;

import java.util.Scanner;
import java.util.regex.Pattern;

import static v3.Operator.getValidOperator;

public class App {

    // 음수, 소수점, 숫자만 들어올 수 있는 정규 표현식
    static final String INPUT_VALID_REGEXP = "^-?(?:[1-9]\\d*|0)(?:\\.\\d+)?$";

    public static void main(String[] args) {
        ArithmeticCalculator<> calculator = new ArithmeticCalculator<>();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("==== 계산기 시작 (종료: exit 입력, 계산결과 이력 보기: his 입력) ====");
            System.out.print("계산할 부호를 입력해 주세요(+, -, *, /) : ");
            String operator = scanner.nextLine();

            if (operator.equals(OtherCommand.EXIT.getCommand())) {  // Enum 적용
                System.out.println("계산기 프로그램을 종료합니다.");
                break;
            } else if (operator.equals(OtherCommand.HISTORY.getCommand())) {    // Enum 적용
                calculator.historyPrinter();
                continue;
            }
            getValidOperator(operator); // 연산자 검증로직을 Enum 클래스의 static 메서드에서 검증 -> 객체 지향적인 설계를 위함

            Number firstValue = validFirstValue(scanner, INPUT_VALID_REGEXP);                // 첫 번째 값 검증
            Number secondValue = validSecondValue(operator, scanner, INPUT_VALID_REGEXP);    // 두 번째 값 검증

            scanner.nextLine();
            Number result = calculator.getCalculateResult(operator, firstValue, secondValue);
            calculator.add(result); // 계산 결과를 저장

            calculator.resultPrinter(operator, result, firstValue, secondValue);   // 계산 결과를 출력
            calculator.historyCountHandler(calculator);    // 계산 이력이 10개 이상되면 1개를 삭제하고 계산 이력의 개수를 출력
        }
    }

    private static <T extends Number> T validFirstValue(Scanner scanner, String regExp) {

        T resultValue;

        while (true) {
            System.out.print("계산을 위한 첫번째 숫자를 입력해 주세요(소수점 입력 가능): ");
            String firstValue = scanner.nextLine();
            boolean checkResult = Pattern.matches(regExp, firstValue);  // 정규식과 비교
            resultValue = getValidNumber(checkResult, firstValue, resultValue);
        }
    }

    private static Number validSecondValue(String operator, Scanner scanner, String regExp) {
        Number resultValue = 0;
        while (true) {

            if (operator.equals(Operator.DIVIDE.getInput())) {
                System.out.print("두번째 숫자를 입력해주세요. 나눗셈 연산은 0으로 나눌 수 없습니다.: ");
                try {
                    String secondValue = scanner.nextLine();
                    boolean checkResult = Pattern.matches(regExp, secondValue);  // 정규식과 비교
                    resultValue = getValidNumber(checkResult, secondValue, resultValue);
                    break;

                } catch (ArithmeticException e) {
                    System.out.println("**** 나눗셈 연산에서는 0을 입력할 수 없습니다. 다시 입력해 주세요 ****");
                    System.out.println();
                }
            } else {
                System.out.print("두번째 숫자를 입력해주세요. 숫자만 가능합니다: ");
                String secondValue = scanner.nextLine();
                boolean checkResult = Pattern.matches(regExp, secondValue);  // 정규식과 비교

                resultValue = getValidNumber(checkResult, secondValue, resultValue);
            }
        }
        return resultValue;
    }

    private static Number getValidNumber(boolean checkResult, String secondValue, Number resultValue) {
        if (!checkResult) {
            System.out.println("**** 입력값이 올바르지 않습니다. 숫자(소수점포함) 입력해 주세요.****");
            System.out.println();
            return resultValue;
        }

        if (secondValue.contains(".")) {
            resultValue = Double.valueOf(secondValue);
        } else {
            resultValue = Integer.parseInt(secondValue);
        }
        return resultValue;
    }
}






