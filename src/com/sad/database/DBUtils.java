package com.sad.database;

import java.io.File;
import java.sql.*;

public class DBUtils {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_URL = "jdbc:derby:YETI;create=true";
    private String dbError=null;

    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException ex) {
            dbError = "Connection to Database Failed.";
            System.out.println("ERROR: " + ex.getMessage());
        }
        return conn;
    }

    public static boolean dbExists() {
        boolean flag = false;
        File dbFile = new File("YETI/log");
        if (dbFile.exists() && dbFile.isDirectory()) {
            flag = true;
        }
        return flag;
    }

    public void createDB() {
        Connection conn = null;
        conn = getConnection();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
        return;
    }

    public void createSequences() {
        Connection conn = null;
        PreparedStatement pstmt;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("create sequence users_seq as int start with 1 increment by 1");
            pstmt.execute();
            pstmt = conn.prepareStatement("create sequence task_seq as int start with 1 increment by 1");
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("ERROR: " + ex.getMessage());
                }
            }
        }
    }

    public void createTables() {
        Connection conn = null;
        PreparedStatement pstmt;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("CREATE TABLE Users (us_id integer primary key, us_userName varchar(50) not null, us_password varchar(50) not null)");
            pstmt.execute();
            pstmt = conn.prepareStatement("CREATE TABLE Priority (pr_id integer primary key, pr_description varchar(20) not null)");
            pstmt.execute();
            pstmt = conn.prepareStatement("CREATE TABLE Task_type (tt_id integer primary key, tt_description varchar(20) not null)");
            pstmt.execute();
            pstmt = conn.prepareStatement("CREATE TABLE Task (ts_id integer Primary Key, ts_user_id integer not null references users(us_id), ts_type integer not null references task_type(tt_id), ts_priority integer not null references priority(pr_id), ts_date date not null, ts_description varchar(255) not null)");
            pstmt.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("ERROR: " + ex.getMessage());
                }
            }
        }
    }

    public void seedTables() {
        Connection conn = null;
        PreparedStatement pstmt;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("INSERT INTO Priority VALUES (1, 'High')");
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement("INSERT INTO Priority VALUES (2, 'Medium')");
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement("INSERT INTO Priority VALUES (3, 'Low')");
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement("INSERT INTO Task_type VALUES (1, 'Personal')");
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement("INSERT INTO Task_type VALUES (2, 'Professional')");
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("ERROR: " + ex.getMessage());
                }
            }
        }
    }

    public void displayTables() {
        Connection conn = null;
        PreparedStatement pstmt;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM Priority");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Priority ID: " + rs.getInt(1) + ", Priority: " + rs.getString(2));
            }
            pstmt = conn.prepareStatement("SELECT * FROM Task_type");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("TaskType ID: " + rs.getInt(1) + ", TaskType: " + rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("ERROR: " + ex.getMessage());
                }
            }
        }
    }
    public String getDbError() {
        return dbError;
    }

}
