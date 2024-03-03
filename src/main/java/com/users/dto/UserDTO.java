package com.users.dto;

import jakarta.validation.constraints.NotEmpty;

public class UserDTO {

    private int id;
    @NotEmpty()
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
