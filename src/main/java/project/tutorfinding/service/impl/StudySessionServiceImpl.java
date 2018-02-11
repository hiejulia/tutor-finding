package project.tutorfinding.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.tutorfinding.domain.StudySession;
import project.tutorfinding.repository.StudySessionDao;
import project.tutorfinding.service.StudySessionService;


@Service("studySessionServiceImpl")
@Transactional
public class StudySessionServiceImpl implements StudySessionService{
    //logging
    final static Logger logger = LoggerFactory.getLogger(StudySessionServiceImpl.class);

    //autowired studysessionDAO
    @Autowired
    private StudySessionDao studySessionDao;

    @Override
    public void save(StudySession ss) {
        studySessionDao.save(ss);
    }

    @Override
    public List<StudySession> findByTutorId(int tutorId) {
        return studySessionDao.findByTutorId(tutorId);
    }

    @Override
    public List<StudySession> findByStudentId(int userId) {
        return studySessionDao.findByUserId(userId);
    }
}
