package com.zeromovie.filmzero.entity;

import com.zeromovie.filmzero.dto.ActorDto;
import com.zeromovie.filmzero.dto.MovieDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "actors")
@Getter @Setter @ToString
public class Actors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Long id;

    private String actorNm; // 배우이름
    private String castNm; // 배역이름



    @ManyToOne
    @JoinColumn(name = "movieInfo_id")
    private MovieInfo movieInfo; // 영화코드

    public static Actors toSaveActors (ActorDto actorDto) {
        Actors actors = new Actors();
        actors.setActorNm(actorDto.getActorNm());
        actors.setCastNm(actorDto.getCastNm());
        actors.setMovieInfo(actorDto.getMovieInfo());
        return actors;
    }
}
