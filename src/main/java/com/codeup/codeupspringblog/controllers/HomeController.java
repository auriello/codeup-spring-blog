package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
        //    get request mapping for /test page
        @GetMapping("/")
//set response content type to text/html
        @ResponseBody
//    define method that will return text/html
        public String test() {
            return "This is the home page!";
        }
}
