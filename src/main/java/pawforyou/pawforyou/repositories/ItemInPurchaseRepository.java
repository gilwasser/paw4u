package pawforyou.pawforyou.repositories;

import org.springframework.data.repository.CrudRepository;

import pawforyou.pawforyou.models.ItemInPurchase;

public interface ItemInPurchaseRepository extends CrudRepository<ItemInPurchase, Integer> {
    
}
