package com.zeromovie.filmzero.repository;

import com.zeromovie.filmzero.entity.MovieInfo;

import java.util.List;

public interface MovieCollectionRepository {
    List<MovieInfo> getRomanceCollection(); // 멜로컬렉션
    List<MovieInfo> getAniCollection(); // 애니메이션컬렉션
    List<MovieInfo> getStarCollection(); // 별점높은컬렉션
    List<MovieInfo> getAudiCollection(); // 누적관객높은컬렉션
    List<MovieInfo> getThrillerCollection(); // 스릴러컬렉션
    List<MovieInfo> getHorrorCollection(); // 호러컬렉션
    List<MovieInfo> getActionCollection(); // 액션컬렉션
    List<MovieInfo> getCrimeCollection(); // 범죄컬렉션
    List<MovieInfo> getDocumentaryCollection(); // 다큐멘터리컬렉션


}
