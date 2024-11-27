package com.JavaProjects.rest.webservices.Restful_Webservices.repository;

import com.JavaProjects.rest.webservices.Restful_Webservices.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
