package com.zeromovie.filmzero.entity;

import com.zeromovie.filmzero.dto.NoticeFormDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Notice")
public class Notice extends BaseEntity {
    @Id // pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(length = 20, nullable = false) // 크기 20, not null
    private String noticeWriter;

    @Column // 크기 255, null 가능
    private String noticePass;

    @Column
    private String noticeTitle;

    @Column(length = 500)
    private String noticeContents;

    @Column
    private int noticeHits;





    public static Notice toSaveEntity(NoticeFormDto boardDTO) {
        Notice notice = new Notice();
        notice.setNoticeWriter(boardDTO.getNoticeWriter());
        notice.setNoticePass(boardDTO.getNoticePass());
        notice.setNoticeTitle(boardDTO.getNoticeTitle());
        notice.setNoticeContents(boardDTO.getNoticeContents());
        notice.setNoticeHits(0);
        return notice;
    }

    public static Notice toUpdateEntity(NoticeFormDto noticeFormDto) {
        Notice notice = new Notice();
        notice.setId(noticeFormDto.getId());
        notice.setNoticeWriter(noticeFormDto.getNoticeWriter());
        notice.setNoticePass(noticeFormDto.getNoticePass());
        notice.setNoticeTitle(noticeFormDto.getNoticeTitle());
        notice.setNoticeContents(noticeFormDto.getNoticeContents());
        notice.setNoticeHits(noticeFormDto.getNoticeHits());
        return notice;
    }


}
