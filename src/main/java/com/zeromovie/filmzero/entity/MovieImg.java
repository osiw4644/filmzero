package com.zeromovie.filmzero.entity;

import javax.persistence.*;

@Entity
@Table(name = "movieImg")
public class MovieImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movieImg_id")
    private Long id;

    private String movie_img_sub; // 스틸컷
    private String movie_video; // 예고편(섬네일만하고 링트로 유튜브 넘김)

    @ManyToOne
    @JoinColumn(name = "movieInfo_id")
    private MovieInfo movieInfo; // 영화코드
}
