package com.zeromovie.filmzero.control;

import com.zeromovie.filmzero.dto.*;
import com.zeromovie.filmzero.entity.Actors;
import com.zeromovie.filmzero.entity.MovieInfo;
import com.zeromovie.filmzero.service.ActorsService;
import com.zeromovie.filmzero.service.MemberService;
import com.zeromovie.filmzero.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final ActorsService actorsService;

    private final MemberService memberService;

    @GetMapping("/do")
    public String paging(@PageableDefault(page = 1)Pageable pageable, Model model){
        pageable.getPageNumber();
        Page<MovieDto> movieList = movieService.paging(pageable);
        int blockLimit =15;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit ))) -1) * blockLimit +1;
        int endPage = ((startPage + blockLimit -1) <movieList.getTotalPages())? startPage + blockLimit - 1 : movieList.getTotalPages();

        System.out.println(movieList.getContent().get(0).getId());

        model.addAttribute("movieList", movieList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage",endPage);

        return "/moviePaging";
    }

    @GetMapping("/collection")
    public String collection() {
        return "/collection";
    }


    @GetMapping("/de/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page = 1) Pageable pageable){
        /* 조회수올리고 게시글 데이터를 가져와서 detail에출력*/
        MovieDto movieDto = movieService.findById(id);
        List<Actors> actor = actorsService.findById(id);

        pageable.getPageNumber();
        Page<MovieDto> movieList = movieService.paging(pageable);
        int blockLimit = 15;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < movieList.getTotalPages()) ? startPage + blockLimit - 1 : movieList.getTotalPages();
        model.addAttribute("actorList", actor);
        model.addAttribute("movieList",movieDto);
        model.addAttribute("page", pageable.getPageNumber());


        model.addAttribute("movieList", movieDto);
        model.addAttribute("page", pageable.getPageNumber());

        pageable.getPageNumber();
        Page<MovieCommentDto> movieCommentList = movieService.pageng(pageable);
//        Page<MovieCommentDto> ActorsList = movieService.pageng(pageable);

        int moviestartPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int movieendPage = ((moviestartPage + blockLimit - 1) < movieCommentList.getTotalPages()) ? moviestartPage + blockLimit - 1 : movieCommentList.getTotalPages();

        model.addAttribute("movieCommentList", movieCommentList);
        model.addAttribute("startPage", moviestartPage);
        model.addAttribute("endPage", movieendPage);

        return "/movieInfo";
    }

    @GetMapping("/movieMore/{id}")
    public String findByMovieMore(@PathVariable Long id, Model model) {
        MovieDto movieDto = movieService.findById(id);
        model.addAttribute("movieList",movieDto);
        return "/movieMore";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MovieCommentDto movieCommentDto, BindingResult br,
                       Principal principal, HttpServletRequest request,Model model) throws IOException {
        String email = principal.getName();
        MemberFormDto memberFormDto = memberService.memberlist(email);
        String referer = request.getHeader("Referer");
        try {
            movieService.save(memberFormDto, movieCommentDto);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("comment error.");
        }
        return "redirect:"+ referer;
    }

    @GetMapping("/comment")
    public String moviecomment(Model model,
                               @PageableDefault(page = 1) Pageable pageable) {

        pageable.getPageNumber();
        Page<MovieCommentDto> movieCommentList = movieService.pageng(pageable);
        int blockLimit = 15;
        int moviestartPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int movieendPage = ((moviestartPage + blockLimit - 1) < movieCommentList.getTotalPages()) ? moviestartPage + blockLimit - 1 : movieCommentList.getTotalPages();
        model.addAttribute("movieCommentList", movieCommentList);
        model.addAttribute("startPage", moviestartPage);
        model.addAttribute("endPage", movieendPage);

        return "/comment";
    }

}
