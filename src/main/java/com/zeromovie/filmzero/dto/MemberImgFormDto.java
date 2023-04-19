package com.zeromovie.filmzero.dto;

import com.zeromovie.filmzero.entity.Member;
import com.zeromovie.filmzero.entity.MemberImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
public class MemberImgFormDto {

    private Long id;    //회원이미지번호
    private String member_img_name;
    private String member_ori_img_name;     //기존 이미지파일 이름
    private String member_img_url;  //이미지 경로.


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //회원번호
    private static ModelMapper modelMapper = new ModelMapper();
    public static MemberImgFormDto of(MemberImg memberImg){
        return modelMapper.map(memberImg, MemberImgFormDto.class);
    }
}