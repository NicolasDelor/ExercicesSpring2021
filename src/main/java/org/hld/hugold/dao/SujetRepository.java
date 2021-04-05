package org.hld.hugold.dao;

import java.util.List;

import org.hld.hugold.entity.Sujet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SujetRepository extends JpaRepository<Sujet, String> {

	@Query("select s from Sujet s where s.city like %:end")
	public List<Sujet> findByCityEndWith(String end);

	public List<Sujet> findByName(String name);
}
