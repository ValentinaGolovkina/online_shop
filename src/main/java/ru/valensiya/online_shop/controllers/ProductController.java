package ru.valensiya.online_shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.valensiya.online_shop.model.Product;
import ru.valensiya.online_shop.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

/*

    @GetMapping
    public String showMainPage(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index";
    }

    @GetMapping("/products/add")
    public String showAddProductForm() {
        return "add_product_form";
    }
*/


    @GetMapping ("/products/{id}")
    @ResponseBody
    public Product showProductInfo(@PathVariable Long id) {
        return  productService.findById(id);
    }

    @GetMapping ("/products")
    @ResponseBody
    public List<Product> showAllProductsInfo() {
        return  productService.findAll();
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
    @ResponseBody
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "Продукт удален";
    }
}
