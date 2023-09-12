package lk.ijse.pos.servlet;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/order"})
public class PlaceOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("aa");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Content-Type", "application/json");

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject orderJsonOb = reader.readObject();

        String orderId = orderJsonOb.getString("orderId");
        String date = orderJsonOb.getString("date");
        String cusId = orderJsonOb.getString("cusId");

        JsonArray itemDetails = orderJsonOb.getJsonArray("itemDet");

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaeePosApp ", "root", "ushan1234");
            connection.setAutoCommit(false);

            PreparedStatement pstm = connection.prepareStatement("INSERT INTO orders VALUES(? ,? ,?)");
            pstm.setObject(1, orderId);
            pstm.setObject(2, date);
            pstm.setObject(3, cusId);

            if (!(pstm.executeUpdate() > 0)) {
                connection.rollback();
                connection.setAutoCommit(true);
                System.out.println("Not Added");
            }

            for (JsonValue orderDetail : itemDetails) {

                JsonObject odObject = orderDetail.asJsonObject();

                String itemCode = odObject.getString("code");
                String qty = odObject.getString("qty");
                String avQty = odObject.getString("avQty");
                String unitPrice = odObject.getString("price");

                PreparedStatement pstm2 = connection.prepareStatement("INSERT INTO OrderDetails VALUES(?,?,?,?)");
                pstm2.setObject(1, orderId);
                pstm2.setObject(2, itemCode);
                pstm2.setObject(3, qty);
                pstm2.setObject(4, unitPrice);

                if (!(pstm2.executeUpdate() > 0)) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    System.out.println("Order Details Not added.!");
                }

                //update the item also
                PreparedStatement pstm3 = connection.prepareStatement("UPDATE Item SET qtyOnHand=? WHERE code=?");
                pstm3.setObject(2, itemCode);
                int availableQty = Integer.parseInt(avQty);
                int purchasingQty = Integer.parseInt(qty);
                pstm3.setObject(1, (availableQty - purchasingQty));

                if (!(pstm3.executeUpdate() > 0)) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    System.out.println("Item cannot be updated");
                }
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.addHeader("Access-Control-Allow-Methods","PUT");
        resp.addHeader("Access-Control-Allow-Methods","DELETE");
        resp.addHeader("Access-Control-Allow-Headers","content-type");
    }

}
