package com.JavaProjects.rest.webservices.Restful_Webservices.helloworld;

import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping(path= "/api/v1")
public class HelloWorldController {
    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(path = "/basicauth")
    public String basicAuthCheck() {
        return "Success";
    }
    @GetMapping(path = "/hello-world")
    public String sayHello() {
        return "Hello welcome to API World!";
    }
    //json format display
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello Bean welcome to REST-API World!");
    }

    @GetMapping(path = "/hello-world-path/{name}")
    public HelloWorldBean helloWorldPath(@PathVariable String name) {
        //return new HelloWorldBean("Hello welcome to REST-API World! : " + name);
        return new HelloWorldBean(String.format("Hello  %s Welcome to REST-API !", name));
    }
    //Display international language  message
    @GetMapping(path = "/hello-world-i18nbean")
    public String helloWorldInternationalizedBean() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,
                "Default Message", locale);

        //return "Hello welcome to REST-API World!";
    }


}
