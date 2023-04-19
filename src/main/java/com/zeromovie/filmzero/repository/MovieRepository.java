package com.zeromovie.filmzero.repository;

import com.zeromovie.filmzero.entity.MovieInfo;
import com.zeromovie.filmzero.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieInfo, Long>,
        QuerydslPredicateExecutor<MovieInfo>, movieRankRepository,MovieCollectionRepository, MoviePersonRepository { }

