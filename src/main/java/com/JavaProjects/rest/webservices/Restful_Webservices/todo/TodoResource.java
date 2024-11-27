package com.JavaProjects.rest.webservices.Restful_Webservices.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/api/v2")
public class TodoResource {

    private  TodoService todoService;

    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveAllTodos(@PathVariable String username){

        return todoService.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodosbyId(@PathVariable String username,
                                  @PathVariable int id){
        return todoService.findById(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodosbyId(@PathVariable String username,
                                                @PathVariable int id){
         todoService.deleteById(id);
         return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodosById(@PathVariable String username
            ,@PathVariable int id,@RequestBody Todo todo){
            todoService.updateTodo(todo);
            return todo;
    }

    @PostMapping("/users/{username}/todos")
    public Todo createTodos(@PathVariable String username,
            @RequestBody Todo todo){
        return todoService.addTodo(username,todo.getDescription(),todo.getDueDate(),todo.isCompleted());
    }
}
