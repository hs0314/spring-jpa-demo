package heesu.me.springjpademo.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import heesu.me.springjpademo.domain.Address;
import heesu.me.springjpademo.domain.Order;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // mappedBy를 통해서 연관관계의 주체를 Order로 봄
    private List<Order> orders = new ArrayList<>();
}
