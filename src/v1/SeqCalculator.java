package v1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SeqCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("==== 계산기 시작 ====");
            System.out.print("계산할 부호를 입력해 주세요(+, -, *, /, 중 입력, 종료는 exit 입력): ");
            String operator = scanner.nextLine();

            if (operator.equals("exit")) {
                System.out.println("계산기 프로그램을 종료합니다.");
                break;
            }

            if (!(operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/"))) {
                System.out.println("**** 연산 부호를 잘못 입력 하셨습니다. 다시 시작합니다 ****");
                System.out.println();
                continue;
            }

            int firstValue;
            int secondValue;
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
                            scanner.nextLine();
                            continue;
                        }

                        break;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("**** 타입이 맞지 않습니다. 정수만 입력해 주세요.****");
                    System.out.println();
                    scanner.nextLine(); // 버퍼지우기
                }
            }

            scanner.nextLine(); // 버퍼지우기
            switch (operator) {
                case "+":
                    System.out.println(firstValue + " + " + secondValue + " = " + (firstValue + secondValue));
                    System.out.println();
                    break;
                case "-":
                    System.out.println(firstValue + " - " + secondValue + " = " + (firstValue - secondValue));
                    System.out.println();
                    break;
                case "*":
                    System.out.println(firstValue + " * " + secondValue + " = " + (firstValue * secondValue));
                    System.out.println();
                    break;
                case "/":
                    System.out.println(firstValue + " / " + secondValue + " = " + firstValue / secondValue);
                    System.out.println();
            }
        }
    }
}
