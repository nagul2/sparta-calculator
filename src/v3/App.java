package v3;

import java.util.Scanner;
import java.util.regex.Pattern;

import static v3.Operator.getValidOperator;

public class App {

    // 음수, 소수점, 숫자만 들어올 수 있는 정규 표현식
    static final String INPUT_VALID_REGEXP = "^-?(?:[1-9]\\d*|0)(?:\\.\\d+)?$";

    public static void main(String[] args) {
        ArithmeticCalculator<Number> calculator = new ArithmeticCalculator<>();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("==== 계산기 시작 ====");
            System.out.println("- 종료: exit");
            System.out.println("- 계산결과 이력 보기: his");
            System.out.println("- 입력값보다 큰 계산 결과 이력 보기: bighis");
            System.out.println("- 계산 부호 입력 (+ , -, *, /)");
            System.out.print("메뉴를 선택해 주세요: ");

            String operator = scanner.nextLine();

            if (operator.equals(OtherCommand.EXIT.getCommand())) {  // Enum 적용
                System.out.println("계산기 프로그램을 종료합니다.");
                break;

            } else if (operator.equals(OtherCommand.HISTORY.getCommand())) {    // Enum 적용
                calculator.historyPrinter();
                continue;

            } else if (operator.equals(OtherCommand.BIG_HISTORY.getCommand())) {
                String inputValue;
                while (true) {
                    System.out.print("조회 하실 값을 입력해 주세요: ");
                    inputValue = scanner.nextLine();
                    boolean checkResult = Pattern.matches(INPUT_VALID_REGEXP, inputValue);  // 정규식과 비교
                    if (!checkResult) {
                        System.out.println("**** 입력값이 올바르지 않습니다. 숫자(소수점포함)만 입력해 주세요.****");
                        System.out.println();
                        continue;
                    }
                    break;
                }
                if (inputValue.contains(".")) {
                    calculator.inputThanBigValuePrint(Double.valueOf(inputValue));
                } else {
                    calculator.inputThanBigValuePrint(Integer.valueOf(inputValue));
                }
                continue;
            }

            Operator validOperator = getValidOperator(operator);
            if (validOperator == null) { // 연산자 검증로직을 Enum 클래스의 static 메서드에서 검증 -> 객체 지향적인 설계를 위함
                System.out.println("**** 연산 부호를 잘못 입력 하셨습니다. 다시 시작합니다 ****");
                System.out.println();
                continue;
            }

            // 입력값 검증
            String validFirstValueStr = validFirstValue(scanner, INPUT_VALID_REGEXP);
            String validSecondValueStr = validSecondValue(validOperator.getSymbol(), scanner, INPUT_VALID_REGEXP);

            Number firstValue = inputTypeConverter(validFirstValueStr);
            Number secondValue = inputTypeConverter(validSecondValueStr);

            if (firstValue instanceof Double || secondValue instanceof Double) {
                calculator.doubleCalculate(validOperator, firstValue.doubleValue(), secondValue.doubleValue());
            } else {
                calculator.intCalculate(validOperator, firstValue.intValue(), secondValue.intValue());
            }
            calculator.historyCountHandler();    // 계산 이력이 10개 이상되면 1개를 삭제하고 계산 이력의 개수를 출력
        }
    }

    private static String validFirstValue(Scanner scanner, String regExp) {
        String firstValue;
        while (true) {
            System.out.print("숫자를 입력해 주세요(소수점 입력 가능): ");
            firstValue = scanner.nextLine();
            boolean checkResult = Pattern.matches(regExp, firstValue);  // 정규식과 비교
            if (!checkResult) {
                System.out.println("**** 입력값이 올바르지 않습니다. 숫자(소수점포함)만 입력해 주세요.****");
                System.out.println();
                continue;
            }
            break;
        }
        return firstValue;
    }

    private static String validSecondValue(String operator, Scanner scanner, String regExp) {
        String secondValue;
        while (true) {

            if (operator.equals(Operator.DIVIDE.getSymbol())) {
                System.out.print("두번째 숫자를 입력해주세요. 나눗셈 연산은 0으로 나눌 수 없습니다.: ");

                secondValue = scanner.nextLine();
                boolean checkResult = Pattern.matches(regExp, secondValue);  // 정규식과 비교
                if (!checkResult) {
                    System.out.println("**** 입력값이 올바르지 않습니다. 숫자(소수점포함)만 입력해 주세요.****");
                    System.out.println();
                    continue;
                }
                if (Double.parseDouble(secondValue) == 0.0) {
                    System.out.println("**** 나눗셈 연산에서는 0을 입력할 수 없습니다. 다시 입력해 주세요 ****");
                    System.out.println();
                    continue;
                }
                break;

            } else {
                System.out.print("두번째 숫자를 입력해주세요. 숫자만 가능합니다: ");
                secondValue = scanner.nextLine();
                boolean checkResult = Pattern.matches(regExp, secondValue);  // 정규식과 비교

                if (!checkResult) {
                    System.out.println("**** 입력값이 올바르지 않습니다. 숫자(소수점포함)만 입력해 주세요.****");
                    System.out.println();
                    continue;
                }
                break;
            }
        }
        return secondValue;
    }

    private static Number inputTypeConverter(String inputValue) {
        if (inputValue.contains(".")) {
            return Double.valueOf(inputValue);
        } else {
            return Integer.valueOf(inputValue);
        }
    }

}






