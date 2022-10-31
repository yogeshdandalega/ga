package com.task.management.system.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.task.management.system.app.model.TimeConverter.timeConvertIntoDayHourseMinutes;

@Entity
public class Worklogs {

	@Id
	@GeneratedValue
	private int id;
	private String date;
	private String timeSpent;
	private String workDescription;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	//@JoinTable(name ="task_worklog", joinColumns = @JoinColumn (name="task_id"), inverseJoinColumns = @JoinColumn (name="worklog_id"))
	private Task task;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(String timeSpent) {
		this.timeSpent = timeSpent;
	}

	public String getWorkDescription() {
		return workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}
