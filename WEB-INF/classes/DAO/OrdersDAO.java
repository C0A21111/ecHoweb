package DAO;

import java.sql.*;
import bean.*;

public class OrdersDAO {
    private final String URL = "";
    private final String USER = "";
    private final String PASS = "";
    private Connection con = null;

    public void connect() {
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OrdersDTO select() {
        Statement stmt = null;
        ResultSet rs = null;
        OrdersDTO odto = new OrdersDTO();
        String sql = "SELECT * FROM orders";
        try {
            connect();
            //
            stmt = con.createStatement();
            //
            rs = stmt.executeQuery(sql);
            //
            while (rs.next()) {
                OrdersBean ob = new OrdersBean();
                ob.setOrderId(rs.getInt("order_id"));
                ob.setDockedNumber(rs.getInt("docked_number"));
                ob.setItemId(rs.getInt("item_id"));
                ob.setItemCount(rs.getInt("item_count"));
                odto.add(ob);
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
        return odto;
    }

    public int insert(int docked_number, int item_id, int item_count) {
        String sql = "INSERT INTO orders (docked_number,item_id,item_count) VALUES ("
                + docked_number + ", " + item_id + "," + item_count + ")";
        return executeSql(sql);
    }

    public int delete(int order_id) {
        String sql = "DELETE FROM orders WHERE order_id = " + order_id;
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
