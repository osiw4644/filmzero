package com.zeromovie.filmzero.entity;

import com.zeromovie.filmzero.dto.MovieDto;
import com.zeromovie.filmzero.dto.NoticeFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter @Setter
@ToString
@Table(name = "movieInfo")
public class MovieInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movieInfo_id")
    @NotNull
    private Long id;

    private String movieCd; // 영화코드
    private String movieNm; // 영화제목(국문)
    private String movieNmEn; // 영화이름(영문)
    private String showTm; // 상영시간
    private String openDt; // 개봉연도
    private String nationNm; // 제작국가명
    private String genreNm; // 장르명
    private String directorNm; // 감독명
    private String watchGrade; // 상영등급
    private String storyInfo; // 영화간단한정보URL
    private float star_avg; // 별점평균
    private int star_person;// 해당 콘텐츠 평가 인원수
    private String movieImg_main; // 영화이미지 섬네일
    private int audiAcc;// 누적관객
    private String salesAcc; // 누적매출액
    @OneToMany
    private List<Actors> actorsList; // 배우
    @OneToMany
    private List<MovieImg> movieImgList; // 스틸컷

    private String movieBackground; // 영화배경화면


    public static MovieInfo toSaveMovie (MovieDto movieDto) {
        MovieInfo movieInfo = new MovieInfo();
        movieInfo.setId(movieDto.getId());
        movieInfo.setMovieCd(movieDto.getMovieCd());
        movieInfo.setMovieNm(movieDto.getMovieNm());
        movieInfo.setMovieNmEn(movieDto.getMovieNmEn());
        movieInfo.setShowTm(movieDto.getShowTm());
        movieInfo.setOpenDt(movieDto.getOpenDt());
        movieInfo.setNationNm(movieDto.getNationNm());
        movieInfo.setGenreNm(movieDto.getGenreNm());
        movieInfo.setDirectorNm(movieDto.getDirectorNm());
        movieInfo.setWatchGrade(movieDto.getWatchGrade());
        movieInfo.setStoryInfo(movieDto.getStoryInfo());
        movieInfo.setStar_avg(movieDto.getStar_avg());
        movieInfo.setStar_person(movieDto.getStar_person());
        movieInfo.setMovieImg_main(movieDto.getMovieImg_main());
        movieInfo.setAudiAcc(movieDto.getAudiAcc());
        movieInfo.setSalesAcc(movieDto.getSalesAcc());
        movieInfo.setMovieBackground(movieInfo.getMovieBackground());
        return movieInfo;
    }



}


