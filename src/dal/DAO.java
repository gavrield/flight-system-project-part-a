package dal;

import java.util.List;

public interface DAO<T>{
    public T get(int id);
    public List<T> getAll();
    public void add(T t);
    public void remove(T t);
    public void update(T t);
}
