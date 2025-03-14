package v2;

import java.util.ArrayList;
import java.util.List;

public class ClazzCalculator {

    private List<Integer> resultHistoryList = new ArrayList<>();

    public void add(int result) {
        resultHistoryList.add(result);
    }

    private int remove() {
        return resultHistoryList.remove(0);
    }

    private int getSize() {
        return resultHistoryList.size();
    }

    public void historyPrinter() {
        if (getSize() == 0) {
            System.out.println("계산 결과 이력이 없습니다.");
            System.out.println();
        }

        System.out.println("----- 전체 계산 이력 조회 -----");
        System.out.println("계산 결과는 최대 10개 까지만 조회 됩니다.");
        for (int i = 0; i < getSize() ; i++) {
            System.out.println(i + 1 + "번째 계산 결과: " + resultHistoryList.get(i));
        }
        System.out.println();
    }

    public int getCalculateResult(String operator, int firstValue, int secondValue) {
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

    public void historyCountHandler(ClazzCalculator calculator) {
        if (calculator.getSize() > 10) {
            int removeValue = calculator.remove();
            System.out.println("계산 결과를 더이상 보관할 수 없어 가장 오래된 계산 결과가 삭제 되었습니다.");
            System.out.println("삭제된 계산 결과: " + removeValue);
            System.out.println();
        }

        System.out.println("계산 이력 " + calculator.getSize() + "건" );
    }

}
