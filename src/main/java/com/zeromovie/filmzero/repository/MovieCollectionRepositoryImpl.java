package com.zeromovie.filmzero.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zeromovie.filmzero.entity.MovieInfo;
import com.zeromovie.filmzero.entity.QMovieInfo;

import javax.persistence.EntityManager;
import java.util.List;

import static com.zeromovie.filmzero.entity.QMovieInfo.movieInfo;

public class MovieCollectionRepositoryImpl implements MovieCollectionRepository {
    private JPAQueryFactory query;
    public  MovieCollectionRepositoryImpl(EntityManager em) {this.query = new JPAQueryFactory(em);}

    @Override
    public List<MovieInfo> getRomanceCollection() { //멜로
        QueryResults<MovieInfo> results = query
                .selectFrom((movieInfo))
                .where(movieInfo.genreNm.like("%멜로%"))
                .orderBy(QMovieInfo.movieInfo.audiAcc.desc())
                .limit(16)
                .fetchResults();
        List<MovieInfo> movieInfoList = results.getResults();
        return movieInfoList;
    }

    @Override
    public List<MovieInfo> getAniCollection() { //애니
        QueryResults<MovieInfo> results = query
                .selectFrom((movieInfo))
                .where(movieInfo.genreNm.like("%애니%"))
                .orderBy(QMovieInfo.movieInfo.audiAcc.desc())
                .limit(16)
                .fetchResults();
        List<MovieInfo> movieInfoList = results.getResults();
        return movieInfoList;
    }

    @Override
    public List<MovieInfo> getStarCollection() { //별점높은순
        QueryResults<MovieInfo> results = query
                .selectFrom((movieInfo))
                .orderBy(QMovieInfo.movieInfo.star_avg.desc())
                .limit(16)
                .fetchResults();
        List<MovieInfo> movieInfoList = results.getResults();
        return movieInfoList;
    }

    @Override
    public List<MovieInfo> getAudiCollection() { // 누적관객
        QueryResults<MovieInfo> results = query
                .selectFrom((movieInfo))
                .orderBy(QMovieInfo.movieInfo.audiAcc.desc())
                .limit(16)
                .fetchResults();
        List<MovieInfo> movieInfoList = results.getResults();
        return movieInfoList;
    }

    @Override
    public List<MovieInfo> getThrillerCollection() { //스릴러
        QueryResults<MovieInfo> results = query
                .selectFrom((movieInfo))
                .where(movieInfo.genreNm.like("%스릴러%"))
                .orderBy(QMovieInfo.movieInfo.audiAcc.desc())
                .limit(16)
                .fetchResults();
        List<MovieInfo> movieInfoList = results.getResults();
        return movieInfoList;
    }

    @Override
    public List<MovieInfo> getHorrorCollection() { //호러
        QueryResults<MovieInfo> results = query
                .selectFrom((movieInfo))
                .where(movieInfo.genreNm.like("%공포%"))
                .orderBy(QMovieInfo.movieInfo.audiAcc.desc())
                .limit(16)
                .fetchResults();
        List<MovieInfo> movieInfoList = results.getResults();
        return movieInfoList;
    }

    @Override
    public List<MovieInfo> getActionCollection() { //액션
        QueryResults<MovieInfo> results = query
                .selectFrom((movieInfo))
                .where(movieInfo.genreNm.like("%액션%"))
                .orderBy(QMovieInfo.movieInfo.audiAcc.desc())
                .limit(16)
                .fetchResults();
        List<MovieInfo> movieInfoList = results.getResults();
        return movieInfoList;
    }

    @Override
    public List<MovieInfo> getCrimeCollection() { //범죄
        QueryResults<MovieInfo> results = query
                .selectFrom((movieInfo))
                .where(movieInfo.genreNm.like("%범죄%"))
                .orderBy(QMovieInfo.movieInfo.audiAcc.desc())
                .limit(16)
                .fetchResults();
        List<MovieInfo> movieInfoList = results.getResults();
        return movieInfoList;
    }

    @Override
    public List<MovieInfo> getDocumentaryCollection() { //다큐
        QueryResults<MovieInfo> results = query
                .selectFrom((movieInfo))
                .where(movieInfo.genreNm.like("%다큐%"))
                .orderBy(QMovieInfo.movieInfo.audiAcc.desc())
                .limit(16)
                .fetchResults();
        List<MovieInfo> movieInfoList = results.getResults();
        return movieInfoList;
    }

}
