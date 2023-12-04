package bean;

import java.io.Serializable;

public class OrdersBean implements Serializable {
    private int order_id;
    private int docked_number;
    private int item_id;
    private int item_count;

    public int getOrderId() {
        return order_id;
    }

    public int getDockedNumber() {
        return docked_number;
    }

    public int getItemId() {
        return item_id;
    }

    public int getItemCount() {
        return item_count;
    }

    public void setOrderId(int order_id) {
        this.order_id = order_id;
    }

    public void setDockedNumber(int docked_number) {
        this.docked_number = docked_number;
    }

    public void setItemId(int item_id) {
        this.item_id = item_id;
    }

    public void setItemCount(int item_count) {
        this.item_count = item_count;
    }

}
