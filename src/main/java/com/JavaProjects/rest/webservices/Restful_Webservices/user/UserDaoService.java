package com.JavaProjects.rest.webservices.Restful_Webservices.user;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<user> users = new ArrayList<>();
    private static int idCounter = 0;
    static {
        users.add(new user(++idCounter,"Sam", LocalDate.now().minusYears(20)));
        users.add(new user(++idCounter,"Ram", LocalDate.now().minusYears(30)));
        users.add(new user(++idCounter,"Yammy", LocalDate.now().minusYears(40)));
        users.add(new user(++idCounter,"Sammy", LocalDate.now().minusYears(50)));
        users.add(new user(++idCounter,"Rommy", LocalDate.now().minusYears(60)));
    }
    //Display all users
    public List<user> findAll() {
        return users;
    }
    //Display users by id
    public user findByUserId(int id) {
        Predicate<? super user> predicate
                = user -> user.getId() == id;
        return users.stream().filter(predicate).findFirst().orElse(null);
    }
    //Creat a user
    public user creatUser(user user) {
        user.setId(++idCounter);
        users.add(user);
        return user;
    }
    //Delete a user
    public void deleteByUserId(int id) {
        Predicate<? super user> predicate
                = user -> user.getId() == id;
        users.removeIf(predicate);
    }
    //Update a User
    public void updateUser(int id, user user) {
            deleteByUserId(id);
            user.setId(id);
            users.add(user);
    }
}
