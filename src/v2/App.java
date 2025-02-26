package v2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ClazzCalculator calculator = new ClazzCalculator();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("==== 계산기 시작 ====");
            System.out.print("계산할 부호를 입력해 주세요(+, -, *, /, 중 입력, 종료는 exit 입력): ");
            String operator = scanner.nextLine();

            if (operator.equals("exit")) {
                System.out.println("계산기 프로그램을 종료합니다.");
                break;
            }

            if (!(operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/"))) {
                System.out.println("**** 연산 부호를 잘못 입력 하셨습니다. 다시 시작합니다 ****\n");
                continue;
            }

            int firstValue = getFirstValue(scanner);
            int secondValue = getSecondValue(operator, scanner);

            scanner.nextLine();
            int result = getCalculateResult(operator, firstValue, secondValue);
            calculator.add(result); // 계산 결과 저장

            resultPrinter(operator, result, firstValue, secondValue);
        }
    }

    private static int getFirstValue(Scanner scanner) {
        int firstValue;
        while (true) {
            try {
                System.out.print("첫번째 숫자를 입력해주세요. 정수만 가능합니다: ");
                firstValue = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("**** 타입이 맞지 않습니다. 정수만 입력해 주세요.**** \n");
                scanner.nextLine(); // 버퍼지우기
            }
        }
        return firstValue;
    }

    private static int getSecondValue(String operator, Scanner scanner) {
        int secondValue;
        while (true) {
            try {
                if (operator.equals("/")) {
                    System.out.print("두번째 숫자를 입력해주세요. 나눗셈 연산은 0을 입력할 수 없습니다: ");
                    secondValue = scanner.nextInt();

                    if (secondValue == 0) {
                        System.out.println("**** 나눗셈 연산에서는 0을 입력할 수 없습니다. 다른 수를 입력해 주세요 ****\n");
                        continue;
                    }
                    break;
                } else {
                    System.out.print("두번째 숫자를 입력해주세요. 양의 정수만 가능합니다: ");
                    secondValue = scanner.nextInt();
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("**** 타입이 맞지 않습니다. 양의 정수만 입력해 주세요.**** \n");
                scanner.nextLine();
            }
        }
        return secondValue;
    }

    private static int getCalculateResult(String operator, int firstValue, int secondValue) {
        int result = 0;
        switch (operator) {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
            case "*":
                result = firstValue * secondValue;
                break;
            case "/":
                result = firstValue / secondValue;
                break;
        }
        return result;
    }

    private static void resultPrinter(String operator, int result, int firstValue, int secondValue) {
        System.out.println("계산 결과: " + firstValue + " " + operator + " " + secondValue + " " + result);
    }
}
