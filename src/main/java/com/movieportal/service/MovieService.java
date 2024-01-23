package com.movieportal.service;

import com.movieportal.dto.ActorDto;
import com.movieportal.dto.MovieDto;
import com.movieportal.entity.Actor;
import com.movieportal.entity.Movie;
import com.movieportal.entity.MovieDetails;
import com.movieportal.exception.ResourceNotFound;
import com.movieportal.mapper.ActorMapper;
import com.movieportal.mapper.MovieMapper;
import com.movieportal.persistence.ActorRepository;
import com.movieportal.persistence.MovieDetailsRepository;
import com.movieportal.persistence.MovieRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    MovieMapper movieMapper=new MovieMapper();
    ActorMapper actorMapper=new ActorMapper();
    private final MovieRepository movieRepository;
    private final MovieDetailsRepository movieDetailsRepository;
    private final ActorRepository actorRepository;

    public MovieService(MovieRepository movieRepository, MovieDetailsRepository movieDetailsRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.movieDetailsRepository = movieDetailsRepository;
        this.actorRepository = actorRepository;
    }


    public ResponseEntity<MovieDto> addNewMovie(MovieDto movieDto){

        List<Movie>movies=movieRepository.findAll();
        List<MovieDto>movieDtos=movieMapper.convertToDto(movies);
       for (MovieDto movieDto_:movieDtos){
           if (movieDto.getMovieName().equalsIgnoreCase(movieDto_.getMovieName())){
             // return new ResponseEntity(HttpStatus.FOUND);
           }
       }

        MovieDetails movieDetails=new MovieDetails();
        movieDetails.setCountry(movieDto.getCountry());
        movieDetails.setDirector(movieDto.getDirector());
        movieDetails.setLanguage(movieDto.getLanguage());
        movieDetails.setProducer(movieDto.getProducer());
        movieDetails.setRunnigTime(movieDto.getRunningTime());


       List<ActorDto>actorDtoList=movieDto.getActors();


       movieDto.setActors(actorDtoList);
        Movie movie=movieMapper.convertToEntity(movieDto);
        List<Actor>actors=actorMapper.convertToEntity(actorDtoList);

        movieDetails.setMovie(movie);



        movie.setStarring(actors);




       // actorRepository.saveAll(actors);
        movie.setMovieDetails(movieDetails);


        movieRepository.save(movie);

        return new ResponseEntity<>(movieDto,HttpStatus.CREATED);


    }


    public ResponseEntity <List<MovieDto>> getAllMovies(){
        List<Movie>movies=movieRepository.findAll();
        if (movies.isEmpty()){
              ResponseEntity.status(HttpStatus.NO_CONTENT);
        }
        List<MovieDto>movieDtos=movieMapper.convertToDto(movies);
        return new ResponseEntity<>(movieDtos,HttpStatus.FOUND);
    }
    public void deleteMovieById(Long id){
        movieRepository.deleteById(id);
    }
    //delete Movie By Name
    public void deleteMovieByName(String movieName)throws ResourceNotFound{
        movieRepository.findByMovieName(movieName).map(movie -> {
            movieRepository.delete(movie);
            return "Object deleted";
        }).orElseThrow(()->new ResourceNotFoundException("Object not found"));

    }
    // delete all movies
   public void deleteAllMovies()throws ResourceNotFound{
        List<Movie>movies=movieRepository.findAll();
        if (movies.isEmpty()) {
            new ResourceNotFound("List is Empty");
        }
        movieRepository.deleteAll();
   }
    //change/update movie's Details
}
