package com.zeromovie.filmzero.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zeromovie.filmzero.dto.MovieDto;
import com.zeromovie.filmzero.entity.MovieInfo;
import com.zeromovie.filmzero.entity.QMovieInfo;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class movieRankRepositoryImpl implements movieRankRepository{
    private JPAQueryFactory query;
    public movieRankRepositoryImpl(EntityManager em) {this.query = new JPAQueryFactory(em);}


    private BooleanExpression searchByThisMonth() { // 이번 달
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;

        String strYear = Integer.toString(year);
        String strMonth = month<10 ? "0"+Integer.toString(month):Integer.toString(month);

        String thisMonth = (strYear+strMonth+"32");

        System.out.println(thisMonth);

         return QMovieInfo.movieInfo.openDt.lt(thisMonth);

    }

    private BooleanExpression searchByLastMonth() { // 지난 달
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR); // calender로 현재 year(연도)를 가져옴
        int month = calendar.get(Calendar.MONTH)+1; // calender로 현재 month(월)을 가져옴

        int LastMonth = month-1; // 현재 월의 -1 지난달을 가져옴

        if(month == 1) { // 현재 1월달인 경우
            year = calendar.get(Calendar.YEAR)-1; // 현재 연도에서 1년 차감
            LastMonth = 12; // 지난 달을 12월달로 만듦
        }

        String strYear = Integer.toString(year);
        String strLastMonth = LastMonth<10 ? "0"+Integer.toString(LastMonth):Integer.toString(LastMonth);

        String lastMonth = (strYear+strLastMonth+"00");

        System.out.println(lastMonth);

        return QMovieInfo.movieInfo.openDt.goe(lastMonth);

    }


    @Override
    public List<MovieInfo> getMovieRank() {
        QueryResults<MovieInfo> results  = query
                .selectFrom((QMovieInfo.movieInfo))
                .where(searchByLastMonth(),
                        searchByThisMonth())
                .orderBy(QMovieInfo.movieInfo.audiAcc.desc())
                .limit(10).fetchResults();
        List<MovieInfo> movieInfoList = results.getResults();

        System.out.println("movieRankRepositoryImpl");
        System.out.println(movieInfoList.get(0).getMovieNm());
        return movieInfoList;
    }


}
