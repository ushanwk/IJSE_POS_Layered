package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.ItemDao;
import lk.ijse.pos.entity.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean add(Item entity) throws SQLException, ClassNotFoundException {

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaeePosApp", "root", "ushan1234");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Item VALUES (?, ?, ?, ?)");
            preparedStatement.setObject(1, entity.getItemCode());
            preparedStatement.setObject(2, entity.getItemName());
            preparedStatement.setObject(3, entity.getItemQty());
            preparedStatement.setObject(4, entity.getItemPrice());

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;

    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
