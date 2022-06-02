package com.shopping.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter @Setter @ToString
public class Cart { // 장바구니
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // 장바구니가 회원 Entitiy를 참조함
    // JoinColumn <- FK를 의미하는 듯
    @OneToOne
    @JoinColumn(name = "member_id") // 나중에 FK됨
    private Member member;
}
