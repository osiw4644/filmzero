package com.zeromovie.filmzero.control;

import com.zeromovie.filmzero.dto.BoardDTO;
import com.zeromovie.filmzero.dto.MemberFormDto;
import com.zeromovie.filmzero.dto.MemberFormUpdateDto;
import com.zeromovie.filmzero.dto.NoticeFormDto;
import com.zeromovie.filmzero.entity.Member;
import com.zeromovie.filmzero.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService mservice;
    private final PasswordEncoder passwordE;

    @GetMapping(value="/new")  //회원가입으로 이동.
    public String join(Model model){
        model.addAttribute("memberFormDto",new MemberFormDto());
        return "join";
    }

    @PostMapping(value="/new")  //회원가입 요청시.
    public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult br, Model md){
        if(br.hasErrors()){
            return "/join";
        }
        try{
            Member mem = Member.createMember(memberFormDto, passwordE );
            mservice.saveMember(mem);
        }catch(Exception e){
            md.addAttribute("errorMessage", e.getMessage());
            return "/join";
        }
        return "/login";
    }


    @GetMapping("/logins")  //로그인 페이지 이동.
    public String loginMember(){
        return "/login";
    }

    @GetMapping("/login/error")   //로그인 오류시.
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/login";
    }

    @GetMapping("/myPoint")  //나의 포인트 확인
    public String myPoint(){    //나의 포인트
        return "/myPoint";
    }
    @GetMapping("/myPage")   //마이페이지 이동
    public String myPage(Principal principal, Model model){
        if (principal == null) {
            return "login";
        }
        String email = principal.getName();
        MemberFormDto memberFormDto = mservice.memberlist(email);
        System.out.println(memberFormDto.getName());
        model.addAttribute("memberFormDto", memberFormDto);
        return "/myPage";
    }

    @GetMapping("/update")   //회원정보 불러오기.
    public String updatemember(Principal principal, Model model){
        String email = principal.getName();
        MemberFormUpdateDto memberFormUpdateDto = mservice.memberupdate(email);

//        System.out.println( memberFormDto.getMemberImgFormDto().getMember_img_name());
        model.addAttribute("MemberFormUpdateDto", memberFormUpdateDto);
        return "myInfo";
    }

    @PostMapping("/update/{id}")   //회원정보 수정 요청시.
    public String updateForm(@Valid MemberFormUpdateDto memberFormUpdateDto,
                             @RequestParam("profileImg") MultipartFile ProfileImg,
                             Model model){
        try{
            mservice.memberupdateForm(memberFormUpdateDto, ProfileImg);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("회원정보 수정 실패");
            model.addAttribute("errorMessage", "회원 수정중 오류가 발생했어요.");
            return "/myInfo";
        }

        return "redirect:/member/myPage";
    }

    @GetMapping(value = "/Delete/{id}")    //회원 탈퇴 클릭시
    public String Delete(@PathVariable("id") Long id,Principal principal){
        mservice.delete(id);
        principal=null;
        return "redirect:/member/logout";
    }












    //관리자모드 회원 관리
    @PostMapping("/admin/memberModify/{id}")
    public String memberUpdateForm(@Valid MemberFormUpdateDto memberFormUpdateDto,
                                   @RequestParam("profileImg") MultipartFile ProfileImg,
                                   Model model){
        try{
            mservice.memberupdateForm(memberFormUpdateDto, ProfileImg);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("회원정보 수정 실패");
            model.addAttribute("errorMessage", "회원 수정중 오류가 발생했어요.");
            return "/myInfo";
        }

        return "redirect:/member/myPage";
    }

    @GetMapping(value = "admin/delete/{id}")    //회원 탈퇴 클릭시
    public String memberDelete(@PathVariable("id") Long id){
        mservice.delete(id);
        return "redirect:/member/logout";
    }

    @GetMapping("/admin/paging") //admin 회원관리 클릭시
    public String memeberadminList(@PageableDefault(page = 1) Pageable pageable, Model model){

        pageable.getPageNumber();
        Page<MemberFormDto> memberList = mservice.pageSub(pageable);

        int blockLimit =99;
        int memberStartPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit ))) -1) * blockLimit +1;
        int memberEndPage = ((memberStartPage + blockLimit -1) <memberList.getTotalPages())? memberStartPage + blockLimit - 1 : memberList.getTotalPages();

        model.addAttribute("memberList", memberList);
        model.addAttribute("memberStartPage", memberStartPage);
        model.addAttribute("memberEndPage", memberEndPage);

        return "/admin/memberCS";
    }

}