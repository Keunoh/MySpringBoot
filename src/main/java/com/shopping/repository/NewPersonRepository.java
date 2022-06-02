package com.shopping.repository;

import com.shopping.entity.NewPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewPersonRepository extends JpaRepository<NewPerson, Long>,
        QuerydslPredicateExecutor<NewPerson> {
    // 이름순으로 정렬
    List<NewPerson> findByOrderByNameDesc();

    // 거주지가 마포인 사람 조회
    List<NewPerson> findByAddress(String address);

    // 급여가 높은순 정렬
    List<NewPerson> findByOrderBySalaryDesc();

    @Query(" select i from NewPerson i where i.salary > :salary ")
    List<NewPerson> findBySalary(@Param("salary") int salary);
}
