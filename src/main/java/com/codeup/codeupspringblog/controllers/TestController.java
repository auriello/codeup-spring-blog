package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@controller tells the compiler that this class will extend the httpservlet and setup our class to control different url patterns.
@Controller
public class TestController {
//    get request mapping for /test page
    @GetMapping("/test")
//set response content type to text/html
    @ResponseBody
//    define method that will return text/html
    public String test() {
        return "Hello Auriel!";
    }




//    /parks
    @GetMapping("/parks/{park}")
    @ResponseBody
    public String parks(@PathVariable String park, @PathVariable String message) {
        return "<h1>Welcome to " + park + " ! </h1><p>" + message + "</p>";
    }

    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number) {
        return number + " plus one is " + (number + 1) + "!";
    }

}
