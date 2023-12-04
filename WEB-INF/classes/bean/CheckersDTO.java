package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class CheckersDTO implements Serializable {
    private ArrayList<CheckersBean> list;

    public CheckersDTO() {
        list = new ArrayList<CheckersBean>();
    }

    public void add(CheckersBean cb) {
        list.add(cb);
    }

    public CheckersBean get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }
}
