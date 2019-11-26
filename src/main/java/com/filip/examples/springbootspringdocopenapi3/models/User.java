package com.filip.examples.springbootspringdocopenapi3.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.filip.examples.springbootspringdocopenapi3.models.auditing.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "USER")
public class User extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "")
    @JsonProperty("id")
    private Long id = null;

    @Schema(description = "", example = "FilipUser")
    @JsonProperty("username")
    private String username = null;

    @Schema(description = "", example = "Filip")
    @JsonProperty("firstName")
    @NotNull(message = "Firstname cannot be null")
    @NotEmpty(message = "custom message - must not be empty")
    @Size(min = 1, message = "custom message - size must be between 1 and 2147483647")
    private String firstName = null;

    @Schema(description = "", example = "Vanden Eynde")
    @JsonProperty("lastName")
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
    private String phone = null;

    @Schema(description = "User Status", example = "1")
    @JsonProperty("userStatus")
    private Integer userStatus = null;

}
