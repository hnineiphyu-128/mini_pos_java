package com.mmit.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: SaleDetail
 *
 */
@Entity
@Table(name = "saledetails")
public class SaleDetail implements Serializable {

	   
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int subQty;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "sale_id", referencedColumnName = "id")
	private Sale sale;
	
	@ManyToOne
	@JoinColumn(name = "item_id", referencedColumnName = "id")
	private Item item;
	
	
	private static final long serialVersionUID = 1L;

	public SaleDetail() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getSubQty() {
		return subQty;
	}
	public void setSubQty(int subQty) {
		this.subQty = subQty;
	}
	public Sale getSale() {
		return sale;
	}
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
 
	
}
