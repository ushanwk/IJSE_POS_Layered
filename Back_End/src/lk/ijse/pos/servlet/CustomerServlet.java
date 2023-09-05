package lk.ijse.pos.servlet;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = {"/customer"})
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaeePosApp", "root", "ushan1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer");

            ResultSet resultSet = preparedStatement.executeQuery();

            resp.addHeader("Access-Control-Allow-Origin","*");
            resp.addHeader("Content-Type","application/json");

            JsonArrayBuilder json = Json.createArrayBuilder();

            while(resultSet.next()){
                String custId = resultSet.getString(1);
                String custName = resultSet.getString(2);
                String custAddress = resultSet.getString(3);
                String custSalary = resultSet.getString(4);

                System.out.println(custId + custName + custAddress + custSalary);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
