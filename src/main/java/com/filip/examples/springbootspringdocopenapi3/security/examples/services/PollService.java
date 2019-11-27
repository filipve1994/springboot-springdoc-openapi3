package com.filip.examples.springbootspringdocopenapi3.security.examples.services;

import com.filip.examples.springbootspringdocopenapi3.security.examples.dtos.PollRequest;
import com.filip.examples.springbootspringdocopenapi3.security.examples.dtos.VoteRequest;
import com.filip.examples.springbootspringdocopenapi3.security.examples.models.Poll;
import com.filip.examples.springbootspringdocopenapi3.security.examples.payloads.PagedResponse;
import com.filip.examples.springbootspringdocopenapi3.security.examples.payloads.PollResponse;
import com.filip.examples.springbootspringdocopenapi3.security.models.UserPrincipal;

public interface PollService {
    PagedResponse<PollResponse> getAllPolls(UserPrincipal currentUser, int page, int size);

    PagedResponse<PollResponse> getPollsCreatedBy(String username, UserPrincipal currentUser, int page, int size);

    PagedResponse<PollResponse> getPollsVotedBy(String username, UserPrincipal currentUser, int page, int size);

    Poll createPoll(PollRequest pollRequest);

    PollResponse getPollById(Long pollId, UserPrincipal currentUser);

    PollResponse castVoteAndGetUpdatedPoll(Long pollId, VoteRequest voteRequest, UserPrincipal currentUser);
}
