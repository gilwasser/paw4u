package pawforyou.pawforyou.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pawforyou.pawforyou.models.Client;
import pawforyou.pawforyou.models.Credentials;
import pawforyou.pawforyou.models.LoginForm;
import pawforyou.pawforyou.models.RegisterForm;
import pawforyou.pawforyou.models.Session;
import pawforyou.pawforyou.repositories.ClientRepository;
import pawforyou.pawforyou.repositories.CredentialsRepository;
import pawforyou.pawforyou.repositories.SessionRepository;

@Service
public class AuthService {
    @Autowired
    private CredentialsRepository credentialsRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private SessionRepository sessionRepository;

    public Client register(RegisterForm form) {
        List<Credentials> credentialsList = credentialsRepository.getByMail(form.getEmail());
        if (credentialsList.size() > 0) {
            return null;
        }
        Client client = new Client();
        client.name(form.getName())
                .lastName(form.getLastName())
                .phone(form.getPhone())
                .address(form.getAddress());
        client = clientRepository.save(client);

        Credentials credentials = new Credentials();
        credentials.email(form.getEmail())
                .password(form.getPassword())
                .client(client);
        credentialsRepository.save(credentials);
        return client;
    }

    public Session login(LoginForm form) {
        List<Credentials> credentials = credentialsRepository
                .getCredentialsByLogin(form.getEmail(), form.getPassword());
        if (credentials.size() > 0) {
            Session session = new Session();
            session.client(credentials.get(0).getClient())
                    .token(UUID.randomUUID().toString())
                    .expirationDate(new Date((new Date()).getTime() + 30*60000));
            sessionRepository.save(session);
            return session;
        }
        return null;
    }

    public Session getSession(String token){
        List<Session> sessions = sessionRepository.findByToken(token);
        System.out.println(token);
        if(sessions.size() > 0 ) {    
            if(sessions.get(0).getEpirationDate().after(new Date())){
                Session session = sessions.get(0).expirationDate(new Date((new Date()).getTime() + 30*60000));
                sessionRepository.save(session);
                return session;
            }
            sessionRepository.delete(sessions.get(0));
        }
        return null;
    }

    public void logout(String value) {
        List<Session> sessions = sessionRepository.findByToken(value);
        if(sessions.size() > 0) {
            sessionRepository.delete(sessions.get(0));
        }
    }

    public String getName(String token) {
        Session session = getSession(token);
        if(session == null) {
            return "";
        }
        Client client = session.getClient();
        return client.getName() + ' ' + client.getLastName();
    }

    public Client getAdmin(){
        return clientRepository.findById(1).get();
    }

    public Client getClient(String token) {
        Session session = getSession(token);
        if(session == null){
            return null;
        }
        return session.getClient();
    }
}
