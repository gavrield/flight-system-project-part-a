package dao;
import java.sql.SQLException;
import java.util.List;

/**
 * the basic CRUD operations
 * @param <POCO>
 * @param <K> PK type
 */
public interface DAO<POCO, K>{
    public POCO get(K id);
    public List<POCO> getAll();
    public void add(POCO poco) throws SQLException;
    public void remove(POCO poco) throws SQLException;
    public void update(POCO poco)throws SQLException;
}
