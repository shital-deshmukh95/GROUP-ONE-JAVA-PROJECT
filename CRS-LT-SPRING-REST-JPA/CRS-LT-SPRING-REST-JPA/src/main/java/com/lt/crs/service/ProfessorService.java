package com.lt.crs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.crs.model.Professor;
import com.lt.crs.repository.ProfessorRepository;

@Service
public class ProfessorService 
{
	
	@Autowired
	ProfessorRepository professorRepository ;

	public String getProfessorById(String profId) {
		String name = null;
		Optional<Professor> professor =  professorRepository.findById(profId);

		if(professor.isPresent()) {
			name  = professor.get().getName();
		}
		return  name;
	}
	
	
	public String viewEnrolledStudents(String profId) {
		String name = null;
		Optional<Professor> professor =  professorRepository.findById(profId);

		if(professor.isPresent()) {
			name  = professor.get().getName();
		}
		return  name;
	}


	public boolean addGrade(String studentID, String courseID, String grade) {
		// TODO Auto-generated method stub
		return false;
	}
}
