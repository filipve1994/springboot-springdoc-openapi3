package com.filip.examples.springbootspringdocopenapi3.security.examples.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSummary {

    private Long id;
    private String username;
    private String name;

    public UserSummary(Long id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }
}
