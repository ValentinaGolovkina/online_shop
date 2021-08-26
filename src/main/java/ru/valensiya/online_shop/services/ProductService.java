package ru.valensiya.online_shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.valensiya.online_shop.model.Product;
import ru.valensiya.online_shop.repositories.ProductRepository;
import ru.valensiya.online_shop.repositories.specifications.ProductSpecifications;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> findPage(int pageIndex, int pageSize, Specification<Product> spec) {
        return productRepository.findAll(spec, PageRequest.of(pageIndex, pageSize));
    }

    public Product save(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> findPageWithFilter(int pageIndex, int pageSize,  BigDecimal minPrice, String title, BigDecimal maxPrice) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (title != null) {
            spec = spec.and(ProductSpecifications.titleLike(title));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLessOrEqualsThan(maxPrice));
        }
        return findPage(pageIndex, pageSize, spec);
    }
}