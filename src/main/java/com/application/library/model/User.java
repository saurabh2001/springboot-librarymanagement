package com.application.library.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "User")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	
	@Column(name = "email", nullable = false)
	private String useremail;

	
	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "active")
	private boolean active;

	@CreationTimestamp
	@Column(name = "create_date", nullable = false, updatable = false)
	private Date createDate;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	List<UserBook> myIssuedBooks = new ArrayList<UserBook>();

	public User() {

	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	public List<UserBook> getMyIssuedBooks() {
		return myIssuedBooks;
	}

	public void setMyIssuedBooks(List<UserBook> myIssuedBooks) {
		this.myIssuedBooks = myIssuedBooks;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", useremail=" + useremail + ", password=" + password + ", active=" + active
				+ ", createDate=" + createDate + "]\n";
	}

	
}
