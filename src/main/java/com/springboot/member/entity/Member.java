package com.springboot.member.entity;

import com.springboot.order.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 13, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "MEMBER")
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "STAMP", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "STAMP_ID")
    private Stamp stamp = new Stamp();

    public void addOrder (Order order) {
        orders.add(order);

        if(order.getMember() != this) {
            order.setMember(this);
        }
    }

    public enum MemberStatus {
        MEMBER_ACTIVE ("활동중"),
        MEMBER_SLEEP("휴면상태"),
        MEMBER_QUID("탈퇴상태");

        @Getter
        private String description;

        MemberStatus(String description) {
            this.description = description;
        }
    }
}
