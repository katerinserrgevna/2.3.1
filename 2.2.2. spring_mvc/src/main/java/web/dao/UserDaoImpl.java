package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext()
    private EntityManager entityManager;

    @Override
    public User getUserById(int id) {
        return entityManager.createQuery(
                "SELECT u FROM User u WHERE u.id = :id",
                User.class)
                .setParameter("id", id)
                .getResultList().stream().findAny().orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Override
    public void delete(int id) {
        entityManager.createQuery(
                "DELETE FROM User WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
