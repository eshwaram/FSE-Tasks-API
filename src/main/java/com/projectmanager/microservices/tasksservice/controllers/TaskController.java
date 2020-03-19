package com.projectmanager.microservices.tasksservice.controllers;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.projectmanager.microservices.tasksservice.entities.ParentTask;
import com.projectmanager.microservices.tasksservice.entities.ParentTasks;
import com.projectmanager.microservices.tasksservice.entities.Project;
import com.projectmanager.microservices.tasksservice.entities.Task;
import com.projectmanager.microservices.tasksservice.entities.Tasks;
import com.projectmanager.microservices.tasksservice.repositories.ParentTaskRepository;
import com.projectmanager.microservices.tasksservice.repositories.ProjectRepository;
import com.projectmanager.microservices.tasksservice.repositories.TaskRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TaskController	{
	
	@Autowired
	ParentTaskRepository pTaskRepo;

	@Autowired
	TaskRepository taskRepo;

	@Autowired
	Environment env;
	
	@Autowired
	ProjectRepository projRepo;

	@PostMapping("/addparenttask")
	public void addPTask(@RequestBody ParentTask task) {
		pTaskRepo.save(task);
		updateNewTaskProject(task.getProjID());
	}

	@GetMapping("/getptasks")
	public ParentTasks getTasks() {
		Set<ParentTask> dbData = pTaskRepo.findAll();
		ParentTasks result = new ParentTasks();
		result.setPort(env.getProperty("local.server.port"));

		for (ParentTask data : dbData) {
			ParentTask pTask = new ParentTask();
			BeanUtils.copyProperties(data, pTask);
			result.addPTasks(pTask);
		}
		return result;
	}

	@PostMapping("/addtask")
	public void addTask(@RequestBody Task task) {
		taskRepo.save(task);
		updateNewTaskProject(task.getProjID());
	}

	@GetMapping("/gettask/{tid}")
	public Task getTask(@PathVariable long tid) {
		Optional<Task> dbData = taskRepo.findById(tid);
		Task existTask = new Task();
		if (dbData.isPresent()) {
			existTask = dbData.get();
		}
		return existTask;
	}
	
	@GetMapping("/getptask/{pid}")
	public ParentTask getPTask(@PathVariable long pid) {
		Optional<ParentTask> dbData = pTaskRepo.findById(pid);
		ParentTask existPTask = new ParentTask();
		if (dbData.isPresent()) {
			existPTask = dbData.get();
		}
		return existPTask;
	}

	@GetMapping("/getalltasks/{pid}")
	public Tasks getAllTasks(@PathVariable long pid) {
		Set<Task> dbData = taskRepo.findByProjID(pid);
		Tasks result = new Tasks();
		result.setPort(env.getProperty("local.server.port"));

		for (Task data : dbData) {
			Task task = new Task();
			BeanUtils.copyProperties(data, task);
			result.addTasks(task);
		}
		return result;
	}
	
	@GetMapping("/getallptasks/{pid}")
	public ParentTasks getAllPTasks(@PathVariable long pid) {
		Set<ParentTask> dbData = pTaskRepo.findByProjID(pid);
		ParentTasks result = new ParentTasks();
		result.setPort(env.getProperty("local.server.port"));

		for (ParentTask data : dbData) {
			ParentTask pTask = new ParentTask();
			BeanUtils.copyProperties(data, pTask);
			result.addPTasks(pTask);
		}
		return result;
	}
	
	@PutMapping("/endptask/{tid}")
	public void endPTask(@RequestBody ParentTask input, @PathVariable long tid) {
		Optional<ParentTask> dbData = pTaskRepo.findById(tid);
		ParentTask existTask = new ParentTask();
		if (dbData.isPresent()) {
			existTask = dbData.get();
		}
		existTask.setStatus(1l);
		pTaskRepo.save(existTask);
		updateTaskEndProject(input.getProjID());
	}
	
	@PutMapping("/endtask/{tid}")
	public void endTask(@RequestBody Task input, @PathVariable long tid) {
		Optional<Task> dbData = taskRepo.findById(tid);
		Task existTask = new Task();
		if (dbData.isPresent()) {
			existTask = dbData.get();
		}
		existTask.setStatus(1l);
		taskRepo.save(existTask);
		updateTaskEndProject(input.getProjID());
	}

	
	@PutMapping("/updateTask/{tid}")
	public void updateTask(@RequestBody Task input, @PathVariable long tid) {
		Optional<Task> dbData = taskRepo.findById(tid);
		Task existTask = new Task();
		if (dbData.isPresent()) {
			existTask = dbData.get();
		}
		existTask.setTaskName(input.getTaskName());
		existTask.setPriority(input.getPriority());
		existTask.setParentTaskID(input.getParentTaskID());
		existTask.setStartDate(input.getStartDate());
		existTask.setEndDate(input.getEndDate());
		taskRepo.save(existTask);
	}
	
	public void updateNewTaskProject(long pid) {
		Optional<Project> dbData = projRepo.findById(pid);
		Project existProj = new Project();
		if (dbData.isPresent()) {
			existProj = dbData.get();
		}
		existProj.setNoOfTasks(existProj.getNoOfTasks()+1);
		projRepo.save(existProj);
	}
	
	public void updateTaskEndProject(long pid) {
		Optional<Project> dbData = projRepo.findById(pid);
		Project existProj = new Project();
		if (dbData.isPresent()) {
			existProj = dbData.get();
		}
		existProj.setNoOfCompTasks(existProj.getNoOfCompTasks()+1);
		projRepo.save(existProj);
	}

}
