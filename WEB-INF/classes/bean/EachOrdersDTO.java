package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class EachOrdersDTO implements Serializable {
    private ArrayList<EachOrdersBean> list;

    public EachOrdersDTO() {
        list = new ArrayList<EachOrdersBean>();
    }

    public void add(EachOrdersBean eb) {
        list.add(eb);
    }

    public EachOrdersBean get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }
}
