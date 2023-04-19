package com.zeromovie.filmzero.dto;

import com.zeromovie.filmzero.entity.Member;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter@Setter@ToString@NoArgsConstructor@AllArgsConstructor
public class MemberFormDto {
    private Long id;
    @NotEmpty(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;
    @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
    @Length(min = 8, max = 15, message = "비밀번호는 8자 이상 15자 이하로 입력하세요,")
    private String pw;
    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;
    @NotBlank(message = "닉네임도 필수 입력값입니다.")
    private String nickname;
    @NotBlank(message = "생일은 필수 입력값입니다.")
    private String birth;

    @NotEmpty(message = "연락처 입력은 필수입니다.")
    private String tel;
    private int point;

    private MemberImgFormDto memberImgFormDto;



    private static ModelMapper modelMapper = new ModelMapper();

    public static MemberFormDto of(Member member){return modelMapper.map(member, MemberFormDto.class);}


    public MemberFormDto(Long id, String email, String nickname,
                         String name, String tel, String birth, String pw) {
        this.id = id;
        this.pw = pw;
        this.email = email;
        this.nickname = nickname;
        this.name = name;
        this.tel = tel;
        this.birth = birth;
    }




}