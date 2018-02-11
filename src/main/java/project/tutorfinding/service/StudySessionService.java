package project.tutorfinding.service;

import project.tutorfinding.domain.StudySession;

import java.util.List;



public interface StudySessionService {

    void save(StudySession ss);

    List<StudySession> findByTutorId(int tutorId);

    List<StudySession> findByStudentId(int userId);
}