package com.filip.examples.springbootspringdocopenapi3.web;

import com.filip.examples.springbootspringdocopenapi3.models.User;
import com.filip.examples.springbootspringdocopenapi3.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.filip.examples.springbootspringdocopenapi3.config.OpenApiConstants.USER_TAG;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
@RequestMapping(value = "/")
@Tag(name = USER_TAG, description = "the user API")
public class UserApiController {

    @Autowired
    private IUserService userService;

    @Operation(
            summary = "Get all users",
            description = "",
            tags = {USER_TAG}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping(value = "/user/all", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity getAll(){
        return ResponseEntity.ok(userService.getall());
    }

    @Operation(summary = "Create user", tags = {USER_TAG})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping(value = "/user", consumes = {APPLICATION_JSON_VALUE})
    public void createUser(@Parameter(description = "Created user object", required = true) @Valid @RequestBody User user) {
        userService.createUser(user);
    }

    @Operation(summary = "Creates list of users with given input array", tags = {USER_TAG})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping(value = "/user/createWithArray", consumes = {APPLICATION_JSON_VALUE})
    public void createUsersWithArrayInput(@Parameter(description = "List of user object", required = true) @Valid @RequestBody List<User> user) {
        userService.createUsersWithArrayInput(user);
    }

    @Operation(summary = "Creates list of users with given input array", tags = {USER_TAG})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping(value = "/user/createWithList", consumes = {APPLICATION_JSON_VALUE})
    public void createUsersWithListInput(@Parameter(description = "List of user object", required = true) @Valid @RequestBody List<User> users) {
        userService.createUsersWithListInput(users);
    }

    @Operation(summary = "Creates list of users with given input array", tags = {USER_TAG})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @DeleteMapping(value = "/user/{username}")
    public void deleteUser(@Parameter(description = "The name that needs to be deleted", required = true) @PathVariable("username") String username) {
        userService.deleteUser(username);
    }

    @Operation(summary = "Get user by user name", tags = {USER_TAG})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied"),
            @ApiResponse(responseCode = "404", description = "User not found")})

    @GetMapping(value = "/user/{username}", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity getUserByName(@Parameter(description = "The name that needs to be fetched. Use user1 for testing.", example = "user1", required = true)
                                        @PathVariable("username") String username) {
        return ResponseEntity.ok(userService.getUserByName(username));
    }

    @Operation(summary = "Logs user into the system", tags = {USER_TAG})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid username/password supplied")
    })
    @GetMapping(value = "/user/login", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity loginUser(@Parameter(description = "The user name for login", required = true) @NotNull @Valid @RequestParam(value = "username", required = true) String username,
                                    @Parameter(description = "The password for login in clear text", required = true) @NotNull @Valid @RequestParam(value = "password", required = true) String password) {
        return userService.loginUser(username, password);
        //return getDelegate().loginUser(username, password);
    }

    @Operation(summary = "Logs out current logged in user session", tags = {USER_TAG})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "/user/logout")
    public void logoutUser() {
        userService.logoutUser();
    }

    @Operation(summary = "Updated user", tags = {USER_TAG})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid user supplied"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping(value = "/user/{username}", consumes = {APPLICATION_JSON_VALUE})
    public void updateUser(@Parameter(description = "name that need to be deleted", required = true) @PathVariable("username") String username,
                           @Parameter(description = "Updated user object", required = true) @Valid @RequestBody User user) {
        userService.updateUser(username, user);
    }

}
