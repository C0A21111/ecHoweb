package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class CancelledDTO implements Serializable {
    private ArrayList<Integer> list;

    public CancelledDTO() {
        list = new ArrayList<Integer>();
    }

    public void add(int cb) {
        list.add(cb);
    }

    public int get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }

    public boolean contains(int i) {
        return list.contains(i);
    }
}
