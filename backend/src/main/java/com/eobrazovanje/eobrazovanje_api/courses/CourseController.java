package com.eobrazovanje.eobrazovanje_api.courses;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @GetMapping
    public String courses() {
        return "Hi";
    }
}
