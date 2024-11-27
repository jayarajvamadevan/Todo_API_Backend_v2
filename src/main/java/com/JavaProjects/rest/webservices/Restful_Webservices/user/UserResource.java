package com.JavaProjects.rest.webservices.Restful_Webservices.user;

import com.JavaProjects.rest.webservices.Restful_Webservices.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


//@RestController
@RequestMapping("/api/v1")
public class UserResource {
    public UserDaoService userDaoService;
    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    //GET users
    @GetMapping("/users")
    public List<user> retrieveAllUsers() {
        return userDaoService.findAll();

    }

//    //GET users byID
//    @GetMapping("/users/{id}")
//    public user retrieveUserById( @PathVariable int id ) {
//       user user= userDaoService.findByUserId(id);
//       if (user == null) {
//           throw new UserNotFoundException("id: "+id+" not Found");
//       }
//       return user;
//    }

    //GET users byID with HATEOAS
    @GetMapping("/users/{id}")
    public EntityModel<user> retrieveUserById(@PathVariable int id ) {
        user user= userDaoService.findByUserId(id);
        if (user == null) {
            throw new UserNotFoundException("id: "+id+" not Found");
        }
        EntityModel<user> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    //POST (creat) a user
    @PostMapping("/users")
    public ResponseEntity<user> createUser(@Valid @RequestBody user user ) {
        user savedUser = userDaoService.creatUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
               .path("/{id}").build(savedUser.getId());
        return ResponseEntity.created(location).build();
    }
 //DELETE a User
     @DeleteMapping("/users/{id}")
     public void deleteUserById( @PathVariable int id ) {
         if (userDaoService.findByUserId(id) == null) {
             throw new UserNotFoundException("id: "+id+" not Found");
         }
         userDaoService.deleteByUserId(id);

     }

    //UPDATE a user
    @PutMapping("/users/{id}")
    public void updateUser( @PathVariable int id, @RequestBody user user ) {
        if (userDaoService.findByUserId(id) == null) {
            throw new UserNotFoundException("id: "+id+" not Found");
        }
        userDaoService.updateUser(id,user);
    }
}
