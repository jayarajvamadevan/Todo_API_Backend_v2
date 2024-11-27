package com.JavaProjects.rest.webservices.Restful_Webservices.user;

import com.JavaProjects.rest.webservices.Restful_Webservices.exception.UserNotFoundException;
import com.JavaProjects.rest.webservices.Restful_Webservices.repository.PostRepository;
import com.JavaProjects.rest.webservices.Restful_Webservices.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/v1")
public class UserResource_jpa {
    public UserRepository userRepository;
    public PostRepository postRepository;

    public UserResource_jpa(UserRepository userRepository,PostRepository postRepository)
    {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    //GET users
    @GetMapping("/jpa/users")
    public List<user> retrieveAllUsers() {
        return userRepository.findAll();

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
    @GetMapping("/jpa/users/{id}")
    public EntityModel<user> retrieveUserById(@PathVariable int id ) {
        Optional<user> user= userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id: "+id+" not Found");
        }
        EntityModel<user> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    //POST (creat) a user
    @PostMapping("/jpa/users")
    public ResponseEntity<user> createUser(@Valid @RequestBody user user ) {
        user savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
               .path("/{id}").build(savedUser.getId());
        return ResponseEntity.created(location).build();
    }
 //DELETE a User
     @DeleteMapping("/jpa/users/{id}")
     public void deleteUserById( @PathVariable int id ) {
         if (userRepository.findById(id).isEmpty()) {
             throw new UserNotFoundException("id: "+id+" not Found");
         }
         userRepository.deleteById(id);

     }

    //UPDATE a user
    @PutMapping("/jpa/users/{id}")
    public void updateUser( @PathVariable int id, @RequestBody user user ) {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException("id: "+id+" not Found");
        }
        userRepository.save(user);
    }

    /// POST IS INVOLVED
    //GET users byID  for Post
   @GetMapping("/jpa/users/{id}/get_posts")
   public List<Post> retrievePostForUser( @PathVariable int id ) {
       Optional<user> user= userRepository.findById(id);
       if (user.isEmpty()) {
           throw new UserNotFoundException("id: "+id+" not Found");
       }
       return  user.get().getPosts();
    }
    //GET users byID  for Post
    @PostMapping("/jpa/users/{id}/post_posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id , @Valid @RequestBody  Post post ) {
        Optional<user> user= userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id: "+id+" not Found");
        }
        post.setUser(user.get() );
        Post savedPost= postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").build(savedPost.getId());
        return ResponseEntity.created(location).build();
    }

}
