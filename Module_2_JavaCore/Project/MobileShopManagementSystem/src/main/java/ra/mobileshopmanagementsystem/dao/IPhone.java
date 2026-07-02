package ra.mobileshopmanagementsystem.dao;

import ra.mobileshopmanagementsystem.model.Product;

import java.util.List;

public interface IPhone {
    public boolean addPhone();
    public boolean deletePhone();
    public boolean updatePhone();
    public List<Product> getAllPhone();
    public Product getPhoneById(int id);
    public List<Product> getPhoneByBrand(String brand);
    public List<Product> getPhoneByNameAndAvailabilityStock(String name);


}
