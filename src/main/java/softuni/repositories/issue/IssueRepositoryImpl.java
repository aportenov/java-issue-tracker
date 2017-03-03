package softuni.repositories.issue;

import softuni.domains.entity.Issue;
import softuni.domains.entity.User;
import softuni.domains.enumeration.Priority;
import softuni.domains.enumeration.Status;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Stateless
@Local(IssueRepository.class)
public class IssueRepositoryImpl implements IssueRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Issue issue) {
        this.entityManager.persist(issue);
    }

    @Override
    public List<Issue> findAll() {
        Query query = this.entityManager.createQuery("SELECT i FROM Issue AS i");
        List<Issue> issues = query.getResultList();

        return Collections.unmodifiableList(issues);
    }

    @Override
    public List<Issue> findByAuthor(User user) {
        return null;
    }

    @Override
    public List<Issue> findByStatus(Status status) {
        return null;
    }

    @Override
    public List<Issue> findByPriority(Priority priority) {
        return null;
    }

    @Override
    public List<Issue> findByDate(Date date) {
        return null;
    }


    @Override
    public void delete(long id) {
        Query query = this.entityManager.createQuery("delete from Issue as i WHERE i.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Issue findById(Long id) {
        Query query = this.entityManager.createQuery("SELECT i FROM Issue AS i WHERE i.id = :id");
        query.setParameter("id", id);
        Issue issue = null;
        if (!query.getResultList().isEmpty()) {
            issue = (Issue) query.getSingleResult();
        }

        return issue;
    }

    @Override
    public void update(Issue issue) {
        Query query = this.entityManager.createQuery("UPDATE from Issue AS i SET i.name = :name, " +
                "i.status = :status, i.priority = :priority");
        query.setParameter("name", issue.getName());
        query.setParameter("status", issue.getStatus());
        query.setParameter("priority", issue.getPriority());

        query.executeUpdate();
    }

    @Override
    public List<Issue> findByWord(String search) {
        Query query = this.entityManager.createQuery("SELECT i FROM Issue AS i WHERE i.name LIKE :search");
        query.setParameter("search", search + "%");
        List<Issue> issues = null;
        if (! query.getResultList().isEmpty()) {
            issues = query.getResultList();
        }

        return Collections.unmodifiableList(issues);
    }

    @Override
    public Set<Issue> findByStatusAndSearch(String status, String search) {
        Query query = this.entityManager.createQuery("SELECT i FROM Issue AS i " +
                "WHERE i.status = :status " +
                "AND i.name LIKE :search");
        query.setParameter("search", "%" + search + "%");
        query.setParameter("status", Status.valueOf(status));
        return new HashSet<>(query.getResultList());
    }
}
