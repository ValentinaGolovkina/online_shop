package ru.valensiya.online_shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.valensiya.online_shop.model.Product;
import ru.valensiya.online_shop.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping ("/products/{id}")
    @ResponseBody
    public Product showProductInfo(@PathVariable Long id) {
        return  productService.findById(id);
    }

    @GetMapping("/products")
    public Page<Product> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        return productService.findPage(pageIndex - 1, 10);
    }

    @GetMapping("/products/find_by_min_price")
    @ResponseBody
    public List<Product> findByMinPrice(@RequestParam(name = "min") int min) {
        return productService.findByMinPrice(min);
    }

    @GetMapping("/products/find_by_max_price")
    @ResponseBody
    public List<Product> findByMaxPrice(@RequestParam(name = "max") int max) {
        return productService.findByMaxPrice(max);
    }

    @GetMapping("/products/find_by_price")
    @ResponseBody
    public List<Product> findByMinPrice(@RequestParam(name = "min") int min, @RequestParam(name = "max") int max) {
        return productService.findByPrice(min, max);
    }

    @PostMapping("/products")
    public Product saveNewProduct(@RequestParam String title, @RequestParam int price) {
        return productService.saveNewProduct(title, price);
    }

    @GetMapping ("/products/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
