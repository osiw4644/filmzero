package com.zeromovie.filmzero.service;

import com.zeromovie.filmzero.dto.MemberFormDto;
import com.zeromovie.filmzero.dto.MemberFormUpdateDto;
import com.zeromovie.filmzero.dto.MemberImgFormDto;
import com.zeromovie.filmzero.dto.NoticeFormDto;
import com.zeromovie.filmzero.entity.*;
import com.zeromovie.filmzero.repository.MemberImgRepository;
import com.zeromovie.filmzero.repository.MemberRepository;
import com.zeromovie.filmzero.repository.MovieCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional// 서비스 클래스가 동작하다가 에러가 발생하면 에러 발생 이전상태로 돌아가게 하기 위한 것
@RequiredArgsConstructor   // Final 이나 notnull 이 있는 객체에 생성자를 생성하여 넣어주는 것
public class MemberService implements UserDetailsService {


    private final MemberRepository mrep;
    private final MemberImgRepository memberimg;

    private final MemberImgService  memberImgService;
    private  final MovieCommentRepository movieCommentRepository;

    public Member saveMember(Member mem){   // 회원가입시 데이터베이스에 저장하기 위한 메서드
        validatedup(mem);   //임의로 만든것

        return mrep.save(mem);
    }

    private  void validatedup(Member member){   // 회원가입시 이메일 중복여부 확인하기 위한 메서드
        Member findemail = mrep.findByEmail(member.getEmail());
        if (findemail !=null){  // null이 아니라면 데이터베이스에 저장된 이메일 이다.
            throw new IllegalStateException("이미 가입된 이메일 입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {  // SecurityConfig로 보낸다

        Member member = mrep.findByEmail(email);    // member 참조 변수
        if(member ==null){  //이메일 조회 실패
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPw())
                .roles(member.getRole().toString())
                .build();

    }

    public MemberFormDto memberlist(String email) {
        Member member = mrep.findByEmail(email);    //member에 mrep(member email)를 찾아서 넣어주겠다.
        MemberFormDto memberFormDto = MemberFormDto.of(member); //memberFormDto에 member값을 다 넣어주겠다.
        return memberFormDto;
    }
    public MemberFormUpdateDto memberupdate(String email) {   //업데이트 페이지로
        Member member = mrep.findByEmail(email);    //member에 mrep(member email)를 찾아서 넣어주겠다.
        MemberFormUpdateDto memberFormUpdateDto = MemberFormUpdateDto.of(member); //memberFormDto에 member값을 다 넣어주겠다.
        MemberImg membercheck = memberimg.findByMemberId(member.getId());// memberimg에 MemberId찾아서 member.Id를 비교해서
        System.out.println(membercheck);
        if (membercheck != null ) { //테이블에 값이 있다면
            MemberImg memberImg = memberimg.findByMemberId(member.getId()); //member Id를 memberImg에 담고
            MemberImgFormDto memberImgFormDto = MemberImgFormDto.of(memberImg); //memberImg에 orgimg, img_name, 를 담아주겠다.
            memberFormUpdateDto.setMemberImgFormDto(memberImgFormDto);
        }
        return memberFormUpdateDto;
    }

    public Long memberupdateForm(MemberFormUpdateDto memberFormUpdateDto, MultipartFile ProfileImg) throws Exception{  //회원 업데이트

        if (ProfileImg.getOriginalFilename().isEmpty()) {  //이미지 선택안학 버튼 클릭시
            System.out.println("이미지선택안함");
            Member member = mrep.findByEmail(memberFormUpdateDto.getEmail());
            member.memberUpdate(memberFormUpdateDto);
        }
        else {  //이미지 선택후 버튼 클릭시
            Member member = mrep.findByEmail(memberFormUpdateDto.getEmail());
            member.memberUpdate(memberFormUpdateDto);
            MemberImg membercheck = memberimg.findByMemberId(member.getId());// memberimg에 MemberId찾아서 member.Id를 비교해서
            System.out.println(membercheck);
            if (membercheck == null) {
                System.out.println("이미지 신규 등록");
                MemberImg memberImg = new MemberImg();
                memberImg.setMember(member);
                memberImgService.saveMemberImg(memberImg, ProfileImg);
            }
            else {
                MemberImg memberImg = memberimg.findByMemberId(memberFormUpdateDto.getId());
                memberImg.setMember(member);
                System.out.println("이미지 수정 등록");
                memberImgService.updateMemberImg(memberImg, ProfileImg);
            }
        }
        return memberFormUpdateDto.getId();
    }



    public void delete(Long memberId) {
        MemberImg memberImg = memberimg.findByMemberId(memberId);
        List<MovieComment> movieComment = movieCommentRepository.findByMemberId(memberId);
        if (memberImg != null){
            memberimg.deleteById(memberImg.getId());
            mrep.deleteById(memberId);
            for (MovieComment t: movieComment){
                movieCommentRepository.deleteById(t.getId());
            }
        } else {
            mrep.deleteById(memberId);
            for (MovieComment t: movieComment){
                movieCommentRepository.deleteById(t.getId());
            }
        }
    }

    public String getImg(String email){
        Member member = mrep.findByEmail(email);
        MemberImg memberImg = memberimg.findByMemberId(member.getId());
        if(memberImg ==null) return null;
        return memberImg.getMember_img_url();
    }





    public Page<MemberFormDto> pageSub(Pageable pageable) { //admin페이지에서 필요한 page
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 5; // 한 페이지에 보여줄 글 갯수
        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<Member> members =
                mrep.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        System.out.println("members.getContent() = " + members.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("members.getTotalElements() = " + members.getTotalElements()); // 전체 글갯수
        System.out.println("members.getNumber() = " + members.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("members.getTotalPages() = " + members.getTotalPages()); // 전체 페이지 갯수
        System.out.println("members.getSize() = " + members.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("members.hasPrevious() = " + members.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("members.isFirst() = " + members.isFirst()); // 첫 페이지 여부
        System.out.println("members.isLast() = " + members.isLast()); // 마지막 페이지 여부

        Page<MemberFormDto> memberFormDtos = members.map(member -> new MemberFormDto(
                member.getId(),
                member.getPw(),
                member.getEmail(),
                member.getNickname(),
                member.getName(),
                member.getTel(),
                member.getBirth()));
        // 목록: id, email, name, Nickname, tel
        return memberFormDtos;
    }


    //   @Transactional(readOnly = true)
    //   public MainMemberDto getMainMemberPage() {  //메인 페이지용

//        return memberMainRepository.getMainMemberPage();
//    }

}