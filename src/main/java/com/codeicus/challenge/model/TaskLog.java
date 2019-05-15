package com.codeicus.challenge.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;

@Entity
@Table(name = "TASK_LOG")
public class TaskLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column
    private Long taskId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Result result;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Operation operation;

    @Column
    private String description;

    public TaskLog() {

    }

    public TaskLog(Optional<Long> taskId, Operation operation, Result result, String description) {
        this.taskId = taskId.orElse(null);
        this.operation = operation;
        this.result = result;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "TaskLog{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", result=" + result +
                ", operation=" + operation +
                ", description='" + description + '\'' +
                '}';
    }
}
