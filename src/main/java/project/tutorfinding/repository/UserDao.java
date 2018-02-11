package project.tutorfinding.repository;

import project.tutorfinding.domain.User;

import java.util.List;

public interface UserDao {
    // create new user
    User save(User user);

    // find user by email
    User findByEmail(String email);

    // find user by email and password
    User findByEmailAndPassword(String email, String password);

    // update user
    void update(User user);

    // delete user - for admin only
    void delete(User user);

}