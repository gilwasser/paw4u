package pawforyou.pawforyou.repositories;

import org.springframework.data.repository.CrudRepository;

import pawforyou.pawforyou.models.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    
}
