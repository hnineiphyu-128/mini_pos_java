package com.mmit.model.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import com.mmit.model.entity.Sale;

public class SaleService {
	
	private EntityManager em;
	
	public SaleService(EntityManager em) {
		this.em = em;
	}

	public void save(Sale salerecord) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		salerecord.setSale_date(LocalDate.now());
		System.out.println("Sale Record in sale service = "+salerecord);
		em.persist(salerecord);
		em.getTransaction().commit();
		
	}

	public List<Sale> findAll() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Sale.getAll", Sale.class).getResultList();
	}

	public Sale findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Sale.class, id);
	}

}
