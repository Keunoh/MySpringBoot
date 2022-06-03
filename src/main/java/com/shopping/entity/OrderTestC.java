package com.shopping.entity;

import com.shopping.constant.ItemSellStatus;
import com.shopping.repository.ItemRepository;
import com.shopping.repository.MemberRepository;
import com.shopping.repository.OrderItemRepository;
import com.shopping.repository.OrderRepository;
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
public class OrderTestC {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    @PersistenceContext
    EntityManager em ;  // Jpa에서 Entity를 관리해주는 관리자역할

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Test
    @DisplayName("지연 로딩 테스트")
    public void LazyLoadingTest(){
        Order order = this.createOrder();

        // 주문한 상품들 중에서 첫번째 상품의 아이디
        Long orderItemId = order.getOrderItems().get(0).getId();

        em.flush(); // 영속성 컨텍스트의 내용을 데이터 베이스에 반영하기
        em.clear(); // 영속성 컨텍스트 상태 초기화

        // 주문 상품 번호 orderItemId에 대하여 조회를 해보도록 한다.
        OrderItem orderItem = orderItemRepository
                            .findById(orderItemId)
                            .orElseThrow(EntityNotFoundException::new);
        System.out.println("Order Class : " + orderItem.getOrder().getClass());
        System.out.println("===========================================");
        orderItem.getOrder().getOrderDate();
        System.out.println("===========================================");
    }

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
