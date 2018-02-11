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
public class UserImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User save(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
        return user;
    }

    @SuppressWarnings("unchecked")
    @Override
    public User findByEmail(String email) {
        Session session = this.sessionFactory.getCurrentSession();
        TypedQuery<User> query = (TypedQuery<User>)session.getNamedQuery("findByEmail");
        query.setParameter("email",email);
        return query.getResultList();


    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findByEmailAndPassword(String email, String password) {
        Session session = this.sessionFactory.getCurrentSession();
        TypedQuery<User> query =(TypedQuery<User>) session.getNamedQuery("findByEmailAndPassword");
        query.setParameter("email", email);
        query.setParameter("password", password);
        return query.getResultList();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
