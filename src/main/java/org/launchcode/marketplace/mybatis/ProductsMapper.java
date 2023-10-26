package org.launchcode.marketplace.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.launchcode.marketplace.model.Product;

import java.util.List;


//@Component

@Mapper
public interface ProductsMapper {

    Product getByProductName(String productName);

    List<Product> getAllFromProducts();

    List<Product> getProductsByCategory(int categoryId);

}
