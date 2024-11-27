package com.JavaProjects.rest.webservices.Restful_Webservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

//@GetMapping("/filter_static")
//    public SomeBean filterStatic() {
//        return new SomeBean("Jay",41,"Male");
//    }
//@GetMapping("/filter_list_static")
//    public List<SomeBean> filterListStatic() {
//        return Arrays.asList(new SomeBean("Jay", 41, "Male"),
//                new SomeBean("Jay2", 80, "Male"));
//    }
@GetMapping("/filter_dynamic")
    public MappingJacksonValue filtering()
    {
        SomeBean someBean= new SomeBean("Jay",41,"Male");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept("name", "gender");
        FilterProvider filterProvider =
                new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }
@GetMapping("/filter_list_dynamic")
    public MappingJacksonValue filtreing_list()
    {
        List<SomeBean> list = Arrays.asList(new SomeBean("Jay", 41, "Male"),
                new SomeBean("Jay2", 80, "Male"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept("name", "gender");
        FilterProvider filterProvider =
                new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

}

