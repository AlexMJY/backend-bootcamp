package kr.co.jhta.app.springbootex11.service;

import kr.co.jhta.app.springbootex11.domain.UserEntity;
import kr.co.jhta.app.springbootex11.dto.JoinDTO;
import kr.co.jhta.app.springbootex11.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void joinProcoess(JoinDTO dto) {
        UserEntity user = new UserEntity()
                .builder()
                .username(dto.getUsername())
                .password( passwordEncoder.encode(dto.getPassword()))
                .role("ROLE_USER")
                .build();
        userRepository.save(user);

    }
}
