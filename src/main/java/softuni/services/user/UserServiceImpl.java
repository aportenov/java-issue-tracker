package softuni.services.user;

import softuni.domains.dto.UserDto;
import softuni.domains.entity.User;
import softuni.domains.enumeration.Role;
import softuni.parsers.interfaces.ModelParser;
import softuni.repositories.user.UserRepository;
import softuni.validator.Validator;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Local(UserService.class)
public class UserServiceImpl implements UserService {

    @Inject
    private ModelParser modelParser;

    @Inject
    private UserRepository userRepository;

    @Inject
    private Validator validator;

    @Override
    public UserDto findUser(String username, String password) {
        User user = this.userRepository.findByUserNameAndPassword(username, password);
        UserDto userDto = null;
        if (user != null) {
            userDto = this.modelParser.convert(user, UserDto.class);
        }

        return userDto;
    }

    @Override
    public List<String> createUser(UserDto userDto) {
        User user = this.modelParser.convert(userDto, User.class);
        long totalUsers = this.userRepository.getUsersCount();
        user.setRole(totalUsers == 0 ? Role.ADMIN : Role.USER);

        List<String> errors = new ArrayList<>();
        errors.addAll(this.validator.validate(user));
        if (errors.isEmpty()) {
            this.userRepository.save(user);
        }

        return errors;
    }

    @Override
    public UserDto findByUsername(String authorName) {
        User user = this.userRepository.findByUsername(authorName);
        UserDto userDto = this.modelParser.convert(user, UserDto.class);

        return userDto;
    }
}
