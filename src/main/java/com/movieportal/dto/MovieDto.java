package com.movieportal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movieportal.entity.Actor;
import com.movieportal.entity.Genre;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MovieDto {

    private String movieName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private double price;
    private String director;
    private String producer;
    private String runningTime;
    private String country;
    private String language;
    private List<ActorDto>actors;
}
