package ra.mobileshopmanagementsystem.dao.impl;

import ra.mobileshopmanagementsystem.dao.IPhone;
import ra.mobileshopmanagementsystem.model.Product;

import java.util.List;

public class PhoneDaoImpl implements IPhone {
    @Override
    public boolean addPhone() {
        return false;
    }

    @Override
    public boolean deletePhone() {
        return false;
    }

    @Override
    public boolean updatePhone() {
        return false;
    }

    @Override
    public List<Product> getAllPhone() {
        return List.of();
    }

    @Override
    public Product getPhoneById(int id) {
        return null;
    }

    @Override
    public List<Product> getPhoneByBrand(String brand) {
        return List.of();
    }

    @Override
    public List<Product> getPhoneByNameAndAvailabilityStock(String name) {
        return List.of();
    }
}
