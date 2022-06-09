package com.shopping.dto;

import org.modelmapper.ModelMapper;

import com.shopping.entity.ItemImg;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter // 페이지 232
public class ItemImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDto of(ItemImg itemImg) {
    	// 상품의 이미지 정보를 상품 전달 Dto 객체로 변환해 줍니다.
        return modelMapper.map(itemImg, ItemImgDto.class);
    }
}