package com.aibayo.websocket.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    // 사용자 정보를 저장하고 상태를 온라인으로 설정하는 메서드
    public void saveUser(User user) {
        user.setStatus(Status.ONLINE); // 사용자의 상태를 온라인으로 설정
        repository.save(user); // 사용자 정보를 저장소에 저장
    }

    // 사용자를 연결 해제하고 상태를 오프라인으로 설정하는 메서드
    public void disconnect(User user) {
        // 사용자 닉네임으로 저장된 사용자 정보를 찾음
        var storedUser = repository.findById(user.getNickName()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE); // 사용자의 상태를 오프라인으로 설정
            repository.save(user); // 변경된 사용자 정보를 저장소에 저장
        }
    }

    // 현재 온라인 상태인 모든 사용자 목록을 반환하는 메서드
    public List<User> findConnectedUsers() {
        return repository.findAllByStatus(Status.ONLINE); // 온라인 상태인 모든 사용자를 찾음
    }
}
