package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
       entityManager.merge(user);

    }

    @Override
    public void delete(long id) {
        entityManager.remove(entityManager.find(User.class, id));

    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUser(long id){
        return entityManager.find(User.class, id);
    }
}
