package project.tutorfinding.repository.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.tutorfinding.domain.Tutor;
import project.tutorfinding.repository.TutorDao;

@Repository
@Transactional
public class TutorImpl implements TutorDao {
    private SessionFactory sessionFactory;

    @Autowired
    public DoctorDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Tutor> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        TypedQuery<Tutor> query = session.getNamedQuery("Tutor.findAll");
        List<Tutor> tutors = query.getResultList();
        return tutors;
    }

    @Override
    public List<Tutor> findBySpecialityCode(String code) {
        Session session = this.sessionFactory.getCurrentSession();
        TypedQuery<Tutor> query = session.getNamedQuery("Tutor.findBySpeciality");
        query.setParameter("specialityCode", code);
        List<Tutor> tutors = query.getResultList();
        return tutors;
    }

    @Override
    public int findAllCount() {
        Session session = this.sessionFactory.getCurrentSession();
        TypedQuery<Number> query = session.getNamedQuery("Tutor.findAllCount");
        int count = ((Number)query.getSingleResult()).intValue();
        return count;
    }

    @Override
    public List<Tutor> findByDepartment(String department) {
        return null;
    }

    @Override
    public List<Tutor> findByClass(String classname) {
        return null;
    }

    @Override
    public Tutor findByUserId(int userId) {
        Session session = this.sessionFactory.getCurrentSession();
        TypedQuery<Tutor> query = session.getNamedQuery("Tutor.findById");
        query.setParameter("id", userId);
        List<Tutor> doctors = query.getResultList();
        return doctors.get(0);

    }

    @Override
    public Tutor save(Tutor tutor) {
        Session session = this.sessionFactory.openSession();
        session.save(tutor);
        session.close();
        return tutor;
    }
}
