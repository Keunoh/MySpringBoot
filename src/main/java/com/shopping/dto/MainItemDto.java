package com.shopping.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainItemDto {
    private Long id;
    private String itemNm;
    private String itemDetail;
    private String imgUrl;
    private Integer price;

    // Entity가 아닌 다른 객체(dto 등)들을 qeurydsl에 사용하고자 할때 이 어노테션을 사용합니다.
    // Projection이라는 용어는 테이블에서 특정 컬럼들을 추출해내는 동작을 의미합니다.
    @QueryProjection
    public MainItemDto(Long id, String itemNm, String itemDetail, String imgUrl, Integer price) {
        this.id = id;
        this.itemNm = itemNm;
        this.itemDetail = itemDetail;
        this.imgUrl = imgUrl;
        this.price = price;
    }
}
