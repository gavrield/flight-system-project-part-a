package dal;

import java.sql.*;

public class Repository {
    private Connection connection;
    private Statement statement;


    public Connection getConnection(String url, String user, String password) {
        try {
            Class.forName(ConstantsAndQueries.DRIVER);
            this.connection = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

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
