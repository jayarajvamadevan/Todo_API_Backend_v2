package com.JavaProjects.rest.webservices.Restful_Webservices.repository;

import com.JavaProjects.rest.webservices.Restful_Webservices.todo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
    List<Todo> findByUsername(String username);
}

