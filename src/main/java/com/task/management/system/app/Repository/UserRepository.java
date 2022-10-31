package com.task.management.system.app.Repository;



import com.task.management.system.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT a from User a Where a.username = :username")
	public User getUserByUsername(@Param("username") String username);

	public User findByForgetPasswordToken(String token);

    User findByName(String name);
}
