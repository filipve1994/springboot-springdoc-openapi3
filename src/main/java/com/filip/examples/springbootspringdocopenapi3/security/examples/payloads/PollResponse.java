package com.filip.examples.springbootspringdocopenapi3.security.examples.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PollResponse {

    private Long id;

    private String question;

    private List<ChoiceResponse> choices;

    private UserSummary createdBy;

    private LocalDateTime creationDateTime;

    private LocalDateTime expirationDateTime;

    private Boolean isExpired;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long selectedChoice;

    private Long totalVotes;


}
