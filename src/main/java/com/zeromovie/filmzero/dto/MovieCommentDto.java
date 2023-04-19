package com.zeromovie.filmzero.dto;

import com.zeromovie.filmzero.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter@Setter
public class MovieCommentDto {

    private Long id;
    private String movieCommentWriter;

    private String movieNickname;
    private Long BoardId;

    public MovieCommentDto(Long id, String movieCommentWriter, String movieNickname) {
        this.id = id;
        this.movieCommentWriter = movieCommentWriter;
        this.movieNickname = movieNickname;
    }
}