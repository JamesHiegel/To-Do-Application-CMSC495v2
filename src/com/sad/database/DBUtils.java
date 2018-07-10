package com.sad.database;

import com.sad.yeti.LocalEvent;
import com.sad.yeti.YETI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;

public class DBUtils {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_URL = "jdbc:derby:YETI;create=true";
    private String dbError=null;

    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException ex) {
            dbError = "Connection to Database Failed.";
            System.out.println("ERROR: " + ex.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: Class not found for " + DRIVER);
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
            /*
                JT - 06/28:
                Modified to add ts_custom_tag, ts_notify, and ts_notfied columns
            */
            pstmt = conn.prepareStatement("CREATE TABLE Task (ts_id integer Primary Key, ts_user_id integer not null references users(us_id), ts_type integer not null references task_type(tt_id), ts_priority integer not null references priority(pr_id), ts_date date not null, ts_description varchar(255) not null, ts_custom_tag varchar(30), ts_notify char, ts_notified char)");
            pstmt.execute();
            pstmt = conn.prepareStatement("CREATE TABLE Version (version double primary key)");
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

    public Double getVersion() {
        Connection conn = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Double version=0.0;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("SELECT version from Version");
            rs = pstmt.executeQuery();
            while (rs.next())
                version = rs.getDouble(1);
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
        return version;
    }

    public void createVersion() {
        Connection conn = null;
        PreparedStatement pstmt;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("CREATE TABLE Version (version double primary key)");
            pstmt.execute();
            pstmt = conn.prepareStatement("INSERT INTO Version VALUES (1.1)");
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

    public void updateDatabase() {
        Connection conn = null;
        PreparedStatement pstmt;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("ALTER TABLE Task ADD ts_custom_tag varchar(30)");
            pstmt.execute();
            pstmt = conn.prepareStatement("ALTER TABLE Task ADD ts_notify char");
            pstmt.execute();
            pstmt = conn.prepareStatement("ALTER TABLE Task ADD ts_notified char");
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
            pstmt = conn.prepareStatement("INSERT INTO Version VALUES (1.1)");
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

    public static void deleteTask(LocalEvent le) {
        Connection conn = null;
        PreparedStatement pstmt;
        try {
            conn = DriverManager.getConnection(DB_URL);
            pstmt = conn.prepareStatement("DELETE FROM Task WHERE ts_id = ?");
            pstmt.setInt(1, le.getRecordID());
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

    public static void addTask(LocalEvent le) {
        Connection conn = null;
        PreparedStatement pstmt;
        try {
            conn = DriverManager.getConnection(DB_URL);
            pstmt = conn.prepareStatement("insert into Task values (next value for task_seq, ?, ?, ?, ?, ?, ?, ?, null)");
            pstmt.setInt(   1, YETI.getUserID());
            pstmt.setInt(   2,    (le.getPersonal()?1:2)); //Task Type - 1=Personal, 2=Professional
            pstmt.setInt(   3,    (le.getPriority())); //Priority - 1=High, 2=Medium, 3=Low
            pstmt.setDate(  4,    java.sql.Date.valueOf(le.getDate())); //Date
            pstmt.setString(5,    le.getDescription()); //Description
            pstmt.setString(6, le.getTag()); //Custom Tag
            pstmt.setString(7, le.getNotify()); //Notify
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

    public static void updateTask(LocalEvent le) {
        Connection conn = null;
        PreparedStatement pstmt;
        try {
            conn = DriverManager.getConnection(DB_URL);
            pstmt = conn.prepareStatement("update Task set ts_type = ?, ts_priority = ?, ts_date = ?, ts_description = ?, ts_custom_tag = ?, ts_notify = ? where ts_id = ?");
            pstmt.setInt(   1,    (le.getPersonal()?1:2)); //ts_type - 1=Personal, 2=Professional
            pstmt.setInt(   2,    (le.getPriority())); //ts_priority - 1=High, 2=Medium, 3=Low
            pstmt.setDate(  3,    java.sql.Date.valueOf(le.getDate())); //ts_date
            pstmt.setString(4,    le.getDescription()); //ts_description
            pstmt.setString(5, le.getTag()); //ts_custom_tag
            pstmt.setString(6, le.getNotify()); //ts_notify
            pstmt.setInt(   7, le.getRecordID()); //ts_id
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

    /*
        JT - 06/28:
        Renamed getPersonalTasks to getTasks since it handles both.
     */
    public static ObservableList<LocalEvent> getTasks(int userID, String taskType) {
        Connection conn = null;
        ObservableList<LocalEvent> leList = FXCollections.observableArrayList();
        PreparedStatement pstmt;
        ResultSet rs;
        boolean personal = false;
        try {
            if (taskType.equals("Personal")) personal=true;

            conn = DriverManager.getConnection(DB_URL);
            pstmt = conn.prepareStatement("SELECT ts_id,  pr_id, ts_date, ts_description, ts_custom_tag, ts_notify FROM Task JOIN Priority on ts_priority = pr_id JOIN Task_Type on ts_type = tt_id where ts_user_id = ? and tt_description = ?");
            pstmt.setInt(1, userID);
            pstmt.setString( 2, taskType);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int taskID = rs.getInt(1);
                int priorityID = rs.getInt( 2);
                LocalDate date = rs.getDate(3).toLocalDate();
                String description = rs.getString(4);

                /*
                    JT - 06:28:
                    Added setting the following to be passed to LocalEvent
                 */
                String customTag = rs.getString(5);
                String notify = rs.getString(6);

                leList.add(new LocalEvent(taskID, priorityID, date, description, personal, customTag, notify));
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
        return leList;
    }

    public static void closeDB() {
        try {
            System.out.println("Closing DB");
            DriverManager.getConnection("jdbc:derby:YETI;shutdown=true");
        } catch (SQLException ex) {
            //System.out.println("ERROR: " + ex.getMessage());
            //Always throws and exception, so we just need to ignore it here.
        }
    }

    public static String getUsername(int userID) {
        String username="";
        Connection conn = null;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            conn = DriverManager.getConnection(DB_URL);
            pstmt = conn.prepareStatement("SELECT us_userName FROM Users where us_id = ?");
            pstmt.setInt(1, userID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                username  = rs.getString(1);
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
        return username;
    }

    public String getDbError() {
        return dbError;
    }

}
