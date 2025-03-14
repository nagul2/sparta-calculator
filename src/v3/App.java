package v3;

import java.util.Scanner;
import java.util.regex.Pattern;

import static v3.Operator.getValidOperator;

public class App {

    // 음수, 소수점, 숫자만 들어올 수 있는 정규 표현식
    static final String INPUT_VALID_REGEXP = "^-?(?:[1-9]\\d*|0)(?:\\.\\d+)?$";

    public static void main(String[] args) {
        ArithmeticCalculator<Double> calculator = new ArithmeticCalculator<>();

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
                calculator.inputThanBigValuePrint(Double.valueOf(inputValue));

                continue;
            }

            Operator validOperator = getValidOperator(operator);
            if (validOperator == null) { // 연산자 검증로직을 Enum 클래스의 static 메서드에서 검증 -> 객체 지향적인 설계를 위함
                System.out.println("**** 연산 부호를 잘못 입력 하셨습니다. 다시 시작합니다 ****");
                System.out.println();
                continue;
            }

            // 입력값 검증
            String validFirstValueStr = null;
            validFirstValueStr = validNumber(scanner, INPUT_VALID_REGEXP, validOperator, validFirstValueStr);
            String validSecondValueStr = validNumber(scanner, INPUT_VALID_REGEXP, validOperator, validFirstValueStr);

            // 연산
            double firstValue = Double.parseDouble(validFirstValueStr);
            double secondValue = Double.parseDouble(validSecondValueStr);

            Double result = calculator.calculate(validOperator, firstValue, secondValue);
            calculator.calculateResultPrinter(validOperator, firstValue, secondValue, result);      // 출력

            calculator.historyCountHandler();    // 계산 이력이 10개 이상되면 1개를 삭제하고 계산 이력의 개수를 출력
        }
    }

    // after
    private static String validNumber(Scanner scanner, String regExp, Operator operator, String firstValue) {
        String validNumber;

        while (true) {
            System.out.print("숫자를 입력해 주세요(소수점 입력 가능): ");
            validNumber = scanner.nextLine();
            boolean checkResult = Pattern.matches(regExp, validNumber);  // 정규식과 비교
            if (!checkResult) {
                System.out.println("**** 입력값이 올바르지 않습니다. 숫자(소수점포함)만 입력해 주세요.****");
                System.out.println();
                continue;
            }

            if (firstValue != null && Operator.DIVIDE.equals(operator) && Double.parseDouble(validNumber) == 0.0) {
                System.out.println("**** 나눗셈 연산에서는 0을 입력할 수 없습니다. 다시 입력해 주세요 ****");
                System.out.println();
                continue;
            }
            break;
        }
        return validNumber;
    }

}






