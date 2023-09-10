package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBo;
import lk.ijse.pos.model.CustomerDTO;

import java.sql.SQLException;

public interface CustomerBo extends SuperBo {

    public void addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    public void updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    public void deleteCustomer(String id) throws SQLException, ClassNotFoundException;

}
