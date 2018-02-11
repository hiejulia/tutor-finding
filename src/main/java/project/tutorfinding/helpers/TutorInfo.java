package project.tutorfinding.helpers;

import project.tutorfinding.domain.Tutor;

import java.util.List;

public class TutorInfo {

    private String message;
    private List<Tutor> tutors;

    private int count;

//    public TutorInfo(){
//    }

    public TutorInfo(String message, List<Tutor> tutors, int count) {
        this.message = message;
        this.tutors = tutors;
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Tutor> getTutors() {
        return tutors;
    }

    public void setTutors(List<Tutor> tutors) {
        this.tutors = tutors;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
