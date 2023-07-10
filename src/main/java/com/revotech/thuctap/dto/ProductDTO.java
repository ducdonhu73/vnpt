package com.revotech.thuctap.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class ProductDTO {
	private Long id;
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
