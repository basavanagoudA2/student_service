package com.bm.world.controller;

import com.bm.world.ApplicationConstants;
import com.bm.world.model.request.StudentRequest;
import com.bm.world.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = ApplicationConstants.BASE_URL)
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping(value = ApplicationConstants.STUDENT_SAVE,consumes = "application/json")
    public ResponseEntity<String> saveStudent(@RequestBody @Valid StudentRequest studentRequest){
        String saveResponse=studentService.saveStudent(studentRequest);
        return new ResponseEntity<>(saveResponse, HttpStatus.OK);
    }
    @PutMapping(value = ApplicationConstants.STUDENT_UPDATE,consumes = "application/json")
    public ResponseEntity<String> updateStudent(@RequestBody StudentRequest studentRequest) {
		String updateResponse = studentService.updateStudent(studentRequest);
		return new ResponseEntity<String>(updateResponse,HttpStatus.OK);
	}
    @DeleteMapping(value = ApplicationConstants.STUDENT_DELETE)
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId){
        String deleteResponse=studentService.deleteStudent(studentId);
        return  new ResponseEntity<>(deleteResponse,HttpStatus.OK);
    }
    
}
