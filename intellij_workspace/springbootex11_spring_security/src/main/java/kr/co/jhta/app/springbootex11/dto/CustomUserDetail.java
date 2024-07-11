package kr.co.jhta.app.springbootex11.dto;

import kr.co.jhta.app.springbootex11.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor  // final로 선언된 필드를 포함하는 생성자를 자동으로 생성
public class CustomUserDetail implements UserDetails {
    private UserEntity userEntity;  // UserEntity 객체를 저장하기 위한 필드

    // 사용자 데이터를 받아서 userEntity 필드에 할당하는 생성자
    public CustomUserDetail(UserEntity userData) {
        this.userEntity = userData;
    }

    // 사용자 권한을 반환하는 메서드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "";  // 권한 정보 반환 (여기서는 빈 문자열 반환)
            }
        });
        return collection;
    }

    // 사용자 비밀번호를 반환하는 메서드
    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    // 사용자 이름(아이디)을 반환하는 메서드
    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    // 계정의 만료 여부를 반환하는 메서드 (true: 만료되지 않음)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정의 잠김 여부를 반환하는 메서드 (true: 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 자격 증명의 만료 여부를 반환하는 메서드 (true: 만료되지 않음)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정의 활성화 여부를 반환하는 메서드 (true: 활성화됨)
    @Override
    public boolean isEnabled() {
        return true;
    }
}