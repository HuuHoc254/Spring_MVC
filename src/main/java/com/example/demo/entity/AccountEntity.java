//package com.example.demo.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.List;
//
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "account")
//public class AccountEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer accountId;
//    @Column(name = "account_name")
//    private String accountName;
//    @Column(name = "full_name")
//    private String fullName;
//    @Column(name = "password")
//    private String password;
//    @Column(name = "phone_number")
//    private String phoneNumber;
//    @ManyToOne
//    @JoinColumn(name = "role_id")
//    private RoleEntity role;
//    @Column (name = "is_online", columnDefinition = "BOOLEAN DEFAULT FALSE")
//    private Boolean isOnline;
//    @Column( columnDefinition = "INT DEFAULT 0")
//    private Integer version;
//    @Column( columnDefinition = "BOOLEAN DEFAULT FALSE")
//    private Boolean isDeleted;
//    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
//    private List<CustomerEntity> customerList;
//    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
//    private List<OrderEntity> orderList;
//}