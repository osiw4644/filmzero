package com.zeromovie.filmzero.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainMemberDto {
    private Long id;
    private String member_img_url;

    private int point;


    @QueryProjection
    public MainMemberDto(Long id, String member_img_url, int point){
        this.id=id;
        this.member_img_url=member_img_url;
        this.point=point;
    }
}