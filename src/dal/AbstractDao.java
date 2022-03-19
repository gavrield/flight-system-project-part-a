package dal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDao<T> implements DAO<T>{
    protected Repository repository = new Repository();
    protected Connection connection;
    protected Statement statement;

    protected void setConnection(){
        connection = repository.getConnection
                (
                        ConstantsAndQueries.URL,
                        ConstantsAndQueries.USER,
                        ConstantsAndQueries.PASSWORD
                );
        statement = repository.getStatement();
    }

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
