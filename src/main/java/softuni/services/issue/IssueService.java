package softuni.services.issue;

import softuni.domains.dto.IssueDto;
import softuni.domains.dto.UserDto;

import java.util.Date;
import java.util.List;

public interface IssueService {

    void save(IssueDto issueDto);

    List<IssueDto> findAll();

    List<IssueDto> findByAuthor(UserDto userDto);

    List<IssueDto> findByStatus(String status);

    List<IssueDto> findByPriority(String priority);

    List<IssueDto> findByDate(Date date);

    void delete(long issueDto);

    IssueDto findById(Long id);

    void update(IssueDto issueDto);

    List<IssueDto> findByWord(String search);

    List<IssueDto> searchIssues(String s, String search);
}
