package bean;

import java.io.Serializable;

public class AutOfStockBean implements Serializable {
    private int id;
    private String name;
    private int count;
    private int stock;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public int getStock() {
        return stock;
    }
}
