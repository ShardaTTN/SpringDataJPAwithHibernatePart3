package com.tothenew.sharda.Entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Embedded
	private Address address;
	
	@OneToOne(mappedBy = "author")				//@OneToOne mapping
	private Book book;
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	@Column(name = "subjectlist")
	@ElementCollection(targetClass = Subject.class)
	private List<Subject> subjectlist;
	
	
	//Uncoomment to Use @OneToMany mapping
	//gets unidirectional if we don't use @ManytoOne in other relevant Entity class
//	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
//	private Set<Book> books;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "authors_books",
	joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
	private Set<Book> books;
	
	
	
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	public List<Subject> getSubjectlist() {
		return subjectlist;
	}
	public void setSubjectlist(List<Subject> subjectlist) {
		this.subjectlist = subjectlist;
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public void addBook(Book book) {
		if (book != null) {
			if (books == null) {
				books = new HashSet<>();
			}
			book.setAuthor(this);
			books.add(book);
		}
	}
}