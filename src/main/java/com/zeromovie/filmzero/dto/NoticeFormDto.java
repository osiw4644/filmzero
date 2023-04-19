package com.zeromovie.filmzero.dto;

import com.zeromovie.filmzero.entity.Notice;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoticeFormDto {
    private Long id;
    private String noticeWriter;
    private String noticePass;
    private String noticeTitle;
    private String noticeContents;
    private int noticeHits;
    private LocalDateTime noticeCreatedTime;
    private LocalDateTime noticeUpdatedTime;



    public NoticeFormDto(Long id, String noticeWriter, String noticeTitle, int noticeHits, LocalDateTime noticeCreatedTime) {
        this.id = id;
        this.noticeWriter = noticeWriter;
        this.noticeTitle = noticeTitle;
        this.noticeHits = noticeHits;
        this.noticeCreatedTime = noticeCreatedTime;
    }

    public static NoticeFormDto toNoticeFormDto(Notice notice) {
        NoticeFormDto noticeFormDto = new NoticeFormDto();
        noticeFormDto.setId(notice.getId());
        noticeFormDto.setNoticeWriter(notice.getNoticeWriter());
        noticeFormDto.setNoticePass(notice.getNoticePass());
        noticeFormDto.setNoticeTitle(notice.getNoticeTitle());
        noticeFormDto.setNoticeContents(notice.getNoticeContents());
        noticeFormDto.setNoticeHits(notice.getNoticeHits());
        noticeFormDto.setNoticeCreatedTime(notice.getCreatedTime());
        noticeFormDto.setNoticeUpdatedTime(notice.getUpdatedTime());
        return noticeFormDto;
    }

}
