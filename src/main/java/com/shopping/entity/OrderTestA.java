package com.shopping.entity;

import com.shopping.constant.ItemSellStatus;
import com.shopping.repository.ItemRepository;
import com.shopping.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
public class OrderTestA {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    @PersistenceContext
    EntityManager em ;  // Jpa에서 Entity를 관리해주는 관리자역할

    @Test
    @DisplayName("영속성 전이 테스트")
    public void cascadeTest(){
        Order order = new Order();
        int data_length = 3; // 주문할 아이템 개수

        for (int i = 0; i < data_length; i++) {
            Item item = this.createItem();
            itemRepository.save(item);

            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);

            order.getOrderItems().add(orderItem);
        }

        // 강제로 flush 명령어를 호출하여 영속성 컨텍스트의 내용을
        // 데이터 베이스에 반영시킵니다.
        orderRepository.saveAndFlush(order);
        // 영속성 컨텍스트의 내용을 비우고, 초기화 상태로 세팅
        em.clear();

        // order : 저장할 정보를 담고 있는 주문 객체
        // savedOrder : 저장된 주문 정보를 담고 있는 객체
        Order savedOrder = orderRepository
                        .findById(order.getId())
                        .orElseThrow(EntityNotFoundException::new);
        // 주문한 아이템 개수와 실제 반영된 개수를 비교해본다.
        Assertions.assertEquals(data_length, savedOrder.getOrderItems().size());
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
