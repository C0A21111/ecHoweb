package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class OrdersDTO implements Serializable {
    private ArrayList<OrdersBean> list;

    public OrdersDTO() {
        list = new ArrayList<OrdersBean>();
    }

    public void add(OrdersBean ob) {
        list.add(ob);
    }

    public OrdersBean get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }
}
