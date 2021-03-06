package com.shopping.entity;

import com.shopping.constant.ItemSellStatus;
import com.shopping.repository.ItemRepository;
import com.shopping.repository.MemberRepository;
import com.shopping.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
public class OrderTestB {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    @PersistenceContext
    EntityManager em ;  // Jpa에서 Entity를 관리해주는 관리자역할

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("고아 객체 제거 테스트")
    public void orphanRemovalTest(){
        Order order = this.createOrder();
        order.getOrderItems().remove(0); // 0번째 요소 제거하기
        em.flush(); // 영속성 컨텍스트에 있는 내용을 데이터베이스에 반영시킵니다.
    }

    // 주문 데이터를 생성하고, 저장해주는 메소드
    private Order createOrder() {
        Order order = new Order();
        for (int i = 0; i < 3; i++) {
            Item item = this.createItem();
            itemRepository.save(item);

            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);

            order.getOrderItems().add(orderItem);
        }
        Member member = new Member();
        memberRepository.save(member);
        order.setMember(member);
        orderRepository.save(order);
        return order;
    }

    private Item createItem() {
        Item item = new Item();

        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("상품 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());

        return item;
    }
}
