package com.zeromovie.filmzero.entity;

import com.zeromovie.filmzero.dto.MemberFormDto;
import com.zeromovie.filmzero.dto.MovieCommentDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="movie_comment_table")
public class MovieComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String movieCommentWriter;  //textarea 내용들

    @Column
    private String movieNickname;    //nickname들

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static MovieComment toSaveEntity(MemberFormDto memberFormDto, MovieCommentDto movieCommentDto) {
        MovieComment movieComment = new MovieComment();
        movieComment.setMovieCommentWriter(movieCommentDto.getMovieCommentWriter());    //textarea 내용들
        movieComment.setMovieNickname(memberFormDto.getNickname());    //nickname
        Member member = new Member();
        member.setId(memberFormDto.getId());
        movieComment.setMember(member);
        return movieComment;
    }

//    public static MovieComment toSaveEntity(MemberFormDto memberFormDto, MovieCommentDto movieCommentDto) {
//        MovieComment movieComment = new MovieComment();
//        movieComment.setId(memberFormDto.getId());   //id
//        movieComment.setMovieCommentWriter(movieCommentDto.getMovieCommentWriter());    //textarea 내용들
//        movieComment.setMovieNickname(memberFormDto.getNickname());    //nickname
//        return movieComment;
//    }
//    public static MovieComment membertoSave(Long id){
//        MovieComment moviecomment = new MovieComment();
//        moviecomment.setId(id);
//        return moviecomment;
//
//    }
}









