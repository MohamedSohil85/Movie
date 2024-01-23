package com.movieportal.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.ManyToMany;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActorDto {

    private String actorName;
    private String country;
    @JsonIgnore
    private MovieDto movieDto;


}
