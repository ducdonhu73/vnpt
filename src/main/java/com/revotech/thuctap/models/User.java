package com.revotech.thuctap.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true, length = 8)
	private String code;
	private String name;
	private String phone_number;
	private String address;
	private String avatar;
	private int rank;
	private boolean admin;
	
	@OneToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Cart> carts;
}
