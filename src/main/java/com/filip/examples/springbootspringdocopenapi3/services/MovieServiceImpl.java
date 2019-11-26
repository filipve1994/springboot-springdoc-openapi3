package com.filip.examples.springbootspringdocopenapi3.services;

import com.filip.examples.springbootspringdocopenapi3.models.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements IMovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    private static List<Movie> moviesData;

    static {
        moviesData = new ArrayList<>();

        Movie m1 = new Movie();
        m1.setId("1");
        m1.setName("MovieABC");
        moviesData.add(m1);

        Movie m2 = new Movie();
        m2.setId("2");
        m2.setName("MovieDEF");
        moviesData.add(m2);

    }


    @Override
    public Movie get(String name) {
        Movie movie = null;
        for (Movie m : moviesData) {
            if (name.equalsIgnoreCase(m.getName())) {
                movie = m;
                logger.info("Found movie with name {} : {} ", name, movie);
            }
        }

        return movie;
    }

    @Override
    public void add(Movie movie) {
        if (get(movie.getName()) == null) {
            moviesData.add(movie);
            logger.info("Added new movie - {}", movie.getName());
        }
    }

    @Override
    public void addAll(List<Movie> movies) {
        for (Movie movie : movies) {
            add(movie);
        }
    }

    @Override
    public List<Movie> getAll() {
        return moviesData;
    }
}
