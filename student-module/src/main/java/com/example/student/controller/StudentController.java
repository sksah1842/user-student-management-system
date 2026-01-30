package com.example.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.entity.Student;
import com.example.student.repository.StudentRepository;


@RestController
@RequestMapping("/api/students")
public class StudentController {

 @Autowired
 private StudentRepository repo;

 @GetMapping
 public List<Student> getAll(){
  return repo.findAll();
 }

 @PostMapping
 @PreAuthorize("hasRole('ADMIN')")
 public Student save(@RequestBody Student s){
  return repo.save(s);
 }
}
