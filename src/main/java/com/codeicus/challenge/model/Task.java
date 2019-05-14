package com.codeicus.challenge.model;

import com.codeicus.challenge.dto.TaskDTO;
import com.codeicus.challenge.dto.UpdateTaskDTO;

import javax.persistence.*;

@Entity
@Table(name = "TASK")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Action action;

    public Task() {

    }

    public Task(TaskDTO taskDTO) {
        this.status = Status.NOT_RUNNING;
        this.action = Action.valueOf(taskDTO.getAction());
        this.description = taskDTO.getDescription();
    }

    public void update(UpdateTaskDTO updateTaskDTO) {
        this.status = Status.valueOf(updateTaskDTO.getStatus());
        this.action = Action.valueOf(updateTaskDTO.getAction());
        this.description = updateTaskDTO.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", action=" + action +
                '}';
    }
}
