package pawforyou.pawforyou.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pawforyou.pawforyou.models.Session;

public interface SessionRepository extends CrudRepository<Session, Integer>{
    @Query("SELECT p FROM Session p where p.token = ?1")
    List<Session> findByToken(String token);
}
