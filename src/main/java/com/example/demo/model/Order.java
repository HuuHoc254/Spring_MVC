//package com.example.demo.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "orders")
//public class OrderEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer orderId;
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private ProductEntity product;
//    @Column( name = "unit_price")
//    private Double unitPrice;
//    @Column( name = "quantity")
//    private Integer quantity;
//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private CustomerEntity customer;
//    @Column( name = "address_customer")
//    private String addressCustomer;
//    @Column( name = "phone_number_customer")
//    private String phoneNumberCustomer;
//    @ManyToOne
//    @JoinColumn(name = "account_id")
//    private AccountEntity account;
//    @Column(name = "order_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
//    private LocalDateTime orderDate;
//    @Column(name = "allocation_date", columnDefinition = "DATETIME DEFAULT NULL")
//    private LocalDateTime allocationDate;
//    @ManyToOne
//    @JoinColumn(name = "order_status_id")
//    private OrderStatusEntity orderStatus;
//    @Column( columnDefinition = "INT DEFAULT 0")
//    private Integer version;
//    @Column( columnDefinition = "BOOLEAN DEFAULT FALSE")
//    private Boolean isDeleted;
//}