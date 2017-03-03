package softuni.controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.models.Model;
import softuni.config.Location;

import javax.ejb.Stateless;

@Stateless
@Controller
public class HomeController {


    @GetMapping("/")
    public String details(Model model){

        model.addAttribute("title", "Issue Tracker");
        model.addAttribute("view", "home.jsp");

        return Location.REDIRECT;
    }

}
