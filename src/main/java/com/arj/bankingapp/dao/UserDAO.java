package com.arj.bankingapp.dao;

import com.arj.bankingapp.entity.User;
import java.util.List;

public interface UserDAO {
    User login(String userName, String password);
    String generateAccessToken(int id);
    List<User> getAll();
    void insert(User u);
    void update(User u);
    boolean delete(int id);
    User getById(int id);
    User getByAccessToken(String accessToken);
}
