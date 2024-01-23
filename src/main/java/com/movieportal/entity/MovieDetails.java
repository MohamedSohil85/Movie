package com.movieportal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movieDetailsId;
    private String director;
    private String producer;
    private String runnigTime;
    private String country;
    private String language;
    @JsonIgnore
    @OneToOne
    private Movie movie;
}
