package com.example.exception.controller;

import com.example.exception.exception.NotFoundException;
import com.example.exception.model.Person;
import com.example.exception.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @ResponseBody
    @GetMapping(value = "/person/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable int id){
        Person person = personRepository.findById(id);
        if(person==null) throw new NotFoundException("Not found id: "+id);
        return ResponseEntity.ok(person);
    }

    @GetMapping(value = "/home")
    public String homePage(Model model){
        Person person = personRepository.findById(1);
        if(person==null) throw new NotFoundException("Not found id: "+1);

        model.addAttribute("person", person);
        return "index";
    }
}
