package com.filip.examples.springbootspringdocopenapi3.security.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignUpRequest {

    @Schema(example = "Filip Vanden Eynde")
    @NotBlank
    @Size(min = 4, max = 40)
    private String name;

    @Schema(example = "filipve")
    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @Schema(example = "email@gmail.com")
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @Schema(example = "Password@123")
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
