package ra.mobileshopmanagementsystem.dao;

import ra.mobileshopmanagementsystem.model.Customer;

import java.util.List;

public interface ICustomer {
    public boolean addCustomer();
    public boolean deleteCustomer();
    public boolean updateCustomer();
    public List<Customer> getAllCustomer();
    public Customer getCustomerById(int id);

}
