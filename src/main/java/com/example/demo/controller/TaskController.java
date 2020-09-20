package com.example.demo.controller;

import java.util.List;

import com.example.demo.Task;
import com.example.demo.repository.TaskJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskJPA taskJPA;

    @GetMapping
    public List<Task> index() {
        return taskJPA.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> show(@PathVariable("id") Long id) {

        return ResponseEntity.of(taskJPA.findById(id));
    }

    @PostMapping
    public Task store(@RequestBody @Validated Task task) {
        return taskJPA.save(task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void destroy(@PathVariable("id") Long id) {
        if (!taskJPA.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        taskJPA.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Task update(@PathVariable("id") Long id, @RequestBody @Validated Task task) {
        if (!taskJPA.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        task.setId(id);
        return taskJPA.save(task);

    }

}