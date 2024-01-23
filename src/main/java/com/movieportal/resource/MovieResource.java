package com.movieportal.resource;

import com.movieportal.dto.ActorDto;
import com.movieportal.dto.MovieDto;
import com.movieportal.exception.ResourceNotFound;
import com.movieportal.service.MovieService;
import org.apache.catalina.LifecycleState;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieResource {

    private MovieService movieService;

    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(value = "/movie",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDto> saveMovie(@RequestBody MovieDto movieDto){
        return movieService.addNewMovie(movieDto);
    }
    @GetMapping(value = "/movies")
    public ResponseEntity loadAllMovies(){
        return movieService.getAllMovies();
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteMovieById(@PathVariable("id")Long id){
        movieService.deleteMovieById(id);
    }
    @DeleteMapping(value = "deleteByName/{name}")
    public void deleteMovieByName(@PathVariable("name")String name) throws ResourceNotFound {
        movieService.deleteMovieByName(name);
    }
    @DeleteMapping(value = "/deleteAll")
    public void deleteMovies() throws ResourceNotFound {
        movieService.deleteAllMovies();
    }

}
