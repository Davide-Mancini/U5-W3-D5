package davidemancini.U5_W3_D5.services;

import davidemancini.U5_W3_D5.entities.User;
import davidemancini.U5_W3_D5.exceptions.MyBadRequestException;
import davidemancini.U5_W3_D5.exceptions.NotFoundException;
import davidemancini.U5_W3_D5.payloads.NewUserDTO;
import davidemancini.U5_W3_D5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bCrypt;


    public Page<User> findAll(int pageNumber, int pageSize, String sortBY) {
        if (pageSize > 30)
            pageSize = 30; //SE NELLA PAGINAZIONE IL NUMERI DI RISULTATI PER PAGINA è MAGGIORE DI 30 IMPOSTO 30 COME MASSIMO
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBY).ascending());
        return userRepository.findAll(pageable);
    }

    public User register(NewUserDTO body) {
        User newUser = new User(body.nome(), body.cognome(), body.email(), bCrypt.encode(body.password()));
        return userRepository.save(newUser);
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("utente non trovato " + email));
    }
}
