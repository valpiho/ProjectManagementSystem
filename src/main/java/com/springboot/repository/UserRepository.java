package com.springboot.repository;

import com.springboot.entity.Project;
import com.springboot.entity.Task;
import com.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByUsernameNot(String username);

    User findUserByUsername(String username);

    User findUserByEmail(String email);


}
