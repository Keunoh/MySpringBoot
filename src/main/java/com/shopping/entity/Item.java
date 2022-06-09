package com.shopping.entity;

import com.shopping.constant.ItemSellStatus;
import com.shopping.dto.ItemFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity{
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy=GenerationType.AUTO) // Auto는 JPA 구현체가 자동으로 생성 전략을 결정합니다.
    private Long id;       //상품 코드(기본 키 역할)

    // nullable = false이면 테이블 설계시 반드시 not null이어야 합니다.
    @Column(nullable = false, length = 50)
    private String itemNm; //상품명

    @Column(nullable = false, name="price")
    private int price; //가격

    @Column(nullable = false)
    private int stockNumber; //재고 수량

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품 판매 상태

//    private LocalDateTime regTime ; //등록 시간
//    private LocalDateTime updateTime ; //수정 시간

    // 상품을 업데이트 해주는 메소드  // 페이지 261
    public void updateItem(ItemFormDto itemFormDto) {
        this.itemNm = itemFormDto.getItemNm() ;
        this.price = itemFormDto.getPrice() ;
        this.stockNumber = itemFormDto.getStockNumber() ;
        this.itemDetail = itemFormDto.getItemDetail() ;
        this.itemSellStatus = itemFormDto.getItemSellStatus() ;
    }

}

