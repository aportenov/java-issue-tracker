package softuni.repositories.user;

import softuni.domains.dto.UserDto;
import softuni.domains.entity.User;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Local(UserRepository.class)
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByUserNameAndPassword(String username, String password) {
        User user = null;
        Query query = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.username = :username AND u.password =:password");
        query.setParameter("username", username);
        query.setParameter("password", password);

        if (! query.getResultList().isEmpty()){
            user = (User) query.getSingleResult();
        }

        return user;
    }

    @Override
    public User findByUsername(String authorUsername) {
        User user = null;
        Query query = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.username = :authorUsername");
        query.setParameter("authorUsername", authorUsername);
        if (! query.getResultList().isEmpty()){
            user = (User) query.getSingleResult();
        }

        return user;
    }

    @Override
    public long getUsersCount() {
        Query query = this.entityManager.createQuery("SELECT count(u) FROM User AS u");
        long total = (long) query.getSingleResult();

        return total;
    }

    @Override
    public void save(User user) {
        this.entityManager.persist(user);
    }


}
