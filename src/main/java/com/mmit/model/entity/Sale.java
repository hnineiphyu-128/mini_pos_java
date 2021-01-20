package com.mmit.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.CascadeType.PERSIST;

/**
 * Entity implementation class for Entity: Sale
 *
 */
@Entity
@Table(name = "sales")
@NamedQuery(name = "Sale.getAll", query = "SELECT s FROM Sale s")
public class Sale implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate sale_date;
	
	@OneToMany(mappedBy = "sale", cascade = PERSIST)
	private List<SaleDetail> detailList = new ArrayList<SaleDetail>();
	
	public void addSaleItem(SaleDetail s) {
		s.setSale(this);
		detailList.add(s);
	}
	
	//getter method
	public int getSubTotal() {
		return detailList.stream().mapToInt(sd->sd.getSubQty() * sd.getItem().getPrice()).sum();
	}
	
	public double getTax() {
		return getSubTotal() * 0.05;
	}
	public double getTotal() {
		return getSubTotal() + getTax();
	}
	
	public int getTotalQty() {
		return detailList.stream().mapToInt(sd->sd.getSubQty()).sum();
	}
	private static final long serialVersionUID = 1L;

	public Sale() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getSale_date() {
		return sale_date;
	}
	public void setSale_date(LocalDate sale_date) {
		this.sale_date = sale_date;
	}
	public List<SaleDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<SaleDetail> detailList) {
		this.detailList = detailList;
	}
	
	
	
}
