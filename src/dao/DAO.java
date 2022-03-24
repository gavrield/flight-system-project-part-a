package dao;
import java.util.List;

/**
 * the basic CRUD operations
 * @param <POCO>
 * @param <K> PK type
 */
public interface DAO<POCO, K>{
    public POCO get(K id);
    public List<POCO> getAll();
    public void add(POCO poco);
    public void remove(POCO poco);
    public void update(POCO poco);
}
