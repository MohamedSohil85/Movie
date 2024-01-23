package com.movieportal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movieId;
    private String movieName;
    @JsonFormat(pattern = "dd/MM/YYYY")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String runningTime;
    private double price;
    @OneToOne(mappedBy = "movie",cascade = CascadeType.ALL)
    private MovieDetails movieDetails;
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Actor> starring;
}
