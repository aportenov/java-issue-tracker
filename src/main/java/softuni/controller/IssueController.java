package softuni.controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.PathVariable;
import com.mvcFramework.annotations.parameters.RequestParam;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;
import softuni.config.Location;
import softuni.domains.dto.IssueDto;
import softuni.domains.dto.UserDto;
import softuni.services.issue.IssueService;
import softuni.services.user.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Stateless
@Controller
public class IssueController {

    @Inject
    private IssueService issueService;

    @Inject
    private UserService userService;

    @GetMapping("/issues")
    public String details(Model model,
                          HttpSession session,
                          @RequestParam("search") String search,
                          @RequestParam("status") String status) {

        List<IssueDto> issueDtos = null;
        if (session.getAttribute("user") == null) {
            return Location.REDIRECT_HOME;
        }

        if(status == null || status.equals("All")){
            issueDtos = this.issueService.findAll();
        } else {
            issueDtos = this.issueService.searchIssues(status.toUpperCase(), search);
        }

        model.addAttribute("issues", issueDtos);
        model.addAttribute("title", "Show Issues");
        model.addAttribute("view", "issues/issues.jsp");

        return Location.REDIRECT;
    }

    @GetMapping("/issues/add")
    public String addIssuePage(Model model) {
        model.addAttribute("title", "Add Issue");
        model.addAttribute("view", "issues/add-issue.jsp");

        return Location.REDIRECT;
    }

    @PostMapping("/issues/add")
    public String addIssue(Model model,
                           @RequestParam("name") String name,
                           @RequestParam("status") String status,
                           @RequestParam("priority") String priority,
                           HttpSession session) {

        UserDto userDto = (UserDto) session.getAttribute("user");
        IssueDto issueDto = new IssueDto(name, priority, status, userDto);
        issueDto.setCreationDate(new Date());
        this.issueService.save(issueDto);

        model.addAttribute("title", "Add Issue");
        model.addAttribute("view", "issues/add-issue.jsp");

        return Location.REDIRECT;
    }

    @GetMapping("/issues/edit/{id}")
    public String editIssuePage(@PathVariable("id") long id,
                                Model model, HttpSession session) {

        IssueDto issueDto = this.issueService.findById(id);
        model.addAttribute("name", issueDto.getName());
        model.addAttribute("status", issueDto.getStatus());
        model.addAttribute("priority", issueDto.getPriority());
        model.addAttribute("title", "Edit Issue");
        model.addAttribute("view", "issues/edit-issue.jsp");

        return Location.REDIRECT;
    }

    @PostMapping("/issues/edit/{id}")
    public String editIssue(Model model,
                          @PathVariable("id") long id,
                          @RequestParam("name") String name,
                          @RequestParam("status") String status,
                          @RequestParam("priority") String priority,
                          HttpSession session) {

        IssueDto issueDto = new IssueDto();
        issueDto.setId(id);
        issueDto.setName(name);
        issueDto.setPriority(priority);
        issueDto.setStatus(status);
        this.issueService.update(issueDto);

        model.addAttribute("title", "Issue page");
        model.addAttribute("view", "issues/issues.jsp");

        return Location.REDIRECT;
    }

    @PostMapping("/issues/delete/{id}")
    public String deleteIssue(Model model, HttpSession session, @PathVariable("id") long id
    ) {
        this.issueService.delete(id);

        model.addAttribute("title", "Issue page");
        model.addAttribute("view", "issues/issues.jsp");

        return Location.REDIRECT;
    }

}
