package pawforyou.pawforyou.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pawforyou.pawforyou.models.Client;
import pawforyou.pawforyou.models.Purchase;
import pawforyou.pawforyou.models.State;
import pawforyou.pawforyou.repositories.PurchaseRepository;

@Service
public class OrderService {
    @Autowired
    private PurchaseRepository purchaseRepository;



    public List<Purchase> getOrders(){
        List<Purchase> purchases = new ArrayList<>();
        this.purchaseRepository.findPurchases().forEach(purchases:: add);

        return purchases;
    }

    public void updateOrderState(int id, String state){
        Optional<Purchase> purchase = this.purchaseRepository.findById(id);
        if(purchase.isEmpty()){
            return;
        }
        for(State s: State.values()){
            if(s.label.equals(state)){
                this.purchaseRepository.save(purchase.get().state(s));
            }
        }
    }

    public List<Purchase> getOrderOfClient(Client client){
        List<Purchase> orders = this.purchaseRepository.findPurchasesByClient(client);
        return orders;
    }

}
