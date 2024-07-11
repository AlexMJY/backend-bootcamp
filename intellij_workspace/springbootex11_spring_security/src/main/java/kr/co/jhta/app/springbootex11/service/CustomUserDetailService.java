package kr.co.jhta.app.springbootex11.service;

import kr.co.jhta.app.springbootex11.domain.UserEntity;
import kr.co.jhta.app.springbootex11.dto.CustomUserDetail;
import kr.co.jhta.app.springbootex11.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// Spring Security에서 사용자 인증을 위해 사용자 정보를 로드하는 UserDetailsService 인터페이스를 구현한 CustomUserDetailService 클래스

@Service  // 이 클래스가 서비스 레이어의 컴포넌트임을 명시
@RequiredArgsConstructor  // final로 선언된 필드를 포함하는 생성자를 자동으로 생성
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;  // UserRepository를 주입받아 사용

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 주어진 사용자 이름으로 사용자 데이터를 조회
        UserEntity userData = userRepository.findByUsername(username);
        if (userData != null) {
            // 조회된 사용자가 있을 경우 CustomUserDetail 객체로 변환하여 반환
            return new CustomUserDetail(userData);
        }
        // 조회된 사용자가 없을 경우 null 반환
        return null;
    }
}