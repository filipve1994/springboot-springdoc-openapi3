package com.filip.examples.springbootspringdocopenapi3.security.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {

    @Schema(example = "email@gmail.com")
    //@Schema(example = "filipve")
    @NotBlank
    private String usernameOrEmail;

    @Schema(example = "Password@123")
    @NotBlank
    private String password;

}
