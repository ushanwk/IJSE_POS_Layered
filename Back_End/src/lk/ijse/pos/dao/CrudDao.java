package lk.ijse.pos.dao;

import java.sql.SQLException;

public interface CrudDao<T> extends SuperDao{

    public boolean add(T entity) throws SQLException, ClassNotFoundException;

}
