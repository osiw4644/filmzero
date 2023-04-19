package com.zeromovie.filmzero.service;
import com.zeromovie.filmzero.dto.BoardDTO;
import com.zeromovie.filmzero.dto.CommentDTO;
import com.zeromovie.filmzero.entity.BoardEntity;
import com.zeromovie.filmzero.entity.CommentEntity;
import com.zeromovie.filmzero.repository.BoardRepository;
import com.zeromovie.filmzero.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Long save(CommentDTO commentDTO) {
        /* 부모엔티티(BoardEntity) 조회 */
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);
            return commentRepository.save(commentEntity).getId();
        } else {
            return null;
        }
    }

    public List<CommentDTO> findAll(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
        /* EntityList -> DTOList */
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity ,boardId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }


//    @Transactional
//    public List<CommentDTO> commentAll(Long id) {
//        Optional<CommentEntity> optionalCommentEntity = commentRepository.findById(id);
//        if (optionalCommentEntity.isPresent()) {
//            CommentEntity commentEntity = optionalCommentEntity.get();
//            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity);
//
//        }
//
//
//
//        List<CommentEntity> commentEntityList = commentRepository.findById();
//        List<CommentDTO> commentDTOList = new ArrayList<>();
//        for (CommentEntity commentEntity : commentEntityList) {
//            commentDTOList.add(CommentDTO.toCommentDTO(commentEntity));
//        }
//        return commentDTOList;
//    }
}