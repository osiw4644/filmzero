package com.zeromovie.filmzero.entity;

import com.zeromovie.filmzero.constant.Role;
import com.zeromovie.filmzero.dto.MemberFormDto;
//import com.zeromovie.filmzero.dto.MemberUpdateFormDto;
//import com.zeromovie.filmzero.repository.MemberUpdateRepository;
import com.zeromovie.filmzero.dto.MemberFormUpdateDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.persistence.*;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    private String pw;

    private String name;
    private String nickname;
    private String birth;
    private String tel;
    private int point;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setNickname(memberFormDto.getNickname());
        member.setBirth(memberFormDto.getBirth());
        member.setTel(memberFormDto.getTel());
        String password = passwordEncoder.encode(memberFormDto.getPw());  //비밀번호 암호화 시켜주기 위한 작업
        member.setPw(password);
        member.setRole(Role.USER);

        return member;
    }


    public void memberUpdate(MemberFormUpdateDto member) {
        this.email = member.getEmail();
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.birth = member.getBirth();
        this.tel = member.getTel();
        this.point = member.getPoint();
    }
}
