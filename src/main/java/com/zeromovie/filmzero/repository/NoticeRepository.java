package com.zeromovie.filmzero.repository;

import com.zeromovie.filmzero.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Modifying
    @Query(value = "update Notice b set b.noticeHits=b.noticeHits+1 where b.id=:id")
    void updateHits(@Param("id") Long id);
}
