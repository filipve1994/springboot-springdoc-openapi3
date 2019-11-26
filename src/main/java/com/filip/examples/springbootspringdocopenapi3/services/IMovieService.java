package com.filip.examples.springbootspringdocopenapi3.services;

import com.filip.examples.springbootspringdocopenapi3.models.Movie;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMovieService {

    Movie get(String name);

    void add(Movie movie);

    void addAll(List<Movie> movies);

    List<Movie> getAll();
}
