package pawforyou.pawforyou.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pawforyou.pawforyou.models.Client;
import pawforyou.pawforyou.models.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    @Query("SELECT p FROM Purchase p ORDER BY p.date")
    List<Purchase> findPurchases();

    @Query("SELECT p FROM Purchase p WHERE p.client = ?1")
    List<Purchase> findPurchasesByClient(Client client);
}
