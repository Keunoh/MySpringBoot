package com.shopping.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.shopping.constant.ItemSellStatus;
import com.shopping.entity.Item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
// 상품 데이터 정보를 전달해주는 dto 클래스
public class ItemFormDto {  // 페이지 233

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "상품 상세는 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    // 상품과 관련된 이미지들의 id를 저장할 용도로 사용하는 데, 수정하고자 할 때 id들을 저장할 용도로 사용합니다.
    private List<Long> itemImgIds = new ArrayList<>(); // 상품 이미지 id 리스트 컬렉션

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem(){
    	// this(ItemFormDto) 객체를 Item 객체로 매핑시켜 줍니다. 
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item){
    	// item(Item) 객체를 ItemFormDto 객체로 매핑시켜 줍니다.
        return modelMapper.map(item, ItemFormDto.class);
    }

}