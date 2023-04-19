package com.zeromovie.filmzero.service;

import com.querydsl.core.QueryResults;
import com.zeromovie.filmzero.dto.MemberFormDto;
import com.zeromovie.filmzero.dto.MovieCommentDto;
import com.zeromovie.filmzero.dto.MovieDto;
import com.zeromovie.filmzero.dto.NoticeFormDto;
import com.zeromovie.filmzero.entity.Member;
import com.zeromovie.filmzero.entity.MovieComment;
import com.zeromovie.filmzero.entity.MovieInfo;
import com.zeromovie.filmzero.entity.Notice;
import com.zeromovie.filmzero.repository.MovieCommentRepository;
import com.zeromovie.filmzero.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieCommentRepository movieCommentRepository;

    @Transactional
    public List<MovieDto> findAll() {
        List<MovieInfo> movieInfoList = movieRepository.findAll();
        List<MovieDto> movieDtoList = new ArrayList<>();
        for (MovieInfo movieInfo: movieInfoList) {
            movieDtoList.add(MovieDto.toMovieDto(movieInfo));
        }
        return movieDtoList;
    }

    @Transactional
    public MovieDto findById(Long id) {
        Optional<MovieInfo> optionalMovieInfo = movieRepository.findById(id);
        if (optionalMovieInfo.isPresent()) {
            MovieInfo movieInfo = optionalMovieInfo.get();
            MovieDto movieDto = MovieDto.toMovieDto(movieInfo);
            return movieDto;
        } else {
            return null;
        }
    }


    public Page<MovieDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 15; // 한 페이지에 보여줄 글 갯수
        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<MovieInfo> movieInfos =
                movieRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        System.out.println("movieInfos.getContent() = " + movieInfos.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("movieInfos.getTotalElements() = " + movieInfos.getTotalElements()); // 전체 글갯수
        System.out.println("movieInfos.getNumber() = " + movieInfos.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("movieInfos.getTotalPages() = " + movieInfos.getTotalPages()); // 전체 페이지 갯수
        System.out.println("movieInfos.getSize() = " + movieInfos.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("movieInfos.hasPrevious() = " + movieInfos.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("movieInfos.isFirst() = " + movieInfos.isFirst()); // 첫 페이지 여부
        System.out.println("movieInfos.isLast() = " + movieInfos.isLast()); // 마지막 페이지 여부

        // 목록: id, writer, title, hits, createdTime
        Page<MovieDto> movieDtos = movieInfos.map(movieInfo -> new MovieDto(
                movieInfo.getId(), movieInfo.getMovieCd(),
                movieInfo.getMovieNm(), movieInfo.getMovieNmEn(),
                movieInfo.getShowTm(), movieInfo.getOpenDt(),
                movieInfo.getNationNm(), movieInfo.getGenreNm(),
                movieInfo.getDirectorNm(), movieInfo.getWatchGrade(),
                movieInfo.getStoryInfo(), movieInfo.getStar_avg(),
                movieInfo.getStar_person(), movieInfo.getMovieImg_main(),
                movieInfo.getAudiAcc(), movieInfo.getSalesAcc(), movieInfo.getMovieBackground()));
        return movieDtos;
    }

    @Transactional(readOnly = true) // 오류가 발생한 경우 오류 발생 이전 상태로 돌리는 애너테이션
    public List<MovieInfo> movieRank() { // 메인출력되는 무비차트(전달~이번달 영화 중 누적관객 내림차순 10개)
        return movieRepository.getMovieRank();
    }

    @Transactional(readOnly = true)
    public List<MovieInfo> romanceCollection() { // 멜로
        return movieRepository.getRomanceCollection();
    }

    @Transactional(readOnly = true)
    public List<MovieInfo> AniCollection() { // 애니메이션
        return movieRepository.getAniCollection();
    }

    @Transactional(readOnly = true)
    public List<MovieInfo> StarCollection() { // 별점높은순
        return movieRepository.getStarCollection();
    }

    @Transactional(readOnly = true)
    public List<MovieInfo> AudiCollection() { // 누적관객 많은순
        return movieRepository.getAudiCollection();
    }

    @Transactional(readOnly = true)
    public List<MovieInfo> ThrillerCollection() { // 스릴러
        return movieRepository.getThrillerCollection();
    }

    @Transactional(readOnly = true)
    public List<MovieInfo> HorrorCollection() { // 공포
        return movieRepository.getHorrorCollection();
    }

    @Transactional(readOnly = true)
    public List<MovieInfo> ActionCollection() { // 액션
        return movieRepository.getActionCollection();
    }

    @Transactional(readOnly = true)
    public List<MovieInfo> CrimeCollection() { // 범죄
        return movieRepository.getCrimeCollection();
    }

    @Transactional(readOnly = true)
    public List<MovieInfo> DocumentaryCollection() { // 다큐
        return movieRepository.getDocumentaryCollection();
    }

//    @Transactional(readOnly = true)
//    public QueryResults<Integer> starPerson() {return movieRepository.getStarPerson();}

    public Page<MovieCommentDto> pageng(Pageable pageable) {   //코멘트 출력부분
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 15; // 한 페이지에 보여줄 글 갯수
        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<MovieComment> movieComments =
                movieCommentRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        Page<MovieCommentDto> MovieCommentDtos = movieComments.map(movieComment -> new MovieCommentDto(
                movieComment.getId(),
                movieComment.getMovieNickname(),
                movieComment.getMovieCommentWriter()));
        return MovieCommentDtos;
    }

    public void save(MemberFormDto memberFormDto, MovieCommentDto movieCommentDto) {    //코멘트에 세이브
//        MovieComment movieComment = movieCommentDto.setMovieNickname(memberFormDto);  //movieCommentDto  setMovieNickname에 (Nickname)를 담아주겠다.
        MovieComment movieComment = MovieComment.toSaveEntity(memberFormDto, movieCommentDto);
        movieCommentRepository.save(movieComment);  //Repository로 MovieComment에(MovieCommentDto) 넣어서 저장하겠다.
    }
    public Page<MovieDto> pageSub(Pageable pageable) { //admin페이지에서 필요한 page
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 5; // 한 페이지에 보여줄 글 갯수
        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<MovieInfo> movieInfos =
                movieRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        System.out.println("movieInfos.getContent() = " + movieInfos.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("movieInfos.getTotalElements() = " + movieInfos.getTotalElements()); // 전체 글갯수
        System.out.println("movieInfos.getNumber() = " + movieInfos.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("movieInfos.getTotalPages() = " + movieInfos.getTotalPages()); // 전체 페이지 갯수
        System.out.println("movieInfos.getSize() = " + movieInfos.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("movieInfos.hasPrevious() = " + movieInfos.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("movieInfos.isFirst() = " + movieInfos.isFirst()); // 첫 페이지 여부
        System.out.println("movieInfos.isLast() = " + movieInfos.isLast()); // 마지막 페이지 여부

        Page<MovieDto> movieDtos = movieInfos.map(movieInfo -> new MovieDto(
                movieInfo.getId(),
                movieInfo.getMovieCd(),
                movieInfo.getMovieNm(),
                movieInfo.getMovieNmEn(),
                movieInfo.getShowTm(),
                movieInfo.getOpenDt(),
                movieInfo.getNationNm(),
                movieInfo.getGenreNm(),
                movieInfo.getDirectorNm(),
                movieInfo.getWatchGrade(),
                movieInfo.getStoryInfo(),
                movieInfo.getStar_avg(),
                movieInfo.getStar_person(),
                movieInfo.getMovieImg_main(),
                movieInfo.getAudiAcc(),
                movieInfo.getSalesAcc(),
                movieInfo.getMovieBackground()));

        return movieDtos;
    }

    }
