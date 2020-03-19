package com.projectmanager.microservices.tasksservice.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.projectmanager.microservices.tasksservice.entities.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
	Set<Task> findAll();

	Set<Task> findByProjID(long pid);

}
