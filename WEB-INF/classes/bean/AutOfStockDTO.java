package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class AutOfStockDTO implements Serializable {
    private ArrayList<AutOfStockBean> list;

    public AutOfStockDTO() {
        list = new ArrayList<AutOfStockBean>();
    }

    public void add(AutOfStockBean ab) {
        list.add(ab);
    }

    public AutOfStockBean get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }
}
