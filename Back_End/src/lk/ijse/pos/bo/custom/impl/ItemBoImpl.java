package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ItemBo;
import lk.ijse.pos.dao.DaoFactory;
import lk.ijse.pos.dao.custom.ItemDao;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.model.ItemDTO;

import java.sql.SQLException;

public class ItemBoImpl implements ItemBo {
    ItemDao itemDao = (ItemDao) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.ITEM);
    @Override
    public void addCItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {

        itemDao.add(new Item(itemDTO.getItemCode(), itemDTO.getItemName(), itemDTO.getItemQty(), itemDTO.getItemPrice()));

    }

    @Override
    public void updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {

        itemDao.update(new Item(itemDTO.getItemCode(), itemDTO.getItemName(), itemDTO.getItemQty(), itemDTO.getItemPrice()));

    }

    @Override
    public void deleteItem(String code) throws SQLException, ClassNotFoundException {

    }
}
