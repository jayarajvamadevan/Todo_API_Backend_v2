package com.JavaProjects.rest.webservices.Restful_Webservices.repository;

import com.JavaProjects.rest.webservices.Restful_Webservices.user.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<user, Integer> {
}
