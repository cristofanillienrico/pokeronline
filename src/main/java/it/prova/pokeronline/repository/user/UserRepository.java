package it.prova.pokeronline.repository.user;

import it.prova.pokeronline.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>, CustomUserRepository {

    User findByUsername(String username);


}
