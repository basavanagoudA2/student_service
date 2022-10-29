package com.bm.world;

import com.bm.world.model.Student;
import com.bm.world.model.User;
import com.bm.world.model.request.StudentRequest;
import com.bm.world.model.response.StudentResponse;
import com.bm.world.payload.SignUpRequest;

/**
 * This utility class used for mapping the object either dto to entity or entity to dto
 */
public class ObjectMapper {
    /**
     * This method used for mapping the studentRequest dto to Student model
     * @param studentRequest
     * @param student
     */
    public static void dtoToModel(StudentRequest studentRequest,Student student){
        student.setEmailId(studentRequest.getEmailId());
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setMiddleName(studentRequest.getMiddleName());
        student.setMobileNumber(studentRequest.getMobileNumber());
    }

    /**
     * This method used to mapping Student model to StudentResponse
     * @param student
     * @param studentResponse
     */
    public static void modelToDto(Student student, StudentResponse studentResponse){
       studentResponse.setEmailId(student.getEmailId());
       studentResponse.setLastName(student.getLastName());
       studentResponse.setFirstName(student.getFirstName());
       studentResponse.setMiddleName(student.getMiddleName());
       studentResponse.setMobileNumber(student.getMobileNumber());
    }

    /**
     * This method user or mapping the signup pojo object to model component
     * @param user
     * @param signUpRequest
     */
    public static void dtoToModel(User user, SignUpRequest signUpRequest) {
        user.setUserName(signUpRequest.getUsername());
        user.setEmailId(signUpRequest.getEmail());
     //   user.setPassword(signUpRequest.getPassword());
    }

}
