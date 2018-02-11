package project.tutorfinding.repository;
import java.util.List;

public interface Tutor {

    List<Tutor> findAll();

    List<Tutor> findBySpecialityCode();

    int findAllCount();

    List<Tutor> findByDepartment(String department);

    List<Tutor> findByClass(String classname);

    Tutor findByUserId(int userId);

    Tutor save(Tutor tutor);




}
