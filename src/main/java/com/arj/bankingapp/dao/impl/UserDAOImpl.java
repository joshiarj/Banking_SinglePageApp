package com.arj.bankingapp.dao.impl;

import com.arj.bankingapp.dao.UserDAO;
import com.arj.bankingapp.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserDAOImpl implements UserDAO {

    EntityManagerFactory emf;
    EntityManager em;
    EntityTransaction trans;

    public UserDAOImpl() {
        emf = Persistence.createEntityManagerFactory("BankJPU");
        em = emf.createEntityManager();
        trans = em.getTransaction();
    }

    @Override
    public User login(String userName, String password) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.userName=:username AND u.password=:password");
        query.setParameter("username", userName);
        query.setParameter("password", password);
        if (query.getResultList().size() > 0) {
            return (User) query.getSingleResult();
        }
        return null;
    }

    @Override
    public String generateAccessToken(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public void insert(User u) {
        trans.begin();
        em.persist(u);
        trans.commit();
    }

    @Override
    public void update(User u) {
        trans.begin();
        em.merge(u);
        trans.commit();
    }

    @Override
    public boolean delete(int id) {
        User u = getById(id);
        if(u==null){
            return false;
        }
        trans.begin();
        em.remove(u);
        trans.commit();
        return true;
    }

    @Override
    public User getById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User getByAccessToken(String accessToken) {
        for(User u:getAll()){
            if(u.getAccessToken().equalsIgnoreCase(accessToken)){
                return u;
            }
        }
        return null;
    }

}
