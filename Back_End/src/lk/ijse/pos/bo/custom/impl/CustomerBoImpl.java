package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.CustomerBo;
import lk.ijse.pos.dao.DaoFactory;
import lk.ijse.pos.dao.custom.CustomerDao;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.model.CustomerDTO;

import java.sql.SQLException;

public class CustomerBoImpl implements CustomerBo {

    CustomerDao customerDao = (CustomerDao) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.CUSTOMER);

    @Override
    public void addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {

        Customer customer = new Customer(customerDTO.getCusId(), customerDTO.getCusName(), customerDTO.getCusAddress(), customerDTO.getCusSalary());

        customerDao.add(customer);

    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {

        Customer customer = new Customer(customerDTO.getCusId(), customerDTO.getCusName(), customerDTO.getCusAddress(), customerDTO.getCusSalary());

        customerDao.update(customer);

    }

}
