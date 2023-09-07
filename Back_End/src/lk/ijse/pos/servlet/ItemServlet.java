package lk.ijse.pos.servlet;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = {"/item"})
public class ItemServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try(PrintWriter writer = resp.getWriter()){

            resp.addHeader("Access-Control-Allow-Origin","*");
            resp.addHeader("Content-Type","application/json");

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaeePosApp", "root", "ushan1234");

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Item");
            ResultSet resultSet = preparedStatement.executeQuery();

            JsonArrayBuilder allItems = Json.createArrayBuilder();

            while(resultSet.next()){

                String code = resultSet.getString("itemCode");
                String name = resultSet.getString("itemName");
                String qty = resultSet.getString("itemQty");
                String price = resultSet.getString("itemPrice");

                JsonObjectBuilder item = Json.createObjectBuilder();

                item.add("code", code);
                item.add("name", name);
                item.add("qty", qty);
                item.add("price", price);

                allItems.add(item.build());

            }

            writer.print(allItems.build());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.addHeader("Access-Control-Allow-Methods","PUT");
        resp.addHeader("Access-Control-Allow-Methods","DELETE");
        resp.addHeader("Access-Control-Allow-Headers","content-type");
    }

}
