package DAO;

import java.sql.*;
import bean.*;

public class CheckersDAO {
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

    public CheckersDTO login(String name, String password) {
        Statement stmt = null;
        ResultSet rs = null;
        CheckersDTO cdto = new CheckersDTO();
        String sql = "SELECT * FROM checkers where checker_name = '" + name + "' and checker_password = '" + password + "'";
        try {
            connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            CheckersBean cb = new CheckersBean();
            if (rs.next()) {
                cb.setId(rs.getInt("checker_id"));
                cb.setName(rs.getString("checker_name"));
                cb.setPassword(rs.getString("checker_password"));
            } else {
                cb.setId(9999);
                cb.setName("notFound");
                cb.setPassword("notFound");
            }
            cdto.add(cb);
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
        return cdto;
    }

    public CheckersDTO select() {
        Statement stmt = null;
        ResultSet rs = null;
        CheckersDTO cdto = new CheckersDTO();
        String sql = "SELECT * FROM checkers";
        try {
            connect();
            //
            stmt = con.createStatement();
            //
            rs = stmt.executeQuery(sql);
            //
            while (rs.next()) {
                CheckersBean cb = new CheckersBean();
                cb.setId(rs.getInt("checker_id"));
                cb.setName(rs.getString("checker_name"));
                cb.setPassword(rs.getString("checker_password"));
                cdto.add(cb);
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
        return cdto;
    }

    public int insert(String name, String password) {
        String sql = "INSERT INTO checkers (checker_name,checker_password) VALUES ('"
                + name + "', '" + password + "')";
        return executeSql(sql);
    }

    public int update(int id, String name, String password) {
        String sql = "UPDATE checkers SET checker_name = '" + name + "', checker_password = '" + password
                + "' WHERE checker_id = " + id;
        return executeSql(sql);
    }

    public int delete(int id) {
        String sql = "DELETE FROM checkers WHERE checker_id = " + id;
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
            //
            if (con != null)
                con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
