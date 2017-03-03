package softuni.services.issue;

import softuni.domains.dto.IssueDto;
import softuni.domains.dto.UserDto;
import softuni.domains.entity.Issue;
import softuni.domains.entity.User;
import softuni.domains.enumeration.Priority;
import softuni.domains.enumeration.Status;
import softuni.parsers.interfaces.ModelParser;
import softuni.repositories.issue.IssueRepository;
import softuni.repositories.user.UserRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Stateless
@Local(IssueService.class)
public class IssueServiceImpl implements IssueService{


    @Inject
    private ModelParser modelParser;

    @Inject
    private IssueRepository issueRepository;

    @Inject
    private UserRepository userRepository;


    @Override
    public void save(IssueDto issueDto) {
        Issue issue = new Issue();
        User author = this.userRepository.findByUsername(issueDto.getAuthor().getUsername());
        issue.setName(issueDto.getName());
        issue.setAuthor(author);
        issue.setPriority(Priority.valueOf(issueDto.getPriority().toUpperCase()));
        issue.setStatus(Status.valueOf(issueDto.getStatus().toUpperCase()));
        issue.setCreationDate(issueDto.getCreationDate());

        this.issueRepository.save(issue);
    }

    @Override
    public List<IssueDto> findAll() {
        List<IssueDto> issueDtoList = new ArrayList<>();
        List<Issue> issues = this.issueRepository.findAll();
        for (Issue issue : issues) {
            IssueDto issueDto = this.modelParser.convert(issue, IssueDto.class);
            issueDtoList.add(issueDto);
        }

        return issueDtoList;
    }

    @Override
    public List<IssueDto> findByAuthor(UserDto userDto) {
        return null;
    }

    @Override
    public List<IssueDto> findByStatus(String status) {
        return null;
    }

    @Override
    public List<IssueDto> findByPriority(String priority) {
        return null;
    }

    @Override
    public List<IssueDto> findByDate(Date date) {
        return null;
    }

    @Override
    public void delete(long id) {
       this.issueRepository.delete(id);
    }

    @Override
    public IssueDto findById(Long id) {
        Issue issue = this.issueRepository.findById(id);
        IssueDto issueDto = this.modelParser.convert(issue, IssueDto.class);

        return issueDto;
    }

    @Override
    public void update(IssueDto issueDto) {
        Issue issue  = this.issueRepository.findById(issueDto.getId());
        issue.setName(issueDto.getName());
        issue.setStatus(Status.valueOf(issueDto.getStatus()));
        issue.setPriority(Priority.valueOf(issueDto.getPriority()));
        this.issueRepository.update(issue);
    }

    @Override
    public List<IssueDto> findByWord(String search) {
        List<Issue> issues = this.issueRepository.findByWord(search);
        List<IssueDto> issueDtos = new ArrayList<>();
        for (Issue issue : issues) {
            issueDtos.add(this.modelParser.convert(issue, IssueDto.class));
        }

        return issueDtos;
    }

    @Override
    public List<IssueDto> searchIssues(String status, String search) {
        List<IssueDto> issueDtoList = new ArrayList<>();
        Set<Issue> issues = this.issueRepository.findByStatusAndSearch(status, search);
        for (Issue issue : issues) {
            IssueDto issueDto = this.modelParser.convert(issue, IssueDto.class);
            issueDtoList.add(issueDto);
        }

        return issueDtoList;
    }
}





