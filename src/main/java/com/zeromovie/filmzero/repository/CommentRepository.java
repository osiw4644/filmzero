package com.zeromovie.filmzero.repository;

import com.zeromovie.filmzero.entity.BoardEntity;
import com.zeromovie.filmzero.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<CommentEntity, Long> {


    List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);


    List<CommentEntity> findById(CommentEntity commentEntity);



    List<CommentEntity> findAllById(CommentEntity commentEntity);
}
