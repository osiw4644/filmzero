package com.zeromovie.filmzero.repository;

import com.zeromovie.filmzero.entity.MovieInfo;

import java.util.List;

public interface movieRankRepository {
    List<MovieInfo> getMovieRank();
}
