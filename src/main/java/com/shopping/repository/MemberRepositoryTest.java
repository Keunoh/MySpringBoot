package com.shopping.repository;

import com.shopping.constant.Role;
import com.shopping.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("멤버 저장 테스트")
    public void createMember(){
        Member bean = new Member();

        bean.setName("haha");
        bean.setAddress("zip");
        bean.setEmail("love@bomi.com");
        bean.setPassword("1234");
        bean.setRole(Role.USER);

        Member savedMember = memberRepository.save(bean);
        System.out.println(savedMember.toString());
    }
}
