package com.mmit.model.service;

import java.util.List;

import javax.persistence.EntityManager;

import com.mmit.model.entity.Category;

public class CategoryService {

	private EntityManager em;
	
	public CategoryService(EntityManager em) {
		this.em = em;
	}

	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Category.getAll", Category.class).getResultList();
	}

	public Category findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Category.class, id);
	}
}
