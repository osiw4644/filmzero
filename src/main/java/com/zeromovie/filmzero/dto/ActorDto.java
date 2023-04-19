package com.zeromovie.filmzero.dto;

import com.zeromovie.filmzero.entity.Actors;
import com.zeromovie.filmzero.entity.MovieInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class ActorDto {

   private String actorNm;
   private String castNm;
   private MovieInfo movieInfo;

   public static ActorDto toActorDto(Actors actors) {
      ActorDto actorDto = new ActorDto();
      actorDto.setActorNm(actors.getActorNm());
      actorDto.setCastNm(actors.getCastNm());
      actorDto.setMovieInfo(actors.getMovieInfo());
      return actorDto;
   }

}
