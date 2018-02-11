package project.tutorfinding.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.tutorfinding.domain.Tutor;
import project.tutorfinding.domain.User;
import project.tutorfinding.exception.UserNotFoundException;
import project.tutorfinding.repository.TutorDao;
import project.tutorfinding.service.TutorService;
import project.tutorfinding.service.UserService;

@Service("tutorServiceImpl")
@Transactional
public abstract class TutorServiceImpl implements TutorService {
    @Autowired
    private TutorDao tutorDAO;

    @Autowired
    private UserService userService;

    @Override
    public Tutor save(Tutor tutor) {
        tutorDAO.save(tutor);
        return tutor;
    }

    @Override
    public List<Tutor> findBySpeciality(String specialityCode) {
        return tutorDAO.findBySpecialityCode(specialityCode);
    }

    @Override
    public List<Tutor> findByFloor(String floor) {
        return null;
    }

    @Override
    public List<Tutor> findByDepartment(String departmentName) {
        return tutorDAO.findByDepartment(departmentName);
    }

    @Override
    public List<Tutor> findAll() {
        return tutorDAO.findAll();
    }

    @Override
    public Tutor findByUserEmailAddress(String email) {
        User user = null;
        try {
            user = userService.getByEmail(email);
        } catch (UserNotFoundException e) {
            return null;
        }
        return this.findByUserId(user.getId());
    }

    @Override
    public int findCount() {
        return tutorDAO.findAllCount();
    }

    @Override
    public Tutor findByUserId(int userId) {
        return tutorDAO.findByUserId(userId);
    }

    @Override
    public void addTutor(User user) {
        // check if user role ==1
        if(user.getRole() == 1){
            Tutor t = new Tutor();
            t.setUser(user);
            t.setSpecialityCode("IT");
            tutorDAO.save(t);
        }

    }
}
