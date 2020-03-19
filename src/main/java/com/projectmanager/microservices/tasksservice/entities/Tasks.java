package com.projectmanager.microservices.tasksservice.entities;

import java.util.ArrayList;
import java.util.List;

public class Tasks {
	private List<Task> tasks;

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	private String port;

	public void addTasks(Task task) {
		if (null == tasks) {
			tasks = new ArrayList<Task>();
		}
		tasks.add(task);
	}
}
