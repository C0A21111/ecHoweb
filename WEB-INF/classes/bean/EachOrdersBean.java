package bean;

import java.io.Serializable;

public class EachOrdersBean implements Serializable {
    private int id;
    private String name;
    private int price;
    private int count;
    private int subtotal;

    public void setItemId(int id) {
        this.id = id;
    }

    public void setItemName(String name) {
        this.name = name;
    }

    public void setItemPrice(int price) {
        this.price = price;
    }

    public void setItemCount(int count) {
        this.count = count;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getItemId() {
        return id;
    }

    public String getItemName() {
        return name;
    }

    public int getItemPrice() {
        return price;
    }

    public int getItemCount() {
        return count;
    }

    public int getSubtotal() {
        return subtotal;
    }

}
