package com.zeromovie.filmzero.entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import com.zeromovie.filmzero.constant.Role;


@Setter
@Getter
public class CustomUserDetails implements UserDetails, Serializable {

    private String loginId;		// 로그인용 ID 값
    private String password;	// 비밀번호
    private String email;	//이메일
    private boolean emailVerified;	//이메일 인증 여부
    private boolean locked;	//계정 잠김 여부
    private String nickname;	//닉네임
    private Role role;	//권한 목록
    private String AUTHORITY;
    private String userImg;

    public void setUserImg(String userImg){
        this.userImg=userImg;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(AUTHORITY));
        return auth;
    }

    /**
     * 비밀번호
     */
    @Override
    public String getPassword() {
        return password;
    }


    /**
     * PK값
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * 계정 만료 여부
     * true : 만료 안됨
     * false : 만료
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정 잠김 여부
     * true : 잠기지 않음
     * false : 잠김
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    /**
     * 비밀번호 만료 여부
     * true : 만료 안됨
     * false : 만료
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    /**
     * 사용자 활성화 여부
     * ture : 활성화
     * false : 비활성화
     * @return
     */
    @Override
    public boolean isEnabled() {
        //이메일이 인증되어 있고 계정이 잠겨있지 않으면 true
        return (emailVerified && !locked);
    }

}