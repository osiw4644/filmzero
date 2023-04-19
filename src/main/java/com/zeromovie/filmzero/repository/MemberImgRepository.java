package com.zeromovie.filmzero.repository;

import com.zeromovie.filmzero.entity.MemberImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberImgRepository extends JpaRepository<MemberImg, Long> {

    MemberImg findByMemberId(Long memberId);


    void deleteById(Long id);


}
