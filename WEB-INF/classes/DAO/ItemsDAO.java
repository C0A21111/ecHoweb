package DAO;

import java.sql.*;
import bean.*;

public class ItemsDAO {
    private final String URL = "jdbc:mysql://echodb/echodb";
    private final String USER = "root";
    private final String PASS = "teLnr1729";
    private Connection con = null;

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ItemsDTO select() {
        Statement stmt = null;
        ResultSet rs = null;
        ItemsDTO idto = new ItemsDTO();
        String sql = "SELECT * FROM items";
        try {
            connect();
            //
            stmt = con.createStatement();
            //
            rs = stmt.executeQuery(sql);
            //
            while (rs.next()) {
                ItemsBean ib = new ItemsBean();
                ib.setId(rs.getInt("item_id"));
                ib.setName(rs.getString("item_name"));
                ib.setPrice(rs.getInt("item_price"));
                ib.setStock(rs.getInt("item_stock"));
                idto.add(ib);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        disconnect();
        return idto;
    }

    public int insert(String name, String price) {
        String sql = "INSERT INTO items (item_name,item_price) VALUES ('"
                + name + "', " + price + ")";
        return executeSql(sql);
    }

    public int updatePrice(String id, String price) {
        String sql = "UPDATE items SET item_price = " + price + " WHERE item_id = " + id;
        return executeSql(sql);
    }

    public int updateStock(String id, String stock) {
        String sql = "UPDATE items SET item_stock = " + stock + " WHERE item_id = " + id;
        return executeSql(sql);
    }

    public int delete(String id) {
        String sql = "DELETE FROM items WHERE item_id = " + id;
        return executeSql(sql);
    }

    public int executeSql(String sql) {
        Statement stmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            connect();
            stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        disconnect();
        return result;
    }

    public void disconnect() {
        try {
            if (con != null)
                con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
