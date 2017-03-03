package softuni.repositories.issue;

import softuni.domains.entity.Issue;
import softuni.domains.entity.User;
import softuni.domains.enumeration.Priority;
import softuni.domains.enumeration.Status;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IssueRepository {

    void save(Issue issue);

    List<Issue> findAll();

    List<Issue> findByAuthor(User user);

    List<Issue> findByStatus(Status status);

    List<Issue> findByPriority(Priority priority);

    List<Issue> findByDate(Date date);

    void delete(long id);

    Issue findById(Long id);

    void update(Issue issue);

    List<Issue> findByWord(String search);

    Set<Issue> findByStatusAndSearch(String status, String search);
}
