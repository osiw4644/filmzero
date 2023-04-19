package com.zeromovie.filmzero.service;

import com.zeromovie.filmzero.entity.MemberImg;
import com.zeromovie.filmzero.repository.MemberImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberImgService {
    @Value("${memberImgLocation}")
    private String memberImgLocation;
    private final FileService fileService;
    private final MemberImgRepository memberImgRepository;


    public void saveMemberImg(MemberImg memberImg, MultipartFile ProfileImg) throws Exception{
        String memberOriImgName = ProfileImg.getOriginalFilename();
        String memberImgName="";
        String memberImgUrl="";

        // 파일 업로드
        if( !StringUtils.isEmpty(memberOriImgName) ){
            memberImgName = fileService.uploadFile(memberImgLocation, memberOriImgName,
                    ProfileImg.getBytes());
            //사용자가 상품의 이미지를 등록했다면 저장할 경로와 파일의 이름,
            // 파일을 파일의 바이트 배열을 파일 업로드 파라미터로 uploadFile 메서드 호출
            // 호출 결과 실제 저장되는 파일의 이름을 imgName에 저장
            memberImgUrl = "/images/Member/"+memberImgName;
            //저장한 상품 이미지를 불러올 경로를 설정
            // 실제 경로는 C:/toyImage/item 이지만 웹에서 브라우저로 접속을 하였을때
            //  C:/toImage/item의 경로를 사용할 권한은 주어 지지 않는다.
            // 그래서 내부에서만 실제경로를 사용하고  외부에서는 접근권한이 있는 경로 로
            // 사용하도록 리소스 연결 작업을 해주었다.
            // 즉  외부(브라우저를 통한 접속) 에서는 static하위 디렉토리인 images를 사용하도록
            //  되어있다.
        }
        memberImg.updatememberImg(memberOriImgName, memberImgName, memberImgUrl);
        memberImgRepository.save(memberImg); // 테이블 저장
    }

    public void updateMemberImg(MemberImg memberImg, MultipartFile itemImgFile) throws Exception{
        if(!itemImgFile.isEmpty()){
            MemberImg savedMemberImg = memberImgRepository.findById(memberImg.getId()).orElseThrow(EntityNotFoundException::new);

            //기존 이미지 파일 삭제
            if(!StringUtils.isEmpty(savedMemberImg.getMember_img_name())){
                fileService.deleteFile(memberImgLocation+"/"+ savedMemberImg.getMember_img_name());
            }
            String memberOriImgName = itemImgFile.getOriginalFilename();
            String memberImgName = fileService.uploadFile(memberImgLocation, memberOriImgName, itemImgFile.getBytes());
            String memberImgUrl = "/images/Member/" + memberImgName;
            savedMemberImg.updatememberImg(memberOriImgName, memberImgName, memberImgUrl);
        }
    }
}