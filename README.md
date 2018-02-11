# tutor-finding
+ Tutor register to help student with course at university
+ Student search for help from study session with tutor based on the tutor speciality code and what course they can help


## Tech stack 
+ Spring boot
+ Spring security
+ Restful API
+ MySQL 
+ API 
    + Tutors
        + GET `api/tutors`
        + GET `api/tutors/{id}`
        + GET `api/tutors/{speciality_code}`
        + GET `api/tutors/{department}`
        + POST `api/users` , check for user role (1 == tutor)
        
    + Users 
        + GET `api/users`
        + POST `api/users`
    + Study session 
        + GET `api/studysessions/{userId}]`
        + GET `api/studysession/{tutorId}`
        + POST `api/studysession`