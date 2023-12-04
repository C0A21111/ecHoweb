package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class UsersDTO implements Serializable {
    private ArrayList<UsersBean> list;

    public UsersDTO() {
        list = new ArrayList<UsersBean>();
    }

    public void add(UsersBean ub) {
        list.add(ub);
    }

    public UsersBean get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }
}
