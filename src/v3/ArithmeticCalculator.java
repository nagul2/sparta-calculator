package v3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArithmeticCalculator <T extends Number> {

    // 이력을 저장하는 DB의 역할
    private List<T> calculratorRepository = new ArrayList<>();

    private void add(T result) {
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
            System.out.println();
            return;
        }

        System.out.println("----- 전체 계산 이력 조회 -----");
        System.out.println("계산 결과는 최대 10개 까지만 조회 됩니다.");
        for (int i = 0; i < getSize() ; i++) {
            System.out.println(i + 1 + "번째 계산 결과: " + calculratorRepository.get(i));
        }
        System.out.println();
    }

    public Double calculate(Operator operator, T firstValue, T secondValue) {
        Double result = 0.0;
        Double num1 = 0.0;
        Double num2 = 0.0;

        if (firstValue instanceof Double || secondValue instanceof Double) {
            num1 = firstValue.doubleValue();
            num2 = secondValue.doubleValue();
        }

        switch (operator) {
            case PLUS:
                result = num1 + num2;
                break;
            case MINUS:
                result = num1 - num2;
                break;
            case MULTIPLY:
                result = num1 * num2;
                break;
            case DIVIDE:
                result = num1 / num2;
                break;
        }

        if (result % 1 == 0) {
            add((T) Integer.valueOf(result.intValue()));
        } else {
            add((T) result);
        }

        return result;
    }

    public void calculateResultPrinter(Operator operator, Double firstValue, Double secondValue, Double result) {

        String printFirstValue = printFormat(firstValue);
        String printSecondValue = printFormat(secondValue);
        String printResultValue = printFormat(result);

        System.out.println("***************** 계산 결과 출력 *****************");
        System.out.println("계산 결과: " + printFirstValue + " " + operator.getSymbol() + " " + printSecondValue+ " = " + printResultValue);
        System.out.println();
    }

    public void historyCountHandler() {
        if (getSize() > 10) {
            T removeValue = remove();
            System.out.println("계산 결과를 더이상 보관할 수 없어 가장 오래된 계산 결과가 삭제 되었습니다.");
            System.out.println("삭제된 계산 결과: " + removeValue);
            System.out.println();
            return;
        }

        System.out.println("계산 이력 " + getSize() + "건" );
    }

    public void inputThanBigValuePrint(T inputValue) {

        if (calculratorRepository.isEmpty()) {
            System.out.println("계산 결과 이력이 없습니다.");
            System.out.println();
            return;
        }

        System.out.println(inputValue + "보다 큰 계산 이력 출력");
        double doubleInputValue = inputValue.doubleValue();

        List<T> resultList = calculratorRepository.stream()
                .filter(history -> history.doubleValue() > doubleInputValue)
                .sorted(Comparator.comparingDouble(history -> history.doubleValue()))   // 동일 타입으로 정렬하기 위해 비교자를 double 타입으로 지정
                .toList();

        System.out.println(resultList);

    }

    private String printFormat(Double value) {
        if (value % 1 == 0) {
            return String.valueOf(value.intValue());
        }
        return String.valueOf(value);
    }
    
}
