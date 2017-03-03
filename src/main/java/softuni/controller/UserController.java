package softuni.controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.RequestParam;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;
import softuni.config.Location;
import softuni.domains.dto.UserDto;
import softuni.services.user.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Controller
public class UserController {

    @Inject
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage(Model model) {

        model.addAttribute("title", "Log In");
        model.addAttribute("view", "user/login.jsp");

        return Location.REDIRECT;
    }

    @PostMapping("/login")
    public String logIn(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {

        UserDto userDto = this.userService.findUser(username, password);
        List<String> errors = new ArrayList<>();
        if (userDto != null) {
            model.addAttribute("title", "Issue Tracker");
            model.addAttribute("view", "home.jsp");
            session.setAttribute("user", userDto);


        } else {
            errors.add("ERROR: usernme and password doesn't match");
            model.addAttribute("errors", errors);
            model.addAttribute("title", "Log In");
            model.addAttribute("view", "user/login.jsp");
        }


        return Location.REDIRECT;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {

        model.addAttribute("title", "Register page");
        model.addAttribute("view", "user/create-user.jsp");

        return Location.REDIRECT;
    }

    @PostMapping("/register")
    public String register(@RequestParam("name") String username,
                           @RequestParam("fullName") String fullName,
                           @RequestParam("password") String password,
                           @RequestParam("repeatPassword") String confirmedPassword,
                           Model model) {

        UserDto userDto = null;
        List<String> errors = new ArrayList<>();
        userDto = this.userService.findUser(username, password);
        if (userDto != null) {
            errors.add("ERROR: This username is already taken");
        } else {
            if (!password.equals(confirmedPassword)) {
                errors.add("ERROR: The password doesn't match");
            }

            userDto = new UserDto(username, fullName, password, new String());
            errors.addAll(this.userService.createUser(userDto));
        }

        if (errors.isEmpty()) {
            model.addAttribute("user", userDto);
            model.addAttribute("view", "user/login.jsp");
            model.addAttribute("title", "Login page");
        } else {
            model.addAttribute("errors", errors);
            model.addAttribute("title", "Register page");
            model.addAttribute("view", "user/create-user.jsp");
        }


        return Location.REDIRECT;
    }

    @GetMapping("/logout")
    public String logOut(Model model, HttpSession session) {
        session.invalidate();
        model.addAttribute("title", "Home page");
        model.addAttribute("view", "home.jsp");

        return Location.REDIRECT;
    }
}
