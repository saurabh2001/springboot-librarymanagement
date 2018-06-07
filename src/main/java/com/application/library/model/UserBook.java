package com.application.library.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "User_Book", uniqueConstraints = @UniqueConstraint (columnNames = {"book_id","user_id"} ))
public class UserBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_book_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "issue_date")
	@Temporal(TemporalType.DATE)
	private Date issue_date;
	
	@Column(name = "return_due_date")
	@Temporal(TemporalType.DATE)
	private Date return_due_date;

	@Column(name = "actual_return_date")
	@Temporal(TemporalType.DATE)
	private Date actual_return_date;
	
	@Column(name = "FINE")
	private Integer fine;

	public UserBook(){
		
	}
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Date getIssue_date() {
		return issue_date;
	}


	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}


	public Date getReturn_due_date() {
		return return_due_date;
	}

	public void setReturn_due_date(Date return_due_date) {
		this.return_due_date = return_due_date;
	}

	public Date getActual_return_date() {
		return actual_return_date;
	}

	public void setActual_return_date(Date actual_return_date) {
		this.actual_return_date = actual_return_date;
	}

	public Integer getFine() {
		return fine;
	}

	public void setFine(Integer fine) {
		this.fine = fine;
	}


	@Override
	public String toString() {
		return "UserBook [id=" + id + ", book=" + book + ", user=" + user + ", issue_date=" + issue_date
				+ ", return_due_date=" + return_due_date + ", actual_return_date=" + actual_return_date + ", fine="
				+ fine + "]";
	}
	
	
	
}
