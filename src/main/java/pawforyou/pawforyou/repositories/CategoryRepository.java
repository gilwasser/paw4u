package pawforyou.pawforyou.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import pawforyou.pawforyou.models.Category;

@Service
public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
