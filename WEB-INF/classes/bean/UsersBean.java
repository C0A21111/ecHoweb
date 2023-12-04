package bean;

import java.io.Serializable;

public class UsersBean implements Serializable {
    private int docked_num;
    private String name;
    private int status;

    public void setDockedNumber(int docked_num) {
        this.docked_num = docked_num;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDockedNumber() {
        return docked_num;
    }

    public String getUserName() {
        return name;
    }

    public int getStatus() {
        return status;
    }
}
