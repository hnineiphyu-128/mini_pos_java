package com.mmit.model.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mmit.model.entity.Item;

public class ItemService {

	private EntityManager em;
	
	public ItemService(EntityManager em) {
		// TODO Auto-generated constructor stub
		this.em = em;
	}

	public List<Item> findAll() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Item.getAll", Item.class).getResultList();
	}

	public void save(Item item) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		if (item.getId() == 0) {
			em.persist(item);
		}else {
			em.merge(item);
		}		
		em.getTransaction().commit();
	}

	public Item findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Item.class, id);
	}

	
	

}
