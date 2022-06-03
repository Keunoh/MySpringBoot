package com.shopping.entity;

import com.shopping.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "item") // name 속성은 실제 테이블 이름이 된다.
@Getter @Setter @ToString
public class Item extends BaseEntity {
    @Id
    @Column(name = "item_id")
    // AUTO는 JPA 구현체가 자동으로 기본키 생성 전략을 결정
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id; // 상품코드(기본 키역할)

    // 테이블에서 not null 옵션과 동일하다.
    // 최대 길이는 50인데, 필수 입력 사항(not null)이다.
    @Column(nullable = false, length = 50)
    private String itemNm; // 상품 이름

    @Column(nullable = false, name = "price")
    private int price; // 가격

    @Column(nullable = false)
    private int stockNumber; // 재고 수량

    @Lob // CLOB(Character Large OBject), BLOB(Binary Large OBject)
    @Column(nullable = false)
    private String itemDetail; // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; // 상품 판매 상태

//    private LocalDateTime regTime; // 등록 시간
//    private LocalDateTime updateTime; // 수정 시간
}
