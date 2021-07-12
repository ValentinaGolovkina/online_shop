package ru.valensiya.online_shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.valensiya.online_shop.dao.ProductDao;
import ru.valensiya.online_shop.model.Product;
import ru.valensiya.online_shop.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public Product findById(Long id) {
        return productDao.findById(id);
    }

    public void saveNewProduct(String title, int price) {
        Product product = new Product(null, title, price);
        if (product.getPrice() <= 0) {
            return;
        }
        productDao.save(product);
    }
}
