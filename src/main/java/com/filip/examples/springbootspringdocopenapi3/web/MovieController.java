package com.filip.examples.springbootspringdocopenapi3.web;

import com.filip.examples.springbootspringdocopenapi3.constraints.MaxSizeConstraint;
import com.filip.examples.springbootspringdocopenapi3.models.Movie;
import com.filip.examples.springbootspringdocopenapi3.services.IMovieService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @GetMapping(value = "/")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(movieService.getAll());
    }

    //{"name":"Movie1"},{"name":"Movie2"},{"name":"Movie3"},{"name":"Movie4"},{"name":"Movie5"}
    @PostMapping(value = "/", consumes = {APPLICATION_JSON_VALUE})
    public void addAll(@RequestBody @NotEmpty(message = "Input movie list cannot be empty.") List<@Valid Movie> movies) {
        movieService.addAll(movies);
    }

    //{"name":"Movie1"},{"name":"Movie2"},{"name":"Movie3"},{"name":"Movie4"},{"name":"Movie5"}
    @PostMapping(value = "/2", consumes = {APPLICATION_JSON_VALUE})
    public void addAll2(@RequestBody @NotEmpty(message = "Input movie list cannot be empty.") @MaxSizeConstraint List<@Valid Movie> movies) {
        movieService.addAll(movies);
    }
}
