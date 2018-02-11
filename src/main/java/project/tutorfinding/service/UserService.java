package project.tutorfinding.service;

import project.tutorfinding.domain.User;
import project.tutorfinding.exception.UnmatchingUserCredentialsException;
import project.tutorfinding.exception.UserNotFoundException;


public interface UserService {

    User save(User user);

    void update(User user);

    User doesUserExist(String email) throws UserNotFoundException;

    User getByEmail(String email) throws UserNotFoundException;

    User isValidUser(String email, String password) throws UnmatchingUserCredentialsException;
}
