package com.projectmanager.microservices.tasksservice.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.projectmanager.microservices.tasksservice.entities.ParentTask;

public interface ParentTaskRepository extends CrudRepository<ParentTask, Long> {
	Set<ParentTask> findAll();

	Set<ParentTask> findByProjID(long pid);
}
