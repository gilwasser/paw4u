package pawforyou.pawforyou.repositories;

import org.springframework.data.repository.CrudRepository;

import pawforyou.pawforyou.models.Client;

public interface ClientRepository extends CrudRepository<Client, Integer>{
}
