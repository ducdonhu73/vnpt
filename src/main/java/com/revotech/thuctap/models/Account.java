package com.revotech.thuctap.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@Column(nullable = false, unique = true, length = 10)
	private String code;
	@Column(nullable = false, unique = true, length = 20)
	private String username;
	@Column(nullable = false, unique = false, length = 16)
	private String password;
	public void update(Account newAccount) {
		if(newAccount.username!=null) this.username=newAccount.username;
		this.password=newAccount.password;
	}
}
