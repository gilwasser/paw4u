package pawforyou.pawforyou.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import pawforyou.pawforyou.models.Credentials;

@Service
public interface CredentialsRepository extends CrudRepository<Credentials, Integer> {
    @Query("SELECT p FROM Credentials p WHERE (p.email = ?1 AND p.password = ?2)")
    List<Credentials> getCredentialsByLogin(String email, String password);

    @Query("SELECT p FROM Credentials p WHERE p.email = ?1")
    List<Credentials> getByMail(String email);
}
