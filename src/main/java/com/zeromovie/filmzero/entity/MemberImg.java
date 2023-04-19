package com.zeromovie.filmzero.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="memberImg")
@Getter@Setter
@ToString
public class MemberImg {

    @Id
    @Column(name="member_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    //회원이미지번호
    private String member_img_name;
    private String member_ori_img_name;     //기존 이미지파일 이름
    private String member_img_url;  //이미지 경로.

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member; //회원번호


    public void updatememberImg(String ori, String img, String url){
        this.member_ori_img_name = ori;
        this.member_img_name = img;
        this.member_img_url= url;
    }

}