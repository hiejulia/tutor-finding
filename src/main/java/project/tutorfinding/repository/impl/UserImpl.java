package project.tutorfinding.repository.impl;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import project.tutorfinding.domain.User;
import project.tutorfinding.repository.UserDao;

import javax.persistence.TypedQuery;

@Repository("userImpl")
@Transactional(propagation = Propagation.MANDATORY)
public abstract class UserImpl implements UserDao {
//    @Autowired
//    private SessionFactory sessionFactory;

    @Autowired
    private UserDao userDao;

    @Override
    public User save(User user) {
        userDao.save(user);
        return user;
    }

    @SuppressWarnings("unchecked")
    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);

    }

    @SuppressWarnings("unchecked")
    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userDao.findByEmailAndPassword(email,password);
    }

    @Override
    public void update(User user) {
        userDao.update(user);


    }

    @Override
    public List<User> findAll() {
     return  userDao.findAll();


    }



}
