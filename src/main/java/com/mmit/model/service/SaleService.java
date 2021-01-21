package com.mmit.model.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.jboss.logging.Param;

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

	public List<Sale> search(String from, String to) {
		// TODO Auto-generated method stub
		//dymanic query dynamic search
		StringBuffer sb = new StringBuffer("SELECT s FROM Sale s WHERE 5=5");
		Map<String, Object> params = new HashMap<String, Object>();
		if(from != null && !from.isEmpty()) {
			sb.append(" AND s.sale_date >= :f");
			params.put("f", LocalDate.parse(from));
		}
		
		if (to != null && !to.isEmpty()) {
			sb.append(" AND s.sale_date <= :t");
			params.put("t", LocalDate.parse(to));
		}
//		System.out.println(sb.toString());
		
		TypedQuery<Sale> query = em.createQuery(sb.toString(),Sale.class);
		for(Entry<String, Object> entry: params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}

}
