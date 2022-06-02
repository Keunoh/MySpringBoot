package com.shopping.controller;

import com.shopping.dto.PersonDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/personEx")
public class ThymeleafPersonController {
    @GetMapping(value = "/person01")
    public String findOne(Model model){
        model.addAttribute("data", "1번 사람 찾았다");
        return "personEx/person01";
    }

    @GetMapping(value = "/person02")
    public String getInformation(Model model){
        PersonDto personDto = new PersonDto();

        personDto.setName("김시민");
        personDto.setAge(39);
        personDto.setAddress("서울");
        personDto.setGender("남자");

        model.addAttribute("personDto", personDto);
        return "personEx/person02";
    }

    @GetMapping(value = "/person03")
    public String getInfoList(Model model){
        List<PersonDto> lists = new ArrayList<PersonDto>();
        for (int i = 0; i < 10; i++) {
            PersonDto personDto = new PersonDto();

            personDto.setName("김시민");
            personDto.setAge(39);
            personDto.setAddress("서울");
            personDto.setGender("남자");

            lists.add(personDto);
        }

        model.addAttribute("personList", lists);

        return "personEx/person03";
    }

    @GetMapping(value = "/person04")
    public String justPage(){
        return "personEx/person04";
    }

    @GetMapping(value = "/person05")
    public String sendParams(String param1, String param2, Model model){
        if(param1 == null){param1 = "신검";}
        if(param2 == null){param2 = "합일";}

        model.addAttribute("param1", param1);
        model.addAttribute("param2", param2);

        return "personEx/person05";
    }

}















