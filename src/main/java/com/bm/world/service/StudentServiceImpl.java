package com.bm.world.service;

import java.util.ArrayList;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.bm.world.ApplicationConstants;
import com.bm.world.exception.StudentNotFoundException;
import com.bm.world.exception.StudentServiceException;
import com.bm.world.model.Student;
import com.bm.world.model.request.StudentRequest;
import com.bm.world.model.response.StudentResponse;
import com.bm.world.repository.StudentRepository;
/**
 * 
 * @author basavanagoud A
 *
 */
@Service
public class StudentServiceImpl implements  StudentService{
	
	private static final Logger LOG=LogManager.getLogger(StudentServiceImpl.class);
	List<StudentResponse> studentsList=new ArrayList<>();
    @Autowired
    public StudentRepository studentRepository;
    /**
     * This method used for save the student details in the database
     */
    @Override
    public String saveStudent(StudentRequest studentRequest) {
    	LOG.info("start the saving the student details:[{}]",studentRequest);
        Student student=new Student();
        if (!ObjectUtils.isEmpty(studentRequest)){
            BeanUtils.copyProperties(studentRequest,student);
            student= studentRepository.save(student);
            LOG.debug("student details inserted completed:[{}]",student);
        }
        LOG.info("completed the insert student details");
        return ApplicationConstants.STUDENT_SAVE_MESSAGE+student.getStudentId();
    }
    /**
     * This method used for delete the student details based on his student Id 
     */
    @Override
    public String deleteStudent(Long studentId) {
    	LOG.info("start the deleting the student records by studentId:[{}]",studentId);
        String deleteMessage="";
        try {
            studentRepository.deleteById(studentId);
            deleteMessage="Student deleted :"+studentId;
            LOG.info("student record is deleted for this studentId:[{}]",studentId);
        }catch (DataAccessException e){
            throw new  StudentNotFoundException("student not found with this Id:"+studentId);
        }
    	LOG.info("completed the delete student records");
        return deleteMessage;
    }
    /**
     * This method used for update the student information
     */
    @Override
    public String updateStudent(StudentRequest studentRequest) {
    	String updateMessgae=" ";
    
	        try {
				LOG.info("start the updating the record:[{}]",studentRequest);
	        	Optional<Student> findById = studentRepository.findById(studentRequest.getStudentId());
	    		if (findById.isPresent()) {
	    			Student student = findById.get();	
	    			student.setEmailId(studentRequest.getEmailId());
					student.setFirstName(studentRequest.getFirstName());
					student.setMiddleName(studentRequest.getMiddleName());			
					student.setLastName(studentRequest.getLastName());
					student.setMobileNumber(studentRequest.getMobileNumber());
					studentRepository.save(student);
					LOG.debug("update is completed for this StudentId:[{}]",studentRequest.getStudentId());
					updateMessgae="student updated with following ID: "+studentRequest.getStudentId();
				}
				
			}catch (NoSuchElementException e) {
				throw new StudentNotFoundException("Student not found for updatation"+studentRequest.getStudentId());
				
			} 
	        catch (DataAccessException e) {
	        	throw new StudentServiceException(e.getCause()+e.getMessage());
	        }
	        LOG.info("completed the student record update");
			return updateMessgae;
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
    			for (Student student : studentList1) {
    				StudentResponse studentResponse=new StudentResponse();
    				BeanUtils.copyProperties(student, studentResponse);
    				studentsList.add(studentResponse);
    			}
    			LOG.debug("fetching all the student details:[{}]",studentsList);
    			studentList1.clear();
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
    	LOG.info("started the get  student details from Database based on StudentId:[{}]",studentId);
    	if (!ObjectUtils.isEmpty(studentId)) {
			Optional<Student> studentOptional = studentRepository.findById(studentId);
			if (studentOptional.isPresent()) {
				StudentResponse studentResponse=new StudentResponse();
				BeanUtils.copyProperties(studentOptional.get(), studentResponse);
				LOG.debug("Get the student details based on studentId:[{}]",studentResponse);
				LOG.info("completed the fetching record based on studentId");
				return studentResponse;
			}else {
				throw new StudentNotFoundException("Student not found with this StudentID: "+studentId);
			}
		}
    
        return null;
    }
}
