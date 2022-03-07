package com.lt.crs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lt.crs.model.Person;

//NOTE: DONT PUT ANY @Repostory as it is extending CrudRepository
public interface PersonRepository<P> extends JpaRepository<Person, Long> {
    List<Person> findByFirstName(String firstName);
    //save()
    //findOne
    //exists
    //delete
    //deleteAll
}

//Note: By inheriting the CRUD repository, we can have many methods without implementation
//We can extend 2 repos: CrudRepository or JpaRepository - both are interface of Spring Data repositories
//most of the features are common features exists inside the JPARepositories with add-on extensions methods to do pagignation/caching and 
//sorting records