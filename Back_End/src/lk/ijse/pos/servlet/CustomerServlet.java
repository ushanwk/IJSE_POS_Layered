package lk.ijse.pos.servlet;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = {"/customer"})
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try(PrintWriter writer = resp.getWriter()) {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaeePosApp", "root", "ushan1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer");

            ResultSet resultSet = preparedStatement.executeQuery();

            resp.addHeader("Access-Control-Allow-Origin","*");
            resp.addHeader("Content-Type","application/json");

            JsonArrayBuilder allCustomers = Json.createArrayBuilder();

            while(resultSet.next()){

                String custId = resultSet.getString(1);
                String custName = resultSet.getString(2);
                String custAddress = resultSet.getString(3);
                String custSalary = resultSet.getString(4);

                JsonObjectBuilder customer = Json.createObjectBuilder();

                customer.add("id", custId);
                customer.add("name", custName);
                customer.add("address", custAddress);
                customer.add("salary", custSalary);

                allCustomers.add(customer.build());

            }

            writer.print(allCustomers.build());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Access-Control-Allow-Origin","*");

        String id = req.getParameter("cusID");
        String name = req.getParameter("cusName");
        String address = req.getParameter("cusAddress");
        String salary = req.getParameter("cusSalary");


        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaeePosApp", "root", "ushan1234");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Customer VALUES (?, ?, ?, ?)");
            preparedStatement.setObject(1, id);
            preparedStatement.setObject(2, name);
            preparedStatement.setObject(3, address);
            preparedStatement.setObject(4, Integer.parseInt(salary));

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Content-Type","application/json");
        resp.addHeader("Access-Control-Allow-Origin","*");

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        String salary = jsonObject.getString("salary");

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaeePosApp", "root", "ushan1234");

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Customer SET CustomerName=?, CustomerAddress=?, CustomerSalary=? WHERE CustomerId=?");
            preparedStatement.setObject(1, name);
            preparedStatement.setObject(2, address);
            preparedStatement.setObject(3, Integer.parseInt(salary));
            preparedStatement.setObject(4, id);

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Access-Control-Allow-Origin","*");

        String id = req.getParameter("cusId");



    }


    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.addHeader("Access-Control-Allow-Methods","PUT");
        resp.addHeader("Access-Control-Allow-Methods","DELETE");
        resp.addHeader("Access-Control-Allow-Headers","content-type");
    }
}
