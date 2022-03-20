package com.example.demo.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book_details")
public class BookDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 50, nullable = false)
	private String name;
	
	@Column(length = 50, nullable = false)
	private String value;
	
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
	
	
	
	public BookDetails(Integer id, String name, String value, Book book) {
		this.id = id;
		this.name = name;
		this.value = value;
		this.book = book;
	}

	public BookDetails() {
		
	}
	
	public BookDetails(String name, String value, Book book) {
		this.name = name;
		this.value = value;
		this.book = book;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return name + "=" + value;
	}
	
	
}
