package org.launchcode.marketplace.controller;

import org.launchcode.marketplace.model.Product;
import org.launchcode.marketplace.mybatis.ProductsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    @Autowired
    private final ProductsMapper productsMapper;


    @Autowired
    public ProductController(ProductsMapper productsMapper) {
        this.productsMapper = productsMapper;
    }

    @GetMapping("/productList")
    public String listProducts(Model model) {
        List<Product> products = productsMapper.getAllFromProducts();
        model.addAttribute("products", products);
        return "product/productList";
    }

    @GetMapping("/product/category/{categoryId}")
    public String viewProductsByCategory(@PathVariable int categoryId, Model model) {
        List<Product> products = productsMapper.getProductsByCategory(categoryId);
        String categoryName = getCategoryNameById(categoryId);
        model.addAttribute("products", products);
        model.addAttribute("categoryName", categoryName);
        return "product/byCategory";
    }

    private String getCategoryNameById(int categoryId) {
        Map<Integer, String> categoryMap = new HashMap<>();
        categoryMap.put(1, "Shirts");
        categoryMap.put(2, "Dresses");
        categoryMap.put(3, "Electronics");
        return categoryMap.get(categoryId);
    }

    @GetMapping("/products/{productName}")
    public String viewProduct(@PathVariable String productName, Model model) {
        Product product = productsMapper.getByProductName(productName);
        if (product != null) {
            model.addAttribute("product", product);
            return "product/product";
        } else {
            return "product/productNotFound";
        }
    }
}
