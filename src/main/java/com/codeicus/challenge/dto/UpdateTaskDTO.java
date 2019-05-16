package com.codeicus.challenge.dto;

import com.codeicus.challenge.model.Task;
import com.codeicus.challenge.validator.annotation.Match;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateTaskDTO extends TaskDTO {

    @NotNull
    private Long id;

    @NotNull
    @NotEmpty
    @Match(regex = "^(RUNNING|NOT_RUNNING|DELAYED|FINISHED|SCHEDULED)$")
    private String status;

    public UpdateTaskDTO() {

    }

    public UpdateTaskDTO(Task task) {
        this.id = task.getId();
        this.status = task.getStatus().name();
        this.action = task.getAction().name();
        this.description = task.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UpdateTaskDTO{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
