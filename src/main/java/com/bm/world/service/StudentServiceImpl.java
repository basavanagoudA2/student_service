package com.bm.world.service;

import com.bm.world.ApplicationConstants;
import com.bm.world.exception.StudentNotFoundException;
import com.bm.world.model.Student;
import com.bm.world.model.request.StudentRequest;
import com.bm.world.model.response.StudentResponse;
import com.bm.world.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class StudentServiceImpl implements  StudentService{
    @Autowired
    public StudentRepository studentRepository;
    @Override
    public String saveStudent(StudentRequest studentRequest) {
        Student student=new Student();
        if (!ObjectUtils.isEmpty(studentRequest)){
            BeanUtils.copyProperties(studentRequest,student);
            student= studentRepository.save(student);
        }
        return ApplicationConstants.STUDENT_SAVE_MESSAGE+student.getStudentId();
    }

    @Override
    public String deleteStudent(Long studentId) {
        String deleteMessage="";
        try {
            studentRepository.deleteById(studentId);
        }catch (DataAccessException e){
            throw new  StudentNotFoundException("student not found with this Id:"+studentId);
        }
        return deleteMessage;
    }

    @Override
    public String updateStudent(StudentRequest studentRequest) {
        return null;
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return null;
    }

    @Override
    public StudentResponse getStudentBasedOnStudentId(Long studentId) {
        return null;
    }
}
