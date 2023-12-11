package DAO;

import java.sql.*;
import bean.*;

public class UsersDAO {
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

    public UsersDTO select(String select) {
        Statement stmt = null;
        ResultSet rs = null;
        UsersDTO udto = new UsersDTO();
        String sql = "";
        if (select == "All") {
            sql = "SELECT * FROM users";
        } else if (select == "FrontAndBehind") {
            sql = "select * from users where docked_number between (select min(docked_number) from users where status = 2) - 3 and (select max(docked_number) from users where status = 2) + 3";
        }

        try {
            connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UsersBean ub = new UsersBean();
                ub.setDockedNumber(rs.getInt("docked_number"));
                ub.setUserName(rs.getString("user_name"));
                ub.setStatus(rs.getInt("status"));
                udto.add(ub);
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
        return udto;
    }

    public UsersDTO frontMostSelect(int status) {
        Statement stmt = null;
        ResultSet rs = null;
        UsersDTO udto = new UsersDTO();
        String sql = "select min(docked_number) as docked_number from users where status = " + status;
        try {
            connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UsersBean ub = new UsersBean();
                ub.setDockedNumber(rs.getInt("docked_number"));
                udto.add(ub);
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
        return udto;
    }

    public UsersDTO pageSelect(String select, int page) {
        Statement stmt = null;
        ResultSet rs = null;
        UsersDTO udto = new UsersDTO();
        page = (page - 1) * 20 + 1;
        String sql = "select * from users where docked_number between " + page + " and " + (page + 19);
        try {
            connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UsersBean ub = new UsersBean();
                ub.setDockedNumber(rs.getInt("docked_number"));
                ub.setUserName(rs.getString("user_name"));
                ub.setStatus(rs.getInt("status"));
                udto.add(ub);
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
        return udto;
    }

    public int insert(String name) {
        String sql = "INSERT INTO users (user_name) VALUES ('" + name + "')";
        return executeSql(sql);
    }

    public int updateStatus(int docked_number, int status) {
        String sql = "UPDATE users SET status = " + status + " WHERE docked_number = " + docked_number;
        return executeSql(sql);
    }

    public int delete(int docked_number) {
        String sql = "DELETE FROM users WHERE docked_number = " + docked_number;
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
