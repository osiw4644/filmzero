package com.zeromovie.filmzero.control;

import com.querydsl.core.QueryResults;
import com.zeromovie.filmzero.dto.BoardDTO;
import com.zeromovie.filmzero.dto.MemberFormDto;
import com.zeromovie.filmzero.dto.MovieDto;
import com.zeromovie.filmzero.dto.NoticeFormDto;
import com.zeromovie.filmzero.entity.MovieInfo;
import com.zeromovie.filmzero.service.BoardService;
import com.zeromovie.filmzero.service.MemberService;
import com.zeromovie.filmzero.service.MovieService;
import com.zeromovie.filmzero.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class MainController {

    private final NoticeService noticeService;
    private final BoardService boardService;
    private final MemberService mservice;
    private final MovieService movieService;
    @GetMapping("/admin") // 관리자메인페이지
    public String admin(@PageableDefault(page = 1) Pageable pageable, Model model){
        pageable.getPageNumber();
        Page<NoticeFormDto> noticeList = noticeService.pageSub(pageable);
        Page<BoardDTO> boardList = boardService.pageSub(pageable);
        Page<MemberFormDto> memberList = mservice.pageSub(pageable);
        Page<MovieDto> movieList = movieService.pageSub(pageable);

        int blockLimit =5;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit ))) -1) * blockLimit +1;
        int endPage = ((startPage + blockLimit -1) <noticeList.getTotalPages())? startPage + blockLimit - 1 : noticeList.getTotalPages();

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage",endPage);

        int boardStartPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit ))) -1) * blockLimit +1;
        int boardEndPage = ((boardStartPage + blockLimit -1) <boardList.getTotalPages())? boardStartPage + blockLimit - 1 : boardList.getTotalPages();

        model.addAttribute("boardList", boardList);
        model.addAttribute("boardStartPage", boardStartPage);
        model.addAttribute("boardEndPage",boardEndPage);

        int memberStartPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit ))) -1) * blockLimit +1;
        int memberEndPage = ((boardStartPage + blockLimit -1) <memberList.getTotalPages())? boardStartPage + blockLimit - 1 : boardList.getTotalPages();

        model.addAttribute("memberList", memberList);
        model.addAttribute("memberStartPage", memberStartPage);
        model.addAttribute("memberEndPage", memberEndPage);


        int movieStartPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit ))) -1) * blockLimit +1;
        int movieEndPage = ((movieStartPage + blockLimit -1) <movieList.getTotalPages())? movieStartPage + blockLimit - 1 : movieList.getTotalPages();

        model.addAttribute("movieList", movieList);
        model.addAttribute("movieStartPage", movieStartPage);
        model.addAttribute("movieEndPage", movieEndPage);

        return "/admin/admin";
    }

    @GetMapping("/main")
    public String main(Principal principal, HttpServletRequest request, @PageableDefault(page = 1) Pageable pageable, Model model){
        HttpSession session = request.getSession();
        if(principal != null) {
            System.out.println("aa");
            String img = mservice.getImg(principal.getName());
            if(img ==null) return "main";
            session.setAttribute("img", img);
            System.out.println(img);
        }else if(session.getAttribute("img") !=null){
            System.out.println("asss");
            session.removeAttribute("img");
        }
        pageable.getPageNumber();
        Page<NoticeFormDto> noticeList = noticeService.pageSub(pageable);
        int blockLimit =5;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit ))) -1) * blockLimit +1;
        int endPage = ((startPage + blockLimit -1) <noticeList.getTotalPages())? startPage + blockLimit - 1 : noticeList.getTotalPages();

        List<MovieInfo> movieList = movieService.movieRank();

        System.out.println(movieList.get(0).getMovieNm());
        model.addAttribute("movieList", movieList);

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage",endPage);

        return "/main";
    }
    @GetMapping("/")
    public String home(){return "intro";}

    @GetMapping("/event/game")
    public String eventgames(){
        return "game";
    }
}