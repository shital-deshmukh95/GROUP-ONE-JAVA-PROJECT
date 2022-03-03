/**
 * 
 */
package com.lt.crs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lt.crs.model.Catalog;

/**
 * @author user215
 *
 */
public interface CatalogRepository extends CrudRepository<Catalog, String>{
	
	List<Catalog> findAll();

}
