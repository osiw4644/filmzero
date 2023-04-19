package com.zeromovie.filmzero.service;

import com.zeromovie.filmzero.dto.NoticeFormDto;
import com.zeromovie.filmzero.entity.Notice;
import com.zeromovie.filmzero.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public void save(NoticeFormDto noticeFormDto) throws IOException {

            Notice notice = Notice.toSaveEntity(noticeFormDto);
            noticeRepository.save(notice);

            Long savedId = noticeRepository.save(notice).getId();
            Notice notice1 = noticeRepository.findById(savedId).get();
    }

    @Transactional
    public List<NoticeFormDto> findAll() {
        List<Notice> noticeList = noticeRepository.findAll();
        List<NoticeFormDto> noticeFormDtoList = new ArrayList<>();
        for (Notice notice: noticeList) {
            noticeFormDtoList.add(NoticeFormDto.toNoticeFormDto(notice));
        }
        return noticeFormDtoList;
    }

    @Transactional
    public void updateHits(Long id) {
        noticeRepository.updateHits(id);
    }

    @Transactional
    public NoticeFormDto findById(Long id) {
        Optional<Notice> optionalNotice = noticeRepository.findById(id);
        if (optionalNotice.isPresent()) {
            Notice notice = optionalNotice.get();
            NoticeFormDto noticeFormDto = NoticeFormDto.toNoticeFormDto(notice);
            return noticeFormDto;
        } else {
            return null;
        }
    }

   // @Transactional
    //public NoticeFormDto userFindById(Long id) {
    //    Optional<Notice> optionalNotice = noticeRepository.findById(id);
      //  if (optionalNotice.isPresent()) {
      //      Notice notice = optionalNotice.get();
      //      NoticeFormDto noticeFormDto = NoticeFormDto.toNoticeFormDto(notice);
    //        return noticeFormDto;
   //     } else {
   //         return null;
   //     }
  //  }

    public NoticeFormDto update(NoticeFormDto noticeFormDto) {
        Notice notice = Notice.toUpdateEntity(noticeFormDto);
        noticeRepository.save(notice);
        return findById(noticeFormDto.getId());
    }

    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }

    public Page<NoticeFormDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 15; // 한 페이지에 보여줄 글 갯수
        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<Notice> notices =
                noticeRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        System.out.println("notices.getContent() = " + notices.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("notices.getTotalElements() = " + notices.getTotalElements()); // 전체 글갯수
        System.out.println("notices.getNumber() = " + notices.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("notices.getTotalPages() = " + notices.getTotalPages()); // 전체 페이지 갯수
        System.out.println("notices.getSize() = " + notices.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("notices.hasPrevious() = " + notices.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("notices.isFirst() = " + notices.isFirst()); // 첫 페이지 여부
        System.out.println("notices.isLast() = " + notices.isLast()); // 마지막 페이지 여부

        // 목록: id, writer, title, hits, createdTime
        Page<NoticeFormDto> noticeFormDtos = notices.map(notice -> new NoticeFormDto(notice.getId(), notice.getNoticeWriter(), notice.getNoticeTitle(), notice.getNoticeHits(), notice.getCreatedTime()));
        return noticeFormDtos;
    }

    public Page<NoticeFormDto> pageSub(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 5; // 한 페이지에 보여줄 글 갯수
        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<Notice> notices =
                noticeRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        System.out.println("notices.getContent() = " + notices.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("notices.getTotalElements() = " + notices.getTotalElements()); // 전체 글갯수
        System.out.println("notices.getNumber() = " + notices.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("notices.getTotalPages() = " + notices.getTotalPages()); // 전체 페이지 갯수
        System.out.println("notices.getSize() = " + notices.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("notices.hasPrevious() = " + notices.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("notices.isFirst() = " + notices.isFirst()); // 첫 페이지 여부
        System.out.println("notices.isLast() = " + notices.isLast()); // 마지막 페이지 여부

        // 목록: id, writer, title, hits, createdTime
        Page<NoticeFormDto> noticeFormDtos = notices.map(notice -> new NoticeFormDto(notice.getId(), notice.getNoticeWriter(), notice.getNoticeTitle(), notice.getNoticeHits(), notice.getCreatedTime()));
        return noticeFormDtos;
    }


}