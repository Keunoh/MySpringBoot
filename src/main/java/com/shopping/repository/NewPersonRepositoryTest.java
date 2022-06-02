package com.shopping.repository;

import com.shopping.entity.NewPerson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NewPersonRepositoryTest {
    @Autowired
    NewPersonRepository newPersonRepository;

    @Test
    @DisplayName("뉴펄슨 저장 테스트")
    public void createNewPersonTest(){
        NewPerson newPerson = new NewPerson();
        newPerson.setAddress("your home");
        newPerson.setName("zicococo");
        newPerson.setSalary(1000);

        NewPerson savedNewPerson = newPersonRepository.save(newPerson);
        System.out.println(savedNewPerson.toString());
    }
}
