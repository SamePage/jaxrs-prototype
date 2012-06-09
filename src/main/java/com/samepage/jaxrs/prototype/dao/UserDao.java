package com.samepage.jaxrs.prototype.dao;

import com.samepage.jaxrs.prototype.model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class UserDao {
    @Inject EntityManager em;

    public List<User> findAll() {
       return em.createQuery("select u from User u", User.class).getResultList();
    }

    public User findById(Long id) {
        return em.createQuery("select u from User u where u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public User findByEmail(String email) {
        return em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();

    }

    public User add(User user) {
        em.persist(user);
        return user;
    }

    public User update(User user) {
        em.merge(user);

        return user;
    }

    public User deleteById(Long id) {
        User user = em.find(User.class, id);
        em.remove(user);
        return user;
    }
}