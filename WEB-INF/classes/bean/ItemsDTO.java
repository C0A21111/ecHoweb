package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemsDTO implements Serializable {
    private ArrayList<ItemsBean> list;

    public ItemsDTO() {
        list = new ArrayList<ItemsBean>();
    }

    public void add(ItemsBean cb) {
        list.add(cb);
    }

    public ItemsBean get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }
}
