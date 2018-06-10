package com.sad.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
    public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    public static final String DB_URL = "jdbc:derby:YETI;create=true";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = DriverManager.getConnection(DB_URL);
        if (connection != null) {
            System.out.println("Database Connected");
        }
        connection.close();
    }
    public CreateDB() {
        try {
            // Create a named constant for the URL.
            // NOTE: This value is specific for Java DB.
            //final String DB_URL = "jdbc:derby:YETI;create=true";

            /**
             * Create a connection to the database.
             */
            Connection conn = DriverManager.getConnection(DB_URL);

            // Build the Users table.
            buildUsersTable(conn);

            // Build the Task_type table.
            buildTask_typeTable(conn);

            // Build the Tasks table.
            buildTaskTable(conn);

            // Build the Priority table.
            buildPriorityTable(conn);

            // Close the connection.
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The dropTables method drops any existing tables in case the database already exists.
     */
    public static void dropTables(Connection conn) {

        try {
            // Get a Statement object.
            Statement stmt = conn.createStatement();

            try {
                // Drop the Users table.
                stmt.execute("DROP TABLE Users");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
            }

            try {
                // Drop the Priority table.
                stmt.execute("DROP TABLE Priority");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
            }

            try {
                // Drop the Task_type table.
                stmt.execute("DROP TABLE Task_type");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
            }

            try {
                // Drop the Task table.
                stmt.execute("DROP TABLE Task");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    /**
     * The buildUsersTable method creates the
     * Users table and adds some rows to it.
     */
    public static void buildUsersTable(Connection conn) {
        try {
            // Get a Statement object.
            Statement stmt = conn.createStatement();

            stmt.execute("create sequence users_seq as int start with 1 increment by 1");
            // Create the Users table.
            stmt.execute("CREATE TABLE Users (" +
                              "  us_id integer primary key, " +
                              "  us_userName varchar(50) not null, " +
                              "  us_password varchar(50) not null)");
            // Insert row #1.
            stmt.execute("INSERT INTO Users VALUES ( " +
                              "  next value for users_seq, " +
                              "'  sdevadmin', " +
                              "'  sdevadmin' )");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    /**
     * The buildPriorityTable method creates
     * the Tasks table.
     */
    public static void buildPriorityTable(Connection conn) {
        try {
            // Get a Statement object.
            Statement stmt = conn.createStatement();

            // Create the Priority table.
            stmt.execute("CREATE TABLE Priority (" +
                              "  pr_id integer primary key, " +
                              "  pr_description varchar(20) not null)");

            // Insert rows.
            stmt.executeUpdate("INSERT INTO Priority VALUES (1, 'High')");
            stmt.executeUpdate("INSERT INTO Priority VALUES (2, 'Medium')");
            stmt.executeUpdate("INSERT INTO Priority VALUES (3, 'Low')");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }


    /**
     * The buildTask_typeTable method creates the
     * Task_type table and adds some rows to it.
     */
    public static void buildTask_typeTable(Connection conn) {
        try {
            // Get a Statement object.
            Statement stmt = conn.createStatement();

            // Create the table.
            stmt.execute("CREATE TABLE Task_type (" +
                              "  tt_id integer primary key, " +
                              "  tt_description varchar(20) not null)");

            // Add some rows to the new table.
            stmt.executeUpdate("INSERT INTO Task_type VALUES (1, 'Personal')");
            stmt.executeUpdate("INSERT INTO Task_type VALUES (2, 'Professional')");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    /**
     * The buildTaskTable method creates
     * the Tasks table.
     */
    public static void buildTaskTable(Connection conn) {
        try {
            // Get a Statement object.
            Statement stmt = conn.createStatement();
            stmt.execute("create sequence task_seq as int start with 1 increment by 1");
            // Create the table.
            stmt.execute("CREATE TABLE Task " +
                              "  ts_id integer Primary Key, " +
                              "  ts_user_id integer not null references users(us_id)," +
                              "  ts_type integer not null references task_type(tt_id)," +
                              "  ts_priority integer not null references priority(pr_id)," +
                              "  ts_date date not null," +
                              "  ts_description varchar(255) not null)");
            // Add some rows to the new table.
            stmt.executeUpdate("INSERT INTO Task VALUES" +
                                    "(NEXT VALUE FOR task_seq, 1, 1, 1, '06/01/2018', 'Personal High Priority Task')");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
