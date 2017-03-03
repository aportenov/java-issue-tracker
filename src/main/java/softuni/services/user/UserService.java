package softuni.services.user;

import softuni.domains.dto.UserDto;

import java.util.Collection;
import java.util.List;


public interface UserService {
    UserDto findUser(String username, String password);

    List<String> createUser(UserDto userDto);

    UserDto findByUsername(String authorName);
}
