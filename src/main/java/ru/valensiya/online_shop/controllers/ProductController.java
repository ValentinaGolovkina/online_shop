package ru.valensiya.online_shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.valensiya.online_shop.dto.ProductDto;
import ru.valensiya.online_shop.exceptions.ResourceNotFoundException;
import ru.valensiya.online_shop.model.Category;
import ru.valensiya.online_shop.model.Product;
import ru.valensiya.online_shop.repositories.specifications.ProductSpecifications;
import ru.valensiya.online_shop.services.CategoryService;
import ru.valensiya.online_shop.services.ProductService;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return new ProductDto(p);
    }

    @GetMapping
    public Page<ProductDto> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex,
                                    @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
                                    @RequestParam(name = "title", required = false) String title) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (title != null) {
            spec = spec.and(ProductSpecifications.titleLike(title));
        }
        return productService.findPage(pageIndex - 1, 10, spec).map(ProductDto::new);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto newProductDto) {
        Product product = new Product();
        product.setPrice(newProductDto.getPrice());
        product.setTitle(newProductDto.getTitle());
        Category category = categoryService.findByTitle(newProductDto.getCategoryTitle());
        product.setCategory(category);
        return new ProductDto(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
