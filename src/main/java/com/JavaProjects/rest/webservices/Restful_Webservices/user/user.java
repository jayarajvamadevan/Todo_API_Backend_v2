package com.JavaProjects.rest.webservices.Restful_Webservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "user_details")
public class user {
   // @JsonProperty("User_Id")
   @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2,message = "Name should have at least 2 characters")
   // @JsonProperty("User_Name")
    private String name;
    @Past(message = "Birth Date should be in the past")
   // @JsonProperty("User_DoB")
    private LocalDate birthDATE;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List <Post> posts;

    public user() {

    }

    public user(int id, String name, LocalDate birthDATE) {
        this.id = id;
        this.name = name;
        this.birthDATE = birthDATE;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDATE() {
        return birthDATE;
    }

    public void setBirthDATE(LocalDate birthDATE) {
        this.birthDATE = birthDATE;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDATE=" + birthDATE +
                '}';
    }
}
