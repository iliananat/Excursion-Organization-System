package com.example.demo.config;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InfoResponse {
    private final String message;

    public InfoResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
