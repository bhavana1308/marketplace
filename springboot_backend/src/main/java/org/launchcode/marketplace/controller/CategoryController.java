package org.launchcode.marketplace.controller;

import org.launchcode.marketplace.model.Category;
import org.launchcode.marketplace.mybatis.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }


    @GetMapping("/all")
    public String viewAllCategories(Model model) {
        List<Category> categories = categoryMapper.getAllFromCategory();
        model.addAttribute("categories", categories);
        return "category/all";
    }

    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category) {
        categoryMapper.insertCategory(category);
        return "redirect:/categories/all";
    }
}


