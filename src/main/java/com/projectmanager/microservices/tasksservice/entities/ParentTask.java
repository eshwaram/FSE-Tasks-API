package com.projectmanager.microservices.tasksservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ParentTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "parent_Task_ID")
	private long parentTaskID;
	@Column(name = "task_Name")
	private String taskName;
	@Column(name = "proj_ID")
	private long projID;
	@Column(name = "user_ID")
	private long userID;
	@Column(name = "status")
	private long status;

	public long getParentTaskID() {
		return parentTaskID;
	}

	public void setParentTaskID(long parentTaskID) {
		this.parentTaskID = parentTaskID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public long getProjID() {
		return projID;
	}

	public void setProjID(long projID) {
		this.projID = projID;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

}
