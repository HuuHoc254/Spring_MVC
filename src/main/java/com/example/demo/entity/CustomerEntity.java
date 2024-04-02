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
//@Entity
//@Table(name = "customer")
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//public class CustomerEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer customerId;
//    @Column( name = "customer_name")
//    private String customerName;
//    @Column( name = "phone_number")
//    private String phoneNumber;
//    @ManyToOne
//    @JoinColumn(name = "administrator_id")
//    private AccountEntity account;
//    @Column( name = "address")
//    private String address;
//    @Column( columnDefinition = "INT DEFAULT 0")
//    private Integer version;
//    @Column( columnDefinition = "BOOLEAN DEFAULT FALSE")
//    private Boolean isDeleted;
//    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
//    private List<OrderEntity> orderList;
//}