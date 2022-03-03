package pawforyou.pawforyou.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pawforyou.pawforyou.models.Category;
import pawforyou.pawforyou.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class ApiCategoryController {
    @Autowired private CategoryService categoryService;

    @PostMapping
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category.getName());
    }

    @GetMapping
    public List<Category> getCategories(){
        return this.categoryService.getAllCategories();
    }


}
