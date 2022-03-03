package pawforyou.pawforyou.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawforyou.pawforyou.models.Category;
import pawforyou.pawforyou.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories :: add);
        return categories;
    }

    public Category addCategory(String name) {
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
        return category;
    }

    public Optional<Category> getCategoryById(int id){
        return this.categoryRepository.findById(id);
    }
}
