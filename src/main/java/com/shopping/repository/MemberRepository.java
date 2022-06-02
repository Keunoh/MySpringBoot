package com.shopping.repository;

import com.shopping.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

// Entity의 클래스와 Entity에 primary key에 해당하는 값의 타입을 인터페이스
// generic으로 설정한다.
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 이메일을 이용하여 회원 검색, 가입 시 중복체크
    Member findByEmail(String email);
}
