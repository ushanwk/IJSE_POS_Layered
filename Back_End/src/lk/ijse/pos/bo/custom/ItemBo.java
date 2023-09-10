package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBo;
import lk.ijse.pos.model.CustomerDTO;
import lk.ijse.pos.model.ItemDTO;

import java.sql.SQLException;

public interface ItemBo extends SuperBo {
    public void addCItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    public void updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    public void deleteItem(String code) throws SQLException, ClassNotFoundException;
}
