package v2;

import java.util.ArrayList;
import java.util.List;

public class ClazzCalculator {

    private List<Integer> resultHistoryList = new ArrayList<>();


    public void add(int result) {
        resultHistoryList.add(result);
    }

    public int remove() {
        return resultHistoryList.remove(0);
    }

    public int getSize() {
        return resultHistoryList.size();
    }
}
