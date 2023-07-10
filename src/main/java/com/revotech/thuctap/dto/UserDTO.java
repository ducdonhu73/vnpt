package com.revotech.thuctap.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
	private long id;
	private String name;
	private String phone_number;
	private String address;
	private String avatar;
	private AccountDTO account;
	private boolean isAdmin;
	private int rank;
	private List<CartDTO> cart;
//	private List<Notifies> notifies;
//	private List<String> history_sreach;
}
