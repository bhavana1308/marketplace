package org.launchcode.marketplace.controller;

import jakarta.servlet.http.HttpSession;
import org.launchcode.marketplace.model.Buyer;
import org.launchcode.marketplace.model.Product;
import org.launchcode.marketplace.mybatis.BuyerMapper;
import org.launchcode.marketplace.mybatis.ProductsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ProductApiController {

    private final BuyerMapper buyerMapper;
    private final ProductsMapper productsMapper;

    @Autowired
    public ProductApiController(BuyerMapper buyerMapper, ProductsMapper productsMapper) {
        this.buyerMapper = buyerMapper;
        this.productsMapper = productsMapper;
    }

    @GetMapping("/list")
    public List<Product> getProductList(HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute("buyerId");
        if (buyerId != null) {
            Buyer buyer = buyerMapper.getBuyerById(buyerId);

            List<Product> productList = productsMapper.getAllFromProducts();

            return productList;
        } else {
            return Collections.emptyList();
        }
    }

    @GetMapping("/category/{categoryId}")
    public Map<String, Object> viewProductsByCategory(
            @PathVariable int categoryId,
            HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute("buyerId");
        if (buyerId != null) {
            List<Product> products = productsMapper.getProductsByCategory(categoryId);
            String categoryName = getCategoryNameById(categoryId);
            Buyer buyer = buyerMapper.getBuyerById(buyerId);

            Map<String, Object> response = new HashMap<>();

            response.put("categoryName", categoryName);
            response.put("products", products);
            return response;
        }
        return null;
    }


    private String getCategoryNameById(int categoryId) {
        Map<Integer, String> categoryMap = new HashMap<>();
        categoryMap.put(1, "Shirts");
        categoryMap.put(2, "Dresses");
        categoryMap.put(3, "Electronics");
        return categoryMap.get(categoryId);
    }

}
