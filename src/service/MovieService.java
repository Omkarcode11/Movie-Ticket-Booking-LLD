package service;
import exceptions.NotFoundException;
import java.util.*;
import  model.Movie;

public class MovieService {
    private final Map<String, Movie> movies;
    
    public MovieService(){
        this.movies = new HashMap<>();
    }

    public Movie getMovie(String movieId) throws NotFoundException {
        if(!movies.containsKey(movieId)){
            throw new NotFoundException("Movie not found");
        }
        return movies.get(movieId);
    }

    public Movie createMovie(String movieName){
        String movieId = UUID.randomUUID().toString();
        Movie movie = new Movie(movieId, movieName);
        movies.put(movieId, movie);
        return movie;
    }
}
