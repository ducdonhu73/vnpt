package com.revotech.thuctap.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    // this is "primary key"
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;
    // validate = constraint
    @Column(nullable = false, unique = true, length = 8)
    private String code;
    private int category_id;
    private String name;
    private String img;
    private long price_old;
    private long price_current;
    private String origin;
    private String sale_off_percent;
    private String sale_off_label;
    private String voucher;
    private boolean favorite;
    private int quantity;
    private int sold;
    private boolean display;
    private int liked;
    private Date year;
}
