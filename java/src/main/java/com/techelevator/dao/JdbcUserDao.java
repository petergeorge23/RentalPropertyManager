package com.techelevator.dao;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        int userId;
        try {
            userId = jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User " + username + " was not found.");
        }

        return userId;
    }

	@Override
	public User getUserById(int userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if (results.next()) {
			return mapRowToUser(results);
		} else {
			return null;
		}
	}

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        for (User user : this.findAll()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public void approveUser(int userId) {
        String sql = "UPDATE users " +
                "SET approved = true " +
                "WHERE user_id = ?;";
        int results = jdbcTemplate.update(sql, userId);
    }

    @Override
    public boolean userIsAdmin(Principal principal) {
        return findByUsername(principal.getName()).getAuthorities().toString().contains("ROLE_ADMIN");
    }

    @Override
    public boolean create(String username, String password, String role, String firstName, String lastName, String email) {
        String insertUserSql = "insert into users (username,password_hash,role,first_name, last_name, email, approved) values (?,?,?,?,?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = role.toUpperCase().startsWith("ROLE_") ? role.toUpperCase() : "ROLE_" + role.toUpperCase();
        boolean approved = false;

        if (ssRole.equalsIgnoreCase("ROLE_renter") || ssRole.equalsIgnoreCase("ROLE_ADMIN")) {
            approved = true;
        }

        return jdbcTemplate.update(insertUserSql, username, password_hash, ssRole, firstName, lastName, email, approved) == 1;

    }

    @Override
    public List<User> getAllRenters() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM users " +
                "WHERE role = 'ROLE_RENTER';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            userList.add(mapRowToUser(results));
        }

        return userList;
    }

    @Override
    public List<User> getAllEmployees() {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM users " +
                "WHERE role = 'ROLE_EMPLOYEE';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            userList.add(mapRowToUser(results));
        }

        return userList;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setActivated(true);
        return user;
    }
}
