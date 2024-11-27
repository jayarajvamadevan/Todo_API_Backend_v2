package com.JavaProjects.rest.webservices.Restful_Webservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 10 )
    private String description;

    @ManyToOne
    @JsonIgnore
    private user user;

       public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    public com.JavaProjects.rest.webservices.Restful_Webservices.user.user getUser() {
        return user;
    }

    public void setUser(com.JavaProjects.rest.webservices.Restful_Webservices.user.user user) {
           this.user = user;
    }
}
