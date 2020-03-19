package com.projectmanager.microservices.tasksservice.entities;

import java.util.ArrayList;
import java.util.List;

public class ParentTasks {
	private List<ParentTask> pTasks;
	private String port;

	public List<ParentTask> getpTasks() {
		return pTasks;
	}

	public void setpTasks(List<ParentTask> pTasks) {
		this.pTasks = pTasks;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void addPTasks(ParentTask pTask) {
		if (null == pTasks) {
			pTasks = new ArrayList<ParentTask>();
		}
		pTasks.add(pTask);
	}
}
