package com.zeromovie.filmzero.dto;

import com.zeromovie.filmzero.entity.Member;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter@Setter@ToString@NoArgsConstructor@AllArgsConstructor
public class MemberFormListDto {
    private Long id;

    private String email;

    private String pw;

    private String name;

    private String nickname;

    private String birth;

    private String tel;
    private int point;

    private MemberImgFormDto memberImgFormDto;



    private static ModelMapper modelMapper = new ModelMapper();

    public static MemberFormListDto of(Member member){return modelMapper.map(member, MemberFormListDto.class);}


    public MemberFormListDto(Long id, String email, String nickname, String name, String tel) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.name = name;
        this.tel = tel;
    }




}