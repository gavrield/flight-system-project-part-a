package dao;

import java.sql.*;

/**
 * database connection class
 */
public class Repository {
    public static String USER = "postgres";
    public static String DRIVER = "org.postgresql.Driver";
    public static String PASSWORD = "s2s2s2s2";
    public static String URL = "jdbc:postgresql://localhost:5432/FLIGHT_SYSTEM;";
    private Connection connection;
    private Statement statement;

    /**
     * making the connection with the database server
     * @param url
     * @param user
     * @param password
     * @return database connection
     */
    public Connection getConnection(String url, String user, String password) {
        try {
            Class.forName(this.DRIVER);
            this.connection = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * creating the ability to query the database
     * @return statement
     */
    public Statement getStatement() {
        try {
            this.statement = this.connection.createStatement();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return statement;
    }
}
