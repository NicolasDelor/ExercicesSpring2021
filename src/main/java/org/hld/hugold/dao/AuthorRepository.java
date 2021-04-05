package org.hld.hugold.dao;

import java.util.List;

import org.hld.hugold.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

	List<Author> findByName(String name);

}