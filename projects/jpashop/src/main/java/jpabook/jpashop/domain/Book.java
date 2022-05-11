package jpabook.jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Book extends Item {

	private String authors;
	private String isbn;

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
