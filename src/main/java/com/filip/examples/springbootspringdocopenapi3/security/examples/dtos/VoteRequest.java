package com.filip.examples.springbootspringdocopenapi3.security.examples.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VoteRequest {

    @NotNull
    private Long choiceId;

}
