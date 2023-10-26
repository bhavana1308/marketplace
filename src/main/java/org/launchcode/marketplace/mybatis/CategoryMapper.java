package org.launchcode.marketplace.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.launchcode.marketplace.model.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    Category getByCategoryId(int id);

    List<Category> getAllFromCategory();

    int insertCategory(Category Category);

    int updateCategory(Category Category);

}
