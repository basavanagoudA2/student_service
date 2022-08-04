package com.bm.world.service;

import com.bm.world.model.request.StudentRequest;
import com.bm.world.model.response.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is used for perform the operation student like sava and delete and update and get
 */
public interface StudentService {

    public String saveStudent(StudentRequest studentRequest);

    public String deleteStudent(Long studentId);

    public String updateStudent(StudentRequest studentRequest);

    public List<StudentResponse> getAllStudents();

    public StudentResponse getStudentBasedOnStudentId(Long studentId);
}
