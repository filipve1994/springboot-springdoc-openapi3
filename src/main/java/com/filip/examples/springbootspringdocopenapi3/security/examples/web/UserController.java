package com.filip.examples.springbootspringdocopenapi3.security.examples.web;

import com.filip.examples.springbootspringdocopenapi3.errorhandling.exceptions.ResourceNotFoundException;
import com.filip.examples.springbootspringdocopenapi3.errorhandling.models.ApiError;
import com.filip.examples.springbootspringdocopenapi3.models.User;
import com.filip.examples.springbootspringdocopenapi3.security.examples.payloads.PagedResponse;
import com.filip.examples.springbootspringdocopenapi3.security.examples.payloads.PollResponse;
import com.filip.examples.springbootspringdocopenapi3.security.examples.payloads.UserIdentityAvailability;
import com.filip.examples.springbootspringdocopenapi3.security.examples.payloads.UserProfile;
import com.filip.examples.springbootspringdocopenapi3.security.examples.payloads.UserSummary;
import com.filip.examples.springbootspringdocopenapi3.security.examples.repositories.PollRepository;
import com.filip.examples.springbootspringdocopenapi3.security.examples.repositories.VoteRepository;
import com.filip.examples.springbootspringdocopenapi3.security.examples.services.PollService;
import com.filip.examples.springbootspringdocopenapi3.security.examples.utils.AppConstants;
import com.filip.examples.springbootspringdocopenapi3.security.jwt.CurrentUser;
import com.filip.examples.springbootspringdocopenapi3.security.models.ApplicationUser;
import com.filip.examples.springbootspringdocopenapi3.security.models.UserPrincipal;
import com.filip.examples.springbootspringdocopenapi3.security.repositories.ApplicationUserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.filip.examples.springbootspringdocopenapi3.config.OpenApiConstants.USER_TAG;

@RestController
@RequestMapping("/api")
@Tag(name = "examples")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private PollService pollService;

    @Operation(summary = "get current user", tags = {"examples"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = UserSummary.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request param", content = @Content(schema = @Schema(implementation = ApiError.class))),
    })
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @Operation(summary = "get current user 2", tags = {"examples"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = UserSummary.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request param", content = @Content(schema = @Schema(implementation = ApiError.class))),
    })
    @GetMapping("/user/me2")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getCurrentUser2(@RequestHeader(value = "Authorization", defaultValue = "Bearer ") String authorizationHeader,
                                          @Parameter(hidden = true, required = false) @CurrentUser UserPrincipal currentUser

    ) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return ResponseEntity.ok(userSummary);
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !applicationUserRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !applicationUserRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        ApplicationUser user = applicationUserRepository.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User", "username", username);
        }

        long pollCount = pollRepository.countByCreatedBy(user.getId());
        long voteCount = voteRepository.countByUserId(user.getId());

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreationDate(), pollCount, voteCount);

        return userProfile;
    }

    @GetMapping("/users/{username}/polls")
    public PagedResponse<PollResponse> getPollsCreatedBy(@PathVariable(value = "username") String username,
                                                         @CurrentUser UserPrincipal currentUser,
                                                         @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                         @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return pollService.getPollsCreatedBy(username, currentUser, page, size);
    }


    @GetMapping("/users/{username}/votes")
    public PagedResponse<PollResponse> getPollsVotedBy(@PathVariable(value = "username") String username,
                                                       @CurrentUser UserPrincipal currentUser,
                                                       @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                       @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return pollService.getPollsVotedBy(username, currentUser, page, size);
    }

}
