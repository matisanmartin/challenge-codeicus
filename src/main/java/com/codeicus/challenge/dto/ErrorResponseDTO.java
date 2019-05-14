package com.codeicus.challenge.dto;

public class ErrorResponseDTO {

    private String description;

    public ErrorResponseDTO(){

    }

    public ErrorResponseDTO(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
