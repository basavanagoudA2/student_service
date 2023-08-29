package com.bm.world.service;

import java.util.List;

import com.bm.world.model.request.StudentRequest;
import com.bm.world.model.response.SaveResponse;
import com.bm.world.model.response.StudentResponse;

/**
 * This class is used for perform the operation student like sava and delete and update and get
 */
public interface StudentService {

    public SaveResponse saveStudent(StudentRequest studentRequest);

    public String deleteStudent(Long studentId);

    public String updateStudent(StudentRequest studentRequest);

    public List<StudentResponse> getAllStudents();

    public StudentResponse getStudentBasedOnStudentId(Long studentId);
}
