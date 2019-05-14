package com.codeicus.challenge.dto;

import com.codeicus.challenge.validator.annotation.Length;
import com.codeicus.challenge.validator.annotation.Match;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TaskDTO {

    @NotNull
    @NotEmpty
    @Length(min = 1, max = 255)
    protected String description;

    @NotNull
    @NotEmpty
    @Match(regex = "^(LOG_INFO|LOG_ERROR|LOG_DEBUG)$")
    protected String action;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
