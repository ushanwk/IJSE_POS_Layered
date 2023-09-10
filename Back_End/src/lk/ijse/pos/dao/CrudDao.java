package lk.ijse.pos.dao;

import java.sql.SQLException;

public interface CrudDao<T> extends SuperDao{

    public boolean add(T entity) throws SQLException, ClassNotFoundException;
    public boolean update(T entity) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;

}
