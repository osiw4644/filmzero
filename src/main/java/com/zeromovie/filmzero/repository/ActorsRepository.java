package com.zeromovie.filmzero.repository;

import com.zeromovie.filmzero.entity.Actors;
import com.zeromovie.filmzero.entity.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorsRepository extends JpaRepository<Actors, Long>
{

    List<Actors> findByMovieInfoId(Long movieInfoId);
}
