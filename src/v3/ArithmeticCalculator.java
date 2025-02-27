package v3;

import java.util.ArrayList;
import java.util.List;

public class ArithmeticCalculator <T extends Number> {

    // ... -> DB 저장소
    private List<Integer> resultHistoryList = new ArrayList<>();

    public void add(Number result) {
        resultHistoryList.add(result);
    }

    public int remove() {
        return resultHistoryList.remove(0);
    }

    public int getSize() {
        return resultHistoryList.size();
    }

    public void historyPrinter() {
        if (getSize() == 0) {
            System.out.println("계산 결과 이력이 없습니다.");
        }

        System.out.println("----- 전체 계산 이력 조회 -----");
        System.out.println("계산 결과는 최대 10개 까지만 조회 됩니다.");
        for (int i = 0; i < getSize() ; i++) {
            System.out.println(i + 1 + "번째 계산 결과: " + resultHistoryList.get(i));
        }
        System.out.println();
    }

    public Number getCalculateResult(String operator, Number firstValue, Number secondValue) {
        Number result;
        if (firstValue instanceof Double || secondValue instanceof Double) {

        }
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


    public static void resultPrinter(String operator, int result, int firstValue, int secondValue) {
        System.out.println("***************** 계산 결과 출력 *****************");
        System.out.println("계산 결과: " + firstValue + " " + operator + " " + secondValue + " = " + result);
        System.out.println();
    }

    public static void historyCountHandler(ArithmeticCalculator calculator) {
        if (calculator.getSize() >= 10) {
            int removeValue = calculator.remove();
            System.out.println("계산 결과를 더이상 보관할 수 없어 가장 오래된 계산 결과가 삭제 되었습니다.");
            System.out.println("삭제된 계산 결과: " + removeValue);
            System.out.println();
        }

        System.out.println("계산 이력 " + calculator.getSize() + "건" );
    }
}
