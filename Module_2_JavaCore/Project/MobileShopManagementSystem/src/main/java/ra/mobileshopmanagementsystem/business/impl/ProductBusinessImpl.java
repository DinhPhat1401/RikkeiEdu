package ra.mobileshopmanagementsystem.business.impl;

import ra.mobileshopmanagementsystem.business.IProductBusiness;
import ra.mobileshopmanagementsystem.dao.impl.ProductDaoImpl;
import ra.mobileshopmanagementsystem.model.Product;

import java.util.List;

public class ProductBusinessImpl implements IProductBusiness {
    private ProductDaoImpl productDao = new ProductDaoImpl();

    @Override
    public List<Product> getPhoneByBrand(String brand) {
        List<Product> phones = productDao.getAllPhone();
        return phones.stream()
                .filter(phone -> phone.getBrand().toLowerCase().contains(brand.toLowerCase()))
                .toList();
    }

    @Override
    public List<Product> getPhoneByNameAndAvailabilityStock(String name) {
        List<Product> phones = productDao.getAllPhone();
        return phones.stream()
                .filter(phone -> phone.getName().toLowerCase().contains(name.toLowerCase()) && phone.getStock() > 0)
                .toList();
    }

    @Override
    public List<Product> getPhoneInRange(double minPrice, double maxPrice) {
        List<Product> phones = productDao.getAllPhone();
        return phones.stream()
                .filter(phone -> phone.getPrice() >= minPrice && phone.getPrice() <= maxPrice)
                .toList();
    }
}
