package ra.mobileshopmanagementsystem.business;

import ra.mobileshopmanagementsystem.model.Product;

import java.util.List;

public interface IProductBusiness {

    public List<Product> getPhoneByBrand(String brand);
    public List<Product> getPhoneByNameAndAvailabilityStock(String name);
    public List<Product> getPhoneInRange(double minPrice, double maxPrice);
}
