package com.movieportal.mapper;

import com.movieportal.dto.ActorDto;
import com.movieportal.entity.Actor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.List;

public class ActorMapper implements EntityMapper<ActorDto, Actor>{

    ModelMapper modelMapper=new ModelMapper();
    @Override
    public ActorDto convertToDto(Actor entity) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(entity,ActorDto.class);
    }

    @Override
    public Actor convertToEntity(ActorDto dto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(dto,Actor.class);
    }

    @Override
    public List<Actor> convertToEntity(List<ActorDto> dtoList) {
        List<Actor>actors=new ArrayList<>();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        dtoList.forEach(actorDto -> {
            actors.add(modelMapper.map(actorDto,Actor.class));
        });
        return actors;
    }

    @Override
    public List<ActorDto> convertToDto(List<Actor> entityList) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        List<ActorDto>actorDtos=new ArrayList<>();
        entityList.forEach(actor -> {
            actorDtos.add(modelMapper.map(actor,ActorDto.class));
        });
        return actorDtos;
    }
}
