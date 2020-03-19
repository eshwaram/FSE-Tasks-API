package com.projectmanager.microservices.tasksservice.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.projectmanager.microservices.tasksservice.entities.Project;


public interface ProjectRepository extends CrudRepository<Project, Long>{
	Set<Project> findAll();
}
