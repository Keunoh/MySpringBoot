package com.shopping.repository;

import com.querydsl.core.BooleanBuilder;
import com.shopping.entity.NewPerson;
import com.shopping.entity.QNewPerson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class NewPersonRepositoryTest02 {

    @Autowired
    NewPersonRepository newPersonRepository;

    @Test
    @DisplayName("회원 생성")
    public void createPersonTest(){
        String[] name = {"김시민", "이순신", "사명대사"};
        String[] address = {"마포", "성북", "구로"};
        int[] salary = {100, 200, 300};

        for (int i = 1; i <= 10; i++) {
            NewPerson bean = new NewPerson();

            bean.setName(name[i % name.length]);
            bean.setAddress(address[i % address.length]);
            bean.setSalary(salary[i % salary.length]);

            NewPerson savedPerson = newPersonRepository.save(bean);
            System.out.println(savedPerson.toString());
        }
    }

    @Test
    @DisplayName("이름순으로 정렬하기")
    public void findByNameOrderByNameDesc(){
        List<NewPerson> lists = newPersonRepository.findByOrderByNameDesc();
        for(NewPerson bean : lists){
            System.out.println(bean.toString());
        }
    }

    @Test
    @DisplayName("주소가 마포인 경우")
    public void findByAddress(){
        List<NewPerson> lists = newPersonRepository.findByAddress("마포");
        for(NewPerson bean : lists){
            System.out.println(bean.toString());
        }
    }

    @Test
    @DisplayName("급여별로 테스트")
    public void findByOrderBySalaryDesc() {
        List<NewPerson> lists = newPersonRepository.findByOrderBySalaryDesc();
        for (NewPerson bean : lists) {
            System.out.println(bean.toString());
        }
    }

    @Test
    @DisplayName("급여 테스트 2")
    public void findBySalary(){
        List<NewPerson> lists = newPersonRepository.findBySalary(150);
        for (NewPerson bean : lists) {
            System.out.println(bean.toString());
        }
    }

    @Test
    @DisplayName("페이징 테스트")
    public void queryDslTest(){
        String address = "포";
        int salary = 50;

        // 이건 어디에씀 => 엔티티 가져오는거같음
        QNewPerson bean = QNewPerson.newPerson;

        // where 절
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(bean.salary.gt(salary));
        booleanBuilder.and(bean.address.like("%" + address + "%"));

        // 페이징
        Pageable pageable = PageRequest.of(0, 2, Sort.by("name").descending());

        Page<NewPerson> personPagingResult
                = newPersonRepository.findAll(booleanBuilder, pageable);
        System.out.println("total elements : " + personPagingResult.getTotalElements());

        List<NewPerson> resultPersonList = personPagingResult.getContent();
        for(NewPerson person : resultPersonList){
            System.out.println(person);
        }
    }
}
