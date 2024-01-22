package ru.javamentor.springsecurity.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.javamentor.springsecurity.model.Role;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    private static final String LIST_ROLES = "SELECT r FROM Role r left join fetch r.user u where u.id = :id";
    private static final String FIND_ROLE_BY_NAME = "SELECT r FROM Role r WHERE Authority =:authority";

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void deleteRole(Role role) {
        entityManager.remove(entityManager.find(Role.class, role.getId()));
    }

    @Override
    public List<Role> getListRoles(Long id) {
        Query<Role> query = (Query<Role>)  entityManager.createQuery(LIST_ROLES, Role.class)
                .setParameter("id", id);
        return query.getResultList();
    }


    @Override
    public Role getRole(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role getRoleByName(String authority) {
        try {
            Query<Role> query = (Query<Role>) entityManager.createQuery(FIND_ROLE_BY_NAME, Role.class)
                    .setParameter("authority", authority);
            return query.setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            System.out.println("NoResultException" + e);
            return null;
        }
    }
}
