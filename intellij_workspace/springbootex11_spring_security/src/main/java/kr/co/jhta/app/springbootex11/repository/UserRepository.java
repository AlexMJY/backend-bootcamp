package kr.co.jhta.app.springbootex11.repository;

import kr.co.jhta.app.springbootex11.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(String name);
}


