package project.tutorfinding.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import project.tutorfinding.domain.Tutor;
import project.tutorfinding.helpers.TutorInfo;
import project.tutorfinding.helpers.TutorList;
import project.tutorfinding.service.TutorService;


@RestController
public class TutorSearchController {
    final static Logger logger = LoggerFactory.getLogger(TutorSearchController.class);

    @Autowired
    private TutorService tutorService;

    // get tutor count

    @GetMapping(value="/tutors/count")
    public TutorInfo getTutorsCount(ModelMap model) {

        int count = tutorService.findCount();
        return new TutorInfo("All doctors count",null, count);
    }



    // get tutor by specialitycode
    @GetMapping(value = "tutors/{code}")
    public TutorInfo getBySpecialityCode(ModelMap model, @PathVariable("code") String code){
        List<Tutor> tutors = tutorService.findBySpeciality(code);
        if(tutors == null){
            return new TutorInfo("There is no tutors found", null, 0);

        }
        return new TutorInfo("Tutors found",tutors,tutors.size());
    }

    // get all tutor
    @GetMapping(value = "/tutors",produces = "application/json")
    public TutorInfo findAll(ModelMap modelMap){
        List<Tutor> tutors = tutorService.findAll();
        if(tutors == null){
            return new TutorInfo("No tutors found",null, 0);

        }
        return new TutorInfo("Tutors found",tutors, tutors.size());
    }



}
