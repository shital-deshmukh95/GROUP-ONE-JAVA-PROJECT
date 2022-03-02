package com.lt.crs.repository;

import org.springframework.data.repository.CrudRepository;

import com.lt.crs.model.Professor;

public interface ProfessorRepository extends CrudRepository<Professor, String> 
{

}
