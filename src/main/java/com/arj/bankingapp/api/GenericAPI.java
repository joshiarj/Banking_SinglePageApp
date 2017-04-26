package com.arj.bankingapp.api;

import com.arj.bankingapp.dao.UserDAO;
import com.arj.bankingapp.dao.impl.UserDAOImpl;

public class GenericAPI {
    protected UserDAO userDAO = new UserDAOImpl();
}
