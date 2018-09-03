package com.ps.repos.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ps.ents.User;
import com.ps.repos.UserRepo;

@Repository("userJpaRepo")
public class JpaUserRepo implements UserRepo {

    private static String BY_ID = "from User u where u.id= :id";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> findAllByUserName(String username, boolean exactMatch) {
        if (exactMatch) {
            return entityManager.createQuery("select u from User u where username= ?", User.class)
                    .setParameter(0, username).getResultList();
        } else {
            return entityManager.createQuery("select u from User u where username like ?", User.class)
                    .setParameter(0, "%" + username + "%").getResultList();
        }
    }

    @Override
    public String findUsernameById(Long id) {
        return (String) entityManager.createQuery("select u.username from " + BY_ID).
                setParameter("id", id).getSingleResult();
    }

    @Override
    public long countUsers() {
        return (Long) entityManager.createQuery("select count(u) from User u").getSingleResult();
    }

    @Override
    public void updatePassword(Long userId, String newPass) {
        final User user = (User) entityManager.createQuery(BY_ID).setParameter("id", userId).getSingleResult();
        user.setPassword(newPass);

        entityManager.merge(user);
    }

    @Override
    public void updateUsername(Long userId, String username) {
        final User user = (User) entityManager.createQuery(BY_ID).setParameter("id", userId).getSingleResult();
        user.setUsername(username);

        entityManager.merge(user);
    }

    @Override
    public void deleteById(Long userId) {
        final User user = (User) entityManager.createQuery(BY_ID).setParameter("id", userId).getSingleResult();

        entityManager.remove(user);
    }

    @Override
    public void save(Set<User> users) {
        for (User user : users) {
            entityManager.persist(user);
        }
    }

    @Override
    public void deleteAll() {
        final List<User> users = findAll();

        for (User user : users) {
            entityManager.remove(user);
        }
    }
}
