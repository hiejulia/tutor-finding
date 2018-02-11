package project.tutorfinding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import project.tutorfinding.domain.User;
import project.tutorfinding.exception.UnmatchingUserCredentialsException;
import project.tutorfinding.exception.UserNotFoundException;
import project.tutorfinding.repository.UserDao;
import project.tutorfinding.service.UserService;


@Service("userServiceImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    // autowired userrepository


    @Autowired
    @Qualifier("userDao")
    private UserDao userDAO;

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public User doesUserExist(String email)  {
        User user = userDAO.findByEmail(email);
        if(user == null){
//            throw new UserNotFoundException("User does not exist in the database");
        }
        return user;
    }

    @Override
    public User getByEmail(String email) throws UserNotFoundException {
        User user = this.doesUserExist(email);
        return user;
    }

    @Override
    public User isValidUser(String email, String password) throws UnmatchingUserCredentialsException {
        User user = userDAO.findByEmailAndPassword(email, password);
        if(user == null ) {
            throw new UnmatchingUserCredentialsException("User with given credentials is not found in the database.");
        }
        return user;
    }
}
