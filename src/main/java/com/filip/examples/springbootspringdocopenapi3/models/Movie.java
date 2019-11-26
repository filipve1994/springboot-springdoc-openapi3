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
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "MOVIE")
public class Movie extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "")
    @JsonProperty("id")
    private String id = null;

    @Schema(description = "")
    @JsonProperty("name")
    @NotEmpty(message = "Movie name cannot be empty.")
    private String name;

}