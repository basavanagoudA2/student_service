package com.bm.world.service;

import java.util.ArrayList;

import java.util.List;

import com.bm.world.ObjectMapper;
import com.bm.world.exception.StudentsDetailsNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.bm.world.ApplicationConstants;
import com.bm.world.exception.StudentNotFoundException;
import com.bm.world.model.Student;
import com.bm.world.model.request.StudentRequest;
import com.bm.world.model.response.StudentResponse;
import com.bm.world.repository.StudentRepository;

/**
 * @author basavanagoud A
 */
@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOG = LogManager.getLogger(StudentServiceImpl.class);
    List<StudentResponse> studentsList = new ArrayList<>();
    private final StudentRepository studentRepository;
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * This method used for save the student details in the database
     */
    @Override
    public String saveStudent(StudentRequest studentRequest) {
        LOG.info("start the saving the student details:[{}]", studentRequest);
        Student student = new Student();
        if (!ObjectUtils.isEmpty(studentRequest)) {
            ObjectMapper.dtoToModel(studentRequest,student);
            student = studentRepository.save(student);
            LOG.debug("student details inserted completed:[{}]", student);
        }
        LOG.info("completed the insert student details");
        return ApplicationConstants.STUDENT_SAVE_MESSAGE + student.getStudentId();
    }

    /**
     * This method used for delete the student details based on his student Id
     */
    @Override
    public String deleteStudent(Long studentId) {
        LOG.info("start the deleting the student records by studentId:[{}]", studentId);
        String deleteMessage = "";
        try {
            studentRepository.deleteById(studentId);
            deleteMessage = "Student deleted :" + studentId;
            LOG.info("student record is deleted for this studentId:[{}]", studentId);
        } catch (DataAccessException e) {
            throw new StudentNotFoundException("student not found with this Id:" + studentId);
        }
        LOG.info("completed the delete student records");
        return deleteMessage;
    }

    /**
     * This method used for update the student information
     */
    @Override
    public String updateStudent(StudentRequest studentRequest) {
        String updateMessage;
            LOG.info("start the updating the record:[{}]", studentRequest);
                Student student = studentRepository.findById(studentRequest.getStudentId()).
                    orElseThrow(()->new StudentNotFoundException("student not found for update with StudentId: " + studentRequest.getStudentId()));
                ObjectMapper.dtoToModel(studentRequest,student);
                studentRepository.save(student);
                LOG.debug("update is completed for this StudentId:[{}]", studentRequest.getStudentId());
        updateMessage = "student updated with following ID: " + studentRequest.getStudentId();
        LOG.info("completed the student record update");
        return updateMessage;
    }

    /**
     * This method used for the get the all students details
     */
    @Override
    public List<StudentResponse> getAllStudents() {
        LOG.info("started the get all student details from Database");
        if (ObjectUtils.isEmpty(studentsList)) {
            List<Student> studentList1 = studentRepository.findAll();
            if (!ObjectUtils.isEmpty(studentList1)) {
                studentList1.stream().forEach(student -> {
                    StudentResponse studentResponse = new StudentResponse();
                    ObjectMapper.modelToDto(student, studentResponse);
                    studentsList.add(studentResponse);
                });
                LOG.debug("fetching all the student details:[{}]", studentsList);
                studentList1.clear();
            } else {
                throw new StudentsDetailsNotFoundException("student details not found");
            }
        }
        LOG.info("Fetching the all the student records completed");
        return studentsList;
    }

    /**
     * This method used for get student details based on studentId
     */
    @Override
    public StudentResponse getStudentBasedOnStudentId(Long studentId) {
        LOG.info("started the get  student details from Database based on StudentId:[{}]", studentId);
       StudentResponse studentResponse=new StudentResponse();
        if (!ObjectUtils.isEmpty(studentId)) {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(()->new StudentNotFoundException("Student not found with this StudentID: " + studentId));
                LOG.debug("Get the student details based on studentId:[{}]", studentResponse);
                LOG.info("completed the fetching record based on studentId");
              ObjectMapper.modelToDto(student,studentResponse);
        }
        return studentResponse;
    }
}
