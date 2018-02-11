package project.tutorfinding.repository.impl;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.tutorfinding.domain.StudySession;
import project.tutorfinding.repository.StudySessionDao;

@Repository("studySessionImpl")
@Transactional
public class StudySessionImpl implements StudySessionDao {
    final static Logger logger = LoggerFactory.getLogger(StudySessionImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public StudySessionImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public StudySession save(StudySession ss) {
        Session session = this.sessionFactory.openSession();
        session.save(ss);
        session.close();
        return ss;
    }

    @Override
    public List<StudySession> findByUserId(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        TypedQuery<StudySession> query = (TypedQuery<StudySession>) session.getNamedQuery("findByUserId");
        query.setParameter("id", id);
        List<StudySession> rxList = query.getResultList();
        return rxList;
    }

    @Override
    public List<StudySession> findByTutorId(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        TypedQuery<StudySession> query = (TypedQuery<StudySession>)session.getNamedQuery("findByTutorId");
        query.setParameter("id", id);
        List<StudySession> rxList = query.getResultList();
        return rxList;
    }
}
