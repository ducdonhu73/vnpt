package com.revotech.thuctap.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, unique = true, length = 8)
	private String code;
	private String name;
	private String phoneNumber;
	private String address;
	private Date birthday;
	private boolean isSenior;
	private Long id_branch;
	private Long id_senior;
	public Employee() {
	}
	public Employee(Long id, String code, String name, String phoneNumber, String address, Date birthday,
			boolean isSenior, Long id_branch, Long id_senior) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.birthday = birthday;
		this.isSenior = isSenior;
		this.id_branch = id_branch;
		this.id_senior = id_senior;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public boolean isSenior() {
		return isSenior;
	}
	public void setSenior(boolean isSenior) {
		this.isSenior = isSenior;
	}
	public Long getId_branch() {
		return id_branch;
	}
	public void setId_branch(Long id_branch) {
		this.id_branch = id_branch;
	}
	public Long getId_senior() {
		return id_senior;
	}
	public void setId_senior(Long id_senior) {
		this.id_senior = id_senior;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", code=" + code + ", name=" + name + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", birthday=" + birthday + ", isSenior=" + isSenior + ", id_branch="
				+ id_branch + ", id_senior=" + id_senior + "]";
	}
}
