package ru.valensiya.online_shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.valensiya.online_shop.dto.ProductDto;
import ru.valensiya.online_shop.model.Category;
import ru.valensiya.online_shop.model.Product;
import ru.valensiya.online_shop.services.CategoryService;
import ru.valensiya.online_shop.services.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        ProductDto productDto = new ProductDto(productService.findById(id));
        return productDto;
    }

    @GetMapping
    public Page<Product> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        Page<Product> page = productService.findPage(pageIndex - 1, 10);
        return productService.findPage(pageIndex - 1, 10);
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
