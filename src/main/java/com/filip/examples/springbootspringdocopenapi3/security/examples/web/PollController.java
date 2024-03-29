package com.filip.examples.springbootspringdocopenapi3.security.examples.web;

import com.filip.examples.springbootspringdocopenapi3.security.examples.dtos.PollRequest;
import com.filip.examples.springbootspringdocopenapi3.security.examples.dtos.VoteRequest;
import com.filip.examples.springbootspringdocopenapi3.security.examples.models.Poll;
import com.filip.examples.springbootspringdocopenapi3.security.examples.payloads.PagedResponse;
import com.filip.examples.springbootspringdocopenapi3.security.examples.payloads.PollResponse;
import com.filip.examples.springbootspringdocopenapi3.security.examples.repositories.PollRepository;
import com.filip.examples.springbootspringdocopenapi3.security.examples.repositories.VoteRepository;
import com.filip.examples.springbootspringdocopenapi3.security.examples.services.PollService;
import com.filip.examples.springbootspringdocopenapi3.security.examples.utils.AppConstants;
import com.filip.examples.springbootspringdocopenapi3.security.jwt.ApiResponse;
import com.filip.examples.springbootspringdocopenapi3.security.jwt.CurrentUser;
import com.filip.examples.springbootspringdocopenapi3.security.models.UserPrincipal;
import com.filip.examples.springbootspringdocopenapi3.security.repositories.ApplicationUserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/polls")
@Tag(name = "examples")
public class PollController {

    private static final Logger logger = LoggerFactory.getLogger(PollController.class);

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PollService pollService;

    @GetMapping
    public PagedResponse<PollResponse> getPolls(@CurrentUser UserPrincipal currentUser,
                                                @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return pollService.getAllPolls(currentUser, page, size);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createPoll(@Valid @RequestBody PollRequest pollRequest) {
        Poll poll = pollService.createPoll(pollRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{pollId}")
                .buildAndExpand(poll.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Poll Created Successfully"));
    }

    @GetMapping("/{pollId}")
    public PollResponse getPollById(@CurrentUser UserPrincipal currentUser,
                                    @PathVariable Long pollId) {
        return pollService.getPollById(pollId, currentUser);
    }

    @PostMapping("/{pollId}/votes")
    @PreAuthorize("hasRole('USER')")
    public PollResponse castVote(@CurrentUser UserPrincipal currentUser,
                                 @PathVariable Long pollId,
                                 @Valid @RequestBody VoteRequest voteRequest) {
        return pollService.castVoteAndGetUpdatedPoll(pollId, voteRequest, currentUser);
    }
}
