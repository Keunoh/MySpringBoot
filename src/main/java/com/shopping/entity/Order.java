package com.shopping.entity;

import com.shopping.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;                    //

    @ManyToOne(fetch = FetchType.LAZY)  // 한사람은 여러개의 주문을 할 수 있다.
    private Member member;              //

    private LocalDateTime orderDate;    // 주문 날짜

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;    // 주문 상태

    private LocalDateTime regTime;      // 등록 시간
    private LocalDateTime updateTime;   // 수정 시간

    // cascade = CascadeType.ALL : 부모 Entity의 영속성 상태 변화를
    // 자식 Entity에게 모두 전이한다.
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
}










