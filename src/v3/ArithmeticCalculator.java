package v3;

import java.util.ArrayList;
import java.util.List;

public class ArithmeticCalculator <T extends Number> {

    // 이력을 저장하는 DB의 역할
    private List<T> calculratorRepository = new ArrayList<>();

    public void add(T result) {
        calculratorRepository.add(result);
    }

    public T remove() {
        return calculratorRepository.remove(0);
    }

    public int getSize() {
        return calculratorRepository.size();
    }

    public void historyPrinter() {
        if (calculratorRepository.isEmpty()) {
            System.out.println("계산 결과 이력이 없습니다.");
        }

        System.out.println("----- 전체 계산 이력 조회 -----");
        System.out.println("계산 결과는 최대 10개 까지만 조회 됩니다.");
        for (int i = 0; i < getSize() ; i++) {
            System.out.println(i + 1 + "번째 계산 결과: " + calculratorRepository.get(i));
        }
        System.out.println();
    }

    public void intCalculate(String operator, Integer firstValue, Integer secondValue) {
        Integer result = 0;

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
        System.out.println("***************** 계산 결과 출력 *****************");
        System.out.println("계산 결과: " + firstValue + " " + operator + " " + secondValue + " = " + result);
        System.out.println();
    }

    public void doubleCalculate(String operator, Double firstValue, Double secondValue) {
        Double result = 0.0;

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
        System.out.println("***************** 계산 결과 출력 *****************");
        System.out.println("계산 결과: " + firstValue + " " + operator + " " + secondValue + " = " + result);
        System.out.println();
    }

    public void historyCountHandler() {
        if (getSize() >= 10) {
            T removeValue = remove();
            System.out.println("계산 결과를 더이상 보관할 수 없어 가장 오래된 계산 결과가 삭제 되었습니다.");
            System.out.println("삭제된 계산 결과: " + removeValue);
            System.out.println();
        }

        System.out.println("계산 이력 " + getSize() + "건" );
    }

    public void inputThanBigValuePrint(T inputValue) {

        System.out.println(inputValue + "보다 큰 계산 이력 출력");
        double doubleInputValue = inputValue.doubleValue();

        List<T> resultList = calculratorRepository.stream()
                .filter(history -> history.doubleValue() > doubleInputValue)
                .sorted()
                .toList();

        System.out.println(resultList);

    }
}
