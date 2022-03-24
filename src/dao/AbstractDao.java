package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * dao will inherit database connection
 * @param <POCO> POCO type
 * @param <K> PK type mapping
 */
public abstract class AbstractDao<POCO, K> implements DAO<POCO, K>{
    protected Repository repository = new Repository();
    protected Connection connection;
    protected Statement statement;

    /**
     * sets the connection with the database
     */
    protected void setConnection(){
        connection = repository.getConnection
                (
                        Repository.URL,
                        Repository.USER,
                        Repository.PASSWORD
                );
        statement = repository.getStatement();
    }

    /**
     * close the connection with the database
     */
    protected void closeConnection(){
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
