package project.tutorfinding.service;

import org.springframework.data.jpa.repository.JpaRepository;
import project.tutorfinding.domain.Tutor;
import project.tutorfinding.domain.User;

import java.util.Iterator;
import java.util.List;



public interface TutorService extends JpaRepository<Tutor,Integer>{
    Tutor save(Tutor tutor);

    List<Tutor> findBySpeciality(String specialityCode);

    List<Tutor> findByFloor(String floor);

    List<Tutor> findByDepartment(String departmentName);

    List<Tutor> findAll();

    Tutor findByUserEmailAddress(String email);

    int findCount();

    Tutor findByUserId(int userId);

    void addTutor(User user);
}
