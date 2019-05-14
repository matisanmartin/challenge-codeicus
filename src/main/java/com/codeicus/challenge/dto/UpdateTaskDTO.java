package com.codeicus.challenge.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateTaskDTO extends TaskDTO {

    @NotNull
    private Long id;

    @NotNull
    @NotEmpty
    //todo agregar regex
    private String status;

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
}
