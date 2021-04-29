package org.hld.nicolasd.dao;

import java.util.List;

import org.hld.nicolasd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query("select s from User s where s.city like %:end")
	public List<User> findByCityEndWith(String end);

	public List<User> findByName(String name);
}
