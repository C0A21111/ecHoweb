package DAO;

import java.sql.*;
import bean.*;

public class EachOrdersDAO {
    private final String URL = "";
    private final String USER = "";
    private final String PASS = "";
    private Connection con = null;

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public EachOrdersDTO select(int docked_number) {
        Statement stmt = null;
        ResultSet rs = null;
        EachOrdersDTO edto = new EachOrdersDTO();
        String sql = "select i.item_id as id, i.item_name as name, i.item_price as price, o.item_count as count, (i.item_price * o.item_count) as subtotal "
                + "from items i join orders o on i.item_id=o.item_id where o.docked_number=" + docked_number;
        try {
            connect();
            //
            stmt = con.createStatement();
            //
            rs = stmt.executeQuery(sql);
            //
            while (rs.next()) {
                EachOrdersBean eb = new EachOrdersBean();
                eb.setItemId(rs.getInt("id"));
                eb.setItemName(rs.getString("name"));
                eb.setItemPrice(rs.getInt("price"));
                eb.setItemCount(rs.getInt("count"));
                eb.setSubtotal(rs.getInt("subtotal"));
                edto.add(eb);
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
        return edto;
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
