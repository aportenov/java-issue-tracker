package softuni.repositories.user;

import softuni.domains.dto.UserDto;
import softuni.domains.entity.User;

import javax.ejb.Local;
import javax.ejb.Stateless;

public interface UserRepository {

    User findByUserNameAndPassword(String username, String password);

    void save(User user);

    User findByUsername(String authorUsername);

    long getUsersCount();
}
