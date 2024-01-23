package com.movieportal.mapper;

import com.movieportal.dto.MovieDto;
import com.movieportal.entity.Movie;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.List;

public class MovieMapper implements EntityMapper<MovieDto, Movie> {
    ModelMapper modelMapper=new ModelMapper();

    @Override
    public MovieDto convertToDto(Movie entity) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(entity,MovieDto.class);
    }

    @Override
    public Movie convertToEntity(MovieDto dto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper.map(dto,Movie.class);
    }

    @Override
    public List<Movie> convertToEntity(List<MovieDto> dtoList) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        List<Movie>movies=new ArrayList<>();
        dtoList.forEach(movieDto -> {
            movies.add(modelMapper.map(movieDto,Movie.class));
        });


        return movies;
    }

    @Override
    public List<MovieDto> convertToDto(List<Movie> entityList) {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        List<MovieDto>dtoList=new ArrayList<>();
        entityList.forEach(entity->{
            dtoList.add(modelMapper.map(entity,MovieDto.class));
        });
        return dtoList;
    }
}
