package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.CustomerDao;
import lk.ijse.pos.entity.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean add(Customer entity) throws SQLException, ClassNotFoundException {

        String id = entity.getCusId();
        String name = entity.getCusName();
        String address = entity.getCusAddress();
        int salary = entity.getCusSalary();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaeePosApp", "root", "ushan1234");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Customer VALUES (?, ?, ?, ?)");
            preparedStatement.setObject(1, id);
            preparedStatement.setObject(2, name);
            preparedStatement.setObject(3, address);
            preparedStatement.setObject(4, salary);

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;

    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {

        String id = entity.getCusId();
        String name = entity.getCusName();
        String address = entity.getCusAddress();
        int salary = entity.getCusSalary();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaeePosApp", "root", "ushan1234");

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Customer SET CustomerName=?, CustomerAddress=?, CustomerSalary=? WHERE CustomerId=?");
            preparedStatement.setObject(1, name);
            preparedStatement.setObject(2, address);
            preparedStatement.setObject(3, salary);
            preparedStatement.setObject(4, id);

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaeePosApp", "root", "ushan1234");

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Customer WHERE CustomerId=?");
            preparedStatement.setObject(1, id);

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;

    }

}
