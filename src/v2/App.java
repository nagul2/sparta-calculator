package v2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ClazzCalculator calculator = new ClazzCalculator();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("==== 계산기 시작 (종료: exit 입력, 계산결과 이력 보기: his 입력) ====");
            System.out.print("계산할 부호를 입력해 주세요(+, -, *, /) : ");
            String operator = scanner.nextLine();

            if (operator.equals("exit")) {
                System.out.println("계산기 프로그램을 종료합니다.");
                break;
            } else if (operator.equals("his")) {
                calculator.historyPrinter();
                continue;
            }

            if (!(operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/"))) {
                System.out.println("**** 연산 부호를 잘못 입력 하셨습니다. 다시 시작합니다 ****");
                System.out.println();
                continue;
            }

            System.out.println("== 첫 번째 숫자 ==");
            int firstValue = validNumber(scanner, operator);

            System.out.println("== 두 번째 숫자 ==");
            int secondValue = validNumber(scanner, operator);

            scanner.nextLine();
            int result = calculator.getCalculateResult(operator, firstValue, secondValue);
            calculator.add(result); // 계산 결과를 저장

            resultPrinter(operator, result, firstValue, secondValue);   // 계산 결과를 출력
            calculator.historyCountHandler(calculator);                 // 계산 이력이 10개 초과되면 1개를 삭제하고 계산 이력의 개수를 출력
        }
    }

    // after - 메서드 하나로 변경
    private static int validNumber(Scanner scanner, String operator) {
        int validNumber;

        while (true) {
            try {
                System.out.print("계산할 숫자를 입력해주세요. 양의 정수(0포함)만 가능합니다: ");    // 공통으로 사용할 수 있도록 문구 변경
                validNumber = scanner.nextInt();

                if (validNumber < 0) {
                    System.out.println("음수가 입력되었습니다. 양의 정수(0포함)만 입력 가능합니다.");
                    System.out.println();
                    scanner.nextLine();
                    continue;
                }

                if ("/".equals(operator) && validNumber == 0) {
                    System.out.println("**** 나눗셈 연산에서는 0을 입력할 수 없습니다. 다른 수를 입력해 주세요 ****");
                    System.out.println();
                    continue;
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("**** 타입이 맞지 않습니다. 양의 정수(0포함)정수만 입력해 주세요.****");
                System.out.println();
                scanner.nextLine(); // 버퍼지우기
            }

        }
        return validNumber;
    }


    // before
    private static int validFirstValue(Scanner scanner) {
        int firstValue;
        while (true) {
            try {
                System.out.print("첫번째 숫자를 입력해주세요. 양의 정수(0포함)만 가능합니다: ");
                firstValue = scanner.nextInt();

                if (firstValue < 0) {
                    System.out.println("음수가 입력되었습니다. 양의 정수(0포함)만 입력 가능합니다.");
                    System.out.println();
                    scanner.nextLine();
                    continue;
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("**** 타입이 맞지 않습니다. 양의 정수(0포함)정수만 입력해 주세요.****");
                System.out.println();
                scanner.nextLine(); // 버퍼지우기
            }
        }
        return firstValue;
    }

    // before
    private static int validSecondValue(String operator, Scanner scanner) {
        int secondValue;
        while (true) {
            try {
                if (operator.equals("/")) {
                    System.out.print("두번째 숫자(양의 정수, 0포함)를 입력해주세요. 나눗셈 연산은 0을 입력할 수 없습니다: ");
                    secondValue = scanner.nextInt();

                    if (secondValue < 0) {
                        System.out.println("음수가 입력되었습니다. 양의 정수(0포함)만 입력 가능합니다.");
                        System.out.println();
                        scanner.nextLine();
                        continue;
                    }

                    if (secondValue == 0) {
                        System.out.println("**** 나눗셈 연산에서는 0을 입력할 수 없습니다. 다른 수를 입력해 주세요 ****");
                        System.out.println();
                        continue;
                    }
                    break;
                } else {
                    System.out.print("두번째 숫자를 입력해주세요. 양의 정수(0포함)만 가능합니다: ");
                    secondValue = scanner.nextInt();

                    if (secondValue < 0) {
                        System.out.println("음수가 입력되었습니다. 양의 정수(0포함)만 입력 가능합니다.");
                        System.out.println();
                        scanner.nextLine();
                        continue;
                    }
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("**** 타입이 맞지 않습니다. 양의 정수(0포함)만 입력해 주세요.****");
                System.out.println();
                scanner.nextLine(); // 버퍼지우기
            }
        }
        return secondValue;
    }


    private static void resultPrinter(String operator, int result, int firstValue, int secondValue) {
        System.out.println("***************** 계산 결과 출력 *****************");
        System.out.println("계산 결과: " + firstValue + " " + operator + " " + secondValue + " = " + result);
        System.out.println();
    }

}
