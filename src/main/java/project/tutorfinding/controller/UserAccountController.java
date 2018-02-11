package project.tutorfinding.controller;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import project.tutorfinding.domain.Tutor;
import project.tutorfinding.domain.User;
import project.tutorfinding.exception.UserNotFoundException;
import project.tutorfinding.helpers.ExecutionStatus;
import project.tutorfinding.service.TutorService;
import project.tutorfinding.service.UserService;


@RestController
@RequestMapping("/accounts/*")
public class UserAccountController {
    final static Logger logger = LoggerFactory.getLogger(UserAccountController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TutorService tutorService;

    // get token
    @GetMapping(value="/token")
    public Map<String,String> getToken(HttpSession session){
        return Collections.singletonMap("token",session.getId());
    }

    // sign up
    @PostMapping(value="/signup")
    public ExecutionStatus processSignup(ModelMap modelMap,@RequestBody User user){
        User user1 = null;
        // check if user exist
        try{
            user = userService.doesUserExist(user.getEmail());
        }catch(UserNotFoundException e){

//            throw UserNotFoundException("User not found exception");
        }
        // if user exist
        if(user !=null){
            return new ExecutionStatus("USER_ACCOUNT_EXISTS", "User account with same email address exists. Please try again!");

        }
        user1 = new User();
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setFirstName(user.getFirstname());
        user1.setLastname(user.getLastname());
        user1.setContactNumber(user.getContactNumber());
        user1.setAlternateContactNumber(user.getAlternateContactNumber());
        user1.setCityCode(user.getCityCode());
        user1.setStateCode(user.getStateCode());
        user1.setCountryCode(user.getCountryCode());
        user1.setAge(user.getAge());
        user1.setGender(user.getGender());
        user1.setRole(user.getRole());
        User persistedUser = userService.save(user);
        //
        // Add entry in tutor table if user is a tutor]
        //
        tutorService.addTutor(user1);

        return new ExecutionStatus("USER_ACCOUNT_CREATED", "User account successfully created");

    }



    // update user profile
    @PostMapping(value = "/update",produces = "application/json")
    public ModelAndView updateProfile(ModelMap modelMap,@RequestParam("firstName") String firstName,
                                      @RequestParam("lastName") String lastName, @RequestParam("address") String address,
                                      @RequestParam("contact_number") String contactNumber ){
        return new ModelAndView("update",modelMap);
    }

    // update user
    @PostMapping(value="/user/update")
    public ExecutionStatus updateUser(ModelMap model, @RequestBody User reqUser) {
        User user = new User();
        user.setId(reqUser.getId());
        user.setFirstName(reqUser.getFirstname());
        user.setLastname(reqUser.getLastname());
        user.setContactNumber(reqUser.getContactNumber());
        user.setAlternateContactNumber(reqUser.getAlternateContactNumber());
        user.setCityCode(reqUser.getCityCode());
        user.setStateCode(reqUser.getStateCode());
        user.setCountryCode(reqUser.getCountryCode());
        user.setAge(reqUser.getAge());
        user.setGender(reqUser.getGender());
        userService.update(user);
        return new ExecutionStatus("USER_ACCOUNT_UPDATED", "User account successfully updated");
    }

    // forgot password process
    @PostMapping(value = "/forgotpassword/process",produces = "application/json")
    public ModelAndView processForgotPassword(ModelMap modelMap, @RequestParam("emailaddress") String email){
        User user = null;
        try {
            user = userService.doesUserExist(email);
        } catch (UserNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(user != null) {

        }
        modelMap.addAttribute("message","An email notification is sent to your email ");
        return new ModelAndView("forgotpassword",modelMap);
    }



}
