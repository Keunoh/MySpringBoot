package com.shopping.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "new")
@Getter @Setter @ToString
public class NewPerson {
    @Id
    @Column(name = "new_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, name = "address", length = 50)
    private String address;

    @Column(nullable = false)
    private int salary;
}

