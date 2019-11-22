package com.filip.examples.springbootspringdocopenapi3.models;

import com.fasterxml.jackson.annotation.JsonProperty;
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
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "")
    @JsonProperty("id")
    private Long id = null;

    @Schema(description = "")
    @JsonProperty("username")
    private String username = null;

    @Schema(description = "")
    @JsonProperty("firstName")
    private String firstName = null;

    @Schema(description = "")
    @JsonProperty("lastName")
    private String lastName = null;

    @Schema(description = "")
    @JsonProperty("email")
    private String email = null;

    @Schema(description = "")
    @JsonProperty("password")
    private String password = null;

    @Schema(description = "")
    @JsonProperty("phone")
    private String phone = null;

    @Schema(description = "User Status")
    @JsonProperty("userStatus")
    private Integer userStatus = null;

}
