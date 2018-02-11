package project.tutorfinding.repository;
import project.tutorfinding.domain.StudySession;

import java.util.List;

public interface StudySessionDao {

    // create new study session
    StudySession save(StudySession ss);

    // find by user id
    List<StudySession> findByUserId(int id);

    // find by tutor id
    List<StudySession> findByTutorId(int id);

}
