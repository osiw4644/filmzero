package com.zeromovie.filmzero.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static com.zeromovie.filmzero.entity.QMovieInfo.movieInfo;

public class MoviePersonRepositoryImpl implements MoviePersonRepository{
    private JPAQueryFactory query;
    public  MoviePersonRepositoryImpl(EntityManager em) {this.query = new JPAQueryFactory(em);}

    @Override
    public QueryResults<Integer> getStarPerson() {
        JPAQuery<Integer> result = query
               .select(movieInfo.star_person.sum())
                .from(movieInfo);
       QueryResults<Integer> movieInfoList = result.fetchResults();

        return movieInfoList;
    }
}
