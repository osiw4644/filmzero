package com.zeromovie.filmzero.control;


import com.zeromovie.filmzero.dto.BoardDTO;
import com.zeromovie.filmzero.dto.CommentDTO;
import com.zeromovie.filmzero.dto.MemberFormDto;
import com.zeromovie.filmzero.service.BoardService;
import com.zeromovie.filmzero.service.CommentService;
import com.zeromovie.filmzero.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final MemberService mservice;

    @GetMapping("/inquiry")
    public String QandA(){;
        return "/inquiry";
    }
    @GetMapping("/save")
    public String saveForm(){


        return "/userCSwrite";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO, BindingResult br, Principal principal) throws IOException {
        String email = principal.getName();
        MemberFormDto memberFormDto = mservice.memberlist(email);

        System.out.println("boardDTO =" + boardDTO);
        boardService.save(boardDTO, memberFormDto.getNickname());
        return "redirect:/board/paging";
    }


    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page = 1) Pageable pageable){   //주소  board/1  에서  1을가져오기위해서 @PathVariable을 사용한다.
        //// 주소를 다른 말로 경로 라고 하며  경로는 영어로 path 이고  경로에 있는 값(variable)ㅇㄹ 가져와야 하므로 Pathvariable 이라는 애너테이션을 사용한다.
        /* 조회수올리고 게시글 데이터를 가져와서 detail에출력*/
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);//현재 id에 해당하는 글 번호의 전체 글 내용
        List<CommentDTO> commentDTOList = commentService.findAll(id); //  현재 글의 댓글 전체 가져오기
        System.out.println(boardDTO.getBoardWriter()+"작성자");
        model.addAttribute("board",boardDTO);
        model.addAttribute("commentList",commentDTOList);
        model.addAttribute("page", pageable.getPageNumber());
        return "userCSBoard";
    }
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate",boardDTO);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board",board);
//            return "detail";
        return "redirect:/board/paging";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        boardService.delete(id);
        return "redirect:/board/paging";
    }

    @GetMapping(value = {"/paging","/paging/{page}"})
    public String paging(@PathVariable("page") Optional<Integer> page, Model model){
        // pageable.getPageNumber();
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0 , 10);
        Page<BoardDTO> boardList = boardService.paging(pageable);

        model.addAttribute("boardList", boardList);
        model.addAttribute("maxPage",10);
        return "userCScheck";
    }



}