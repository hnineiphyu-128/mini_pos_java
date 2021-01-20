package com.mmit.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.CascadeType.REMOVE;

/**
 * Entity implementation class for Entity: Item
 *
 */
@Entity
@Table(name = "items")
@NamedQuery(name = "Item.getAll", query = "SELECT i FROM Item i")
public class Item implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private int price;
	private LocalDate exp_date;
	
	@ManyToOne(fetch = LAZY, optional = false, cascade = REMOVE)
	@JoinColumn(name = "category_id") 
	private Category category;

	public Item() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDate getExp_date() {
		return exp_date;
	}

	public void setExp_date(LocalDate exp_date) {
		this.exp_date = exp_date;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
   
}
