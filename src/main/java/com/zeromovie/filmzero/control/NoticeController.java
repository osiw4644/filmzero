package com.zeromovie.filmzero.control;

import com.zeromovie.filmzero.dto.NoticeFormDto;
import com.zeromovie.filmzero.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/admin/save")
    public String saveForm(){ return "/admin/noticeSave";}

    @PostMapping("/admin/save")
    public String save(@ModelAttribute NoticeFormDto noticeFormDto) throws IOException {
        System.out.println("noticeFormDto =" + noticeFormDto);
        noticeService.save(noticeFormDto);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page = 1) Pageable pageable){
        /* 조회수올리고 게시글 데이터를 가져와서 detail에출력*/
        noticeService.updateHits(id);
        NoticeFormDto noticeFormDto = noticeService.findById(id);
        model.addAttribute("notice",noticeFormDto);
        model.addAttribute("page", pageable.getPageNumber());
        return "/admin/noticeDetail";
    }
    @GetMapping("/admin/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        NoticeFormDto noticeFormDto = noticeService.findById(id);
        model.addAttribute("noticeUpdate",noticeFormDto);
        return "/admin/noticeUpdate";
    }

    @PostMapping("/admin/update")
    public String update(@ModelAttribute NoticeFormDto noticeFormDto, Model model){
        NoticeFormDto notice = noticeService.update(noticeFormDto);
        model.addAttribute("notice",notice);
        return "/admin/noticeDetail";
        // return "redirect:/board/" + boardDTO.getId();
    }

    @GetMapping("/admin/delete/{id}") // 관리자페이지에서 삭제 후 관리자 공지목록으로 돌아감
    public String delete(@PathVariable Long id){
        noticeService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/paging")
    public String paging(@PageableDefault(page = 1)Pageable pageable, Model model){
        pageable.getPageNumber();
        Page<NoticeFormDto> noticeList = noticeService.paging(pageable);
        int blockLimit =15;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit ))) -1) * blockLimit +1;
        int endPage = ((startPage + blockLimit -1) <noticeList.getTotalPages())? startPage + blockLimit - 1 : noticeList.getTotalPages();

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage",endPage);
        return "/admin/noticePaging";
    }

    //일반 유저 페이지 매핑
    @GetMapping("/paging")
    public String userPaging(@PageableDefault(page = 1)Pageable pageable, Model model){
        pageable.getPageNumber();
        Page<NoticeFormDto> noticeList = noticeService.paging(pageable);
        int blockLimit =15;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit ))) -1) * blockLimit +1;
        int endPage = ((startPage + blockLimit -1) <noticeList.getTotalPages())? startPage + blockLimit - 1 : noticeList.getTotalPages();

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage",endPage);
        return "/userNoticePaging";
    }

    @GetMapping("/{id}")
    public String userFindById(@PathVariable Long id, Model model,
                           @PageableDefault(page = 1) Pageable pageable){
        /* 조회수올리고 게시글 데이터를 가져와서 detail에출력*/
        noticeService.updateHits(id);
        NoticeFormDto noticeFormDto = noticeService.findById(id);
        model.addAttribute("notice",noticeFormDto);
        model.addAttribute("page", pageable.getPageNumber());
        return "/userNoticeDetail";
    }



}