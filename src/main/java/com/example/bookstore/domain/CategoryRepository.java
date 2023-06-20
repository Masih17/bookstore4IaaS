package com.example.bookstore.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository  extends CrudRepository<Category, Long> {

	List<Category> findByName(@Param("name") String name);

}
