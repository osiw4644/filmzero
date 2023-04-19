package com.zeromovie.filmzero.repository;

import com.querydsl.core.QueryResults;

public interface MoviePersonRepository {
    QueryResults<Integer> getStarPerson();
}
