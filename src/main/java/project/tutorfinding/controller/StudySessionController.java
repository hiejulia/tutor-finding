package project.tutorfinding.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import project.tutorfinding.domain.StudySession;
import project.tutorfinding.domain.Tutor;
import project.tutorfinding.domain.User;
import project.tutorfinding.dto.StudySessionDto;
import project.tutorfinding.exception.UserNotFoundException;
import project.tutorfinding.service.StudySessionService;
import project.tutorfinding.service.TutorService;
import project.tutorfinding.service.UserService;


@RestController
public class StudySessionController {
    final static Logger logger = LoggerFactory.getLogger(StudySessionController.class);

    @Autowired
    private StudySessionService studySessionService;

    @Autowired
    private UserService userService;

    @Autowired
    private TutorService tutorService;

    // get list all study session saved on database
    // get all study sessions by user id
    @GetMapping(value = "/studysessions",produces="application/json")
    public List<StudySessionDto> getStudySessions(){
        List<StudySession> studySessions = null;
        User user = this.getUser();
        if(user.getRole() == 1) {
            studySessions = studySessionService.findByTutorId(user.getId());
        } else {
            studySessions = studySessionService.findByStudentId(user.getId());
        }

        List<StudySessionDto> studySessionDtos = new ArrayList<StudySessionDto>();

        for(StudySession studySession: studySessions){
            StudySessionDto studySessionDto = new StudySessionDto();
            studySessionDto.setCourse(studySession.getCourse());
            studySessionDto.setKnowledge(studySession.getStudyDescription());
            studySessionDto.setStudentName(studySession.getUser().getFirstname());
            // add to studysessions
            studySessionDtos.add(studySessionDto);
        }
        return studySessionDtos;
    }


    // create new study session

    @PostMapping(value = "/studysession/new",produces = "application/json")
    public StudySession createNewStudySession(ModelMap modelMap, @RequestBody StudySessionDto studySessionDto){



        StudySession studySession  = new StudySession();
        // set study session
        studySession.setCourse(studySessionDto.getCourse());
        studySession.setStudyDescription(studySessionDto.getKnowledge());

        User student = null;

        try {
            student = userService.getByEmail(studySessionDto.getStudentId());
        }catch(UserNotFoundException e){

        }
        studySession.setUser(student);

        Tutor tutor = tutorService.findByUserEmailAddress(getUserEmailAndAddress());
        studySession.setTutor(tutor);

        studySessionService.save(studySession);
        return studySession;

    }



    // get user
    private User getUser() {
        String userEmailAddress = this.getUserEmailAndAddress();
        User user = null;
        try {
            user = userService.doesUserExist(userEmailAddress);
        } catch (Exception e ) {
        }
        return user;
    }
    // get user email and address
    private String getUserEmailAndAddress(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }


}
