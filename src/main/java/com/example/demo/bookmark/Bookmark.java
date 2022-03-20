package com.example.demo.bookmark;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Bookmark {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 30, nullable = false, unique = true)
	private String name;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "bm_identity")
	private BookmarkIdentity bookmarkidentity;
	
	public Bookmark() {}
	
	public Bookmark(String name, BookmarkIdentity bookmarkidentity) {
		super();
		this.name = name;
		this.bookmarkidentity = bookmarkidentity;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BookmarkIdentity getBookmarkidentity() {
		return bookmarkidentity;
	}
	public void setBookmarkidentity(BookmarkIdentity bookmarkidentity) {
		this.bookmarkidentity = bookmarkidentity;
	}

	
}
