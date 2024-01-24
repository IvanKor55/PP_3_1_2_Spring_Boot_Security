package ru.javamentor.springsecurity.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.javamentor.springsecurity.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private static final String LIST_USER = "SELECT u FROM User u";

    private static final String FIND_USER_BY_LOGIN = "SELECT u FROM User u WHERE login =:login";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        user = entityManager.find(User.class, user.getId());
        user.setRoles(null);
        entityManager.remove(user);
    }

    @Override
    public List<User> getListUsers() {
        return entityManager.createQuery(LIST_USER, User.class).getResultList();
    }

    @Override
    public User getUser (Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByLogin (String login) {
        try {
            Query<User> query = (Query<User>) entityManager.createQuery(FIND_USER_BY_LOGIN, User.class)
                    .setParameter("login", login);
            return query.setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            System.out.println("NoResultException" + e);
            return null;
        }
    }
}
