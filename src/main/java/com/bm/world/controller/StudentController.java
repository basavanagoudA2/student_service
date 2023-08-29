package com.bm.world.controller;

import com.bm.world.ApplicationConstants;
import com.bm.world.model.request.StudentRequest;
import com.bm.world.model.response.SaveResponse;
import com.bm.world.model.response.StudentResponse;
import com.bm.world.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value = ApplicationConstants.BASE_URL)
public class StudentController {
    @Autowired
    StudentService studentService;

    /**
     * This method for saving the Student data
     * @param studentRequest
     * @return
     */
    @CrossOrigin
    @PostMapping(value = ApplicationConstants.STUDENT_SAVE,consumes = "application/json")
    public ResponseEntity<SaveResponse> saveStudent(@RequestBody @Valid StudentRequest studentRequest){
        SaveResponse saveResponse=studentService.saveStudent(studentRequest);
        return new ResponseEntity<>(saveResponse, HttpStatus.OK);
    }
    @PutMapping(value = ApplicationConstants.STUDENT_UPDATE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateStudent(@RequestBody StudentRequest studentRequest) {
		String updateResponse = studentService.updateStudent(studentRequest);
		return new ResponseEntity<>(updateResponse,HttpStatus.OK);
	}
    @DeleteMapping(value = ApplicationConstants.STUDENT_DELETE)
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId){
        String deleteResponse=studentService.deleteStudent(studentId);
        return  new ResponseEntity<>(deleteResponse,HttpStatus.OK);
    }
    @GetMapping(value = ApplicationConstants.GET_ALL_STUDENTS)
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
    	List<StudentResponse> allStudents = studentService.getAllStudents();
    	return new ResponseEntity<>(allStudents,HttpStatus.OK);
	}
    @GetMapping(value = ApplicationConstants.GET_STUDENT_BY_ID,produces = "application/json")
    public ResponseEntity<Object> getStudentById(@PathVariable long studentId) {
    	StudentResponse studentResponse = studentService.getStudentBasedOnStudentId(studentId);
		return new ResponseEntity<>(studentResponse,HttpStatus.OK);
	}
    
}
