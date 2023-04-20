package com.techelevator.dao;

import com.techelevator.model.User;

import java.security.Principal;
import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(int userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role, String firstName, String lastName, String email);

    public void approveUser(int userId);

    public boolean userIsAdmin(Principal principal);

    public List<User> getAllRenters();

    public List<User> getAllEmployees();
}
