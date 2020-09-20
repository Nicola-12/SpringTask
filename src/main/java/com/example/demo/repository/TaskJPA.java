package com.example.demo.repository;

import com.example.demo.Task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskJPA extends JpaRepository<Task, Long> {

}

