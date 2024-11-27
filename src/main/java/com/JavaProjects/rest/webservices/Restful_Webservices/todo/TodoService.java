package com.JavaProjects.rest.webservices.Restful_Webservices.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos =new ArrayList<>();
    private static int todosCount = 0;
    static {
        todos.add(new Todo(++todosCount,"Jay",
                "Learn java", LocalDate.now().plusYears(1),false));
        todos.add(new Todo(++todosCount,"Jay",
                "Learn javaScript", LocalDate.now().plusYears(1),false));
        todos.add(new Todo(++todosCount,"Jay",
                "Learn SpringBoot", LocalDate.now().plusYears(1),false));
        todos.add(new Todo(++todosCount,"Jay",
                "Learn Node JS", LocalDate.now().plusYears(1),false));
        todos.add(new Todo(++todosCount,"Jay",
                "Learn React", LocalDate.now().plusYears(1),false));
    }
    public   List<Todo> findByUsername(String username)
    {
        return todos;
    }

    public Todo addTodo(String username,String description,LocalDate dueDate,boolean completed){
        Todo todo =new Todo(++todosCount,username,description,dueDate,completed);
        todos.add(todo);
        return todo;
    }

    public void deleteById(int id)
    {
        Predicate<? super Todo> predicate
                  = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id){
        Predicate<? super Todo> predicate
                = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate ).findFirst().get();
        return todo;
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
