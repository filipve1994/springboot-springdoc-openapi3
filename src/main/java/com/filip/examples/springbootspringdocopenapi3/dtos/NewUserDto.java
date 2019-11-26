package com.filip.examples.springbootspringdocopenapi3.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class NewUserDto {

    @Schema(description = "", example = "FilipUser")
    @JsonProperty("username")
    private String username = null;

    @Schema(description = "", example = "Filip")
    @JsonProperty("firstName")
    @NotNull(message = "firstName cannot be null")
    @NotEmpty(message = "firstName must not be empty")
    private String firstName = null;

    @Schema(description = "", example = "Vanden Eynde")
    @JsonProperty("lastName")
    @NotNull(message = "lastName cannot be null")
    @NotEmpty(message = "lastName must not be empty")
    private String lastName = null;

    @Schema(description = "", example = "toolongemail@gmail.com")
    @JsonProperty("email")
    @Size(
            min = 5,
            max = 20,
            message = "The user email '${validatedValue}' must be between {min} and {max} characters long"
    )
    private String email = null;

    @Schema(description = "", example = "XXXXXXXXXXXXX")
    @JsonProperty("password")
    private String password = null;


    @Schema(description = "", example = "123-456-789")
    @JsonProperty("phone")
    @NotNull(message = "Phone cannot be null")
    @NotEmpty(message = "Phone must not be empty")
    @Size(min = 6, max = 20)
    private String phone = null;
}
