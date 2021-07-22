package ru.valensiya.online_shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.valensiya.online_shop.model.Product;
import ru.valensiya.online_shop.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public Product saveNewProduct(String title, int price) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        if (product.getPrice() <= 0) {
            return null;
        }
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findByPrice(int min, int max) {
        return productRepository.findAllByPriceBetween(min, max);
    }

    public List<Product> findByMaxPrice(int max) {
        return productRepository.findAllByPriceLessThanEqual(max);
    }

    public List<Product> findByMinPrice(int min) {
        return productRepository.findAllByPriceGreaterThanEqual(min);
    }

    public Page<Product> findPage(int pageIndex, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }
}
