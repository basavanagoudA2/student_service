package com.bm.world;

import com.bm.world.model.Student;
import com.bm.world.model.request.StudentRequest;
import com.bm.world.model.response.StudentResponse;

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
}
