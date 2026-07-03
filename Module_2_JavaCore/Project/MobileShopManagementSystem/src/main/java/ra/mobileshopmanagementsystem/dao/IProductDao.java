package ra.mobileshopmanagementsystem.dao;

import ra.mobileshopmanagementsystem.model.Product;

import java.util.List;

public interface IProductDao {
    public boolean addPhone();
    public boolean deletePhone();
    public boolean updatePhone();
    public List<Product> getAllPhone();



}
