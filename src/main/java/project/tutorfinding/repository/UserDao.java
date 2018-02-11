package project.tutorfinding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import project.tutorfinding.domain.User;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    // create new user
    User save(User user);

    // find user by email
    User findByEmail(String email);

    // find user by email and password
    User findByEmailAndPassword(String email, String password);

    // update user
    void update(User user);



}