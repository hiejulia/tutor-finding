package project.tutorfinding.repository;

import project.tutorfinding.domain.Tutor;

import java.util.List;

public interface TutorDao {

    List<Tutor> findAll();

    List<Tutor> findBySpecialityCode(String code);

    int findAllCount();

    List<Tutor> findByDepartment(String department);

    List<Tutor> findByClass(String classname);

    Tutor findByUserId(int userId);

    Tutor save(Tutor tutor);




}
