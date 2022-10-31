package com.task.management.system.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String estimate;
    private String priority;
    private String summary;


    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL)
    //@JoinTable(name ="task_worklog", joinColumns = @JoinColumn (name="task_id"), inverseJoinColumns = @JoinColumn (name="worklog_id"))
    private List<Worklogs> worklog;

    @ManyToOne(cascade = CascadeType.ALL)
    //@JoinTable(name ="user_task", joinColumns = @JoinColumn (name="user_id"), inverseJoinColumns = @JoinColumn (name="task_id"))
    private User user ;


}