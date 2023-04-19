package com.zeromovie.filmzero.dto;

import com.zeromovie.filmzero.entity.Actors;
import com.zeromovie.filmzero.entity.MovieInfo;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

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
    private float star_avg; // 별점 평균
    private int star_person;// 해당 콘텐츠 평가 인원수
    private String movieImg_main; // 영화이미지 섬네일
    private int audiAcc;// 누적관객
    private String salesAcc; // 누적매출액

    private String movieBackground;

    //public static ModelMapper modelMapper = new ModelMapper();
    //public static MovieDto of(Optional<MovieInfo> movieInfo){return modelMapper.map(movieInfo, MovieDto.class);}



    public static MovieDto toMovieDto(MovieInfo movieInfo) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movieInfo.getId());
        movieDto.setMovieCd(movieInfo.getMovieCd());
        movieDto.setMovieNm(movieInfo.getMovieNm());
        movieDto.setMovieNmEn(movieInfo.getMovieNmEn());
        movieDto.setShowTm(movieInfo.getShowTm());
        movieDto.setOpenDt(movieInfo.getOpenDt());
        movieDto.setNationNm(movieInfo.getNationNm());
        movieDto.setGenreNm(movieInfo.getGenreNm());
        movieDto.setDirectorNm(movieInfo.getDirectorNm());
        movieDto.setWatchGrade(movieInfo.getWatchGrade());
        movieDto.setStoryInfo(movieInfo.getStoryInfo());
        movieDto.setStar_avg(movieInfo.getStar_avg());
        movieDto.setStar_person(movieInfo.getStar_person());
        movieDto.setMovieImg_main(movieInfo.getMovieImg_main());
        movieDto.setAudiAcc(movieInfo.getAudiAcc());
        movieDto.setSalesAcc(movieInfo.getSalesAcc());
        movieDto.setMovieBackground(movieInfo.getMovieBackground());
        return movieDto;
    }
}
