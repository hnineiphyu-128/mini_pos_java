package com.mmit.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mmit.model.entity.Item;
import com.mmit.model.service.CategoryService;
import com.mmit.model.service.ItemService;

@WebServlet(urlPatterns = {"/items","/add-item","/item-save","/edit-item"},loadOnStartup = 1)
public class ItemController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ItemService itemService;
	private CategoryService catService;
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("pos-jpql");
			getServletContext().setAttribute("emf", emf);
		}
		catService = new CategoryService(emf.createEntityManager());
		itemService = new ItemService(emf.createEntityManager());
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		 if ("/items".equals(path)) {
			 //retrieve item list from db
			 List<Item> list = itemService.findAll();
			 
			 //dd list obj to req
			 req.setAttribute("itemlists", list);
			 
			getServletContext().getRequestDispatcher("/items.jsp").forward(req, resp);
		} else if ("/add-item".equals(path) || "/edit-item".equals(path)) {
			String itemId = req.getParameter("itemId");
			System.out.println("Item id in edit item = "+ itemId);
			Item i = (itemId!=null && !itemId.isEmpty()) ? itemService.findById(Integer.parseInt(itemId)) : new Item();
			//add item to req
			req.setAttribute("item", i);
			System.out.println("Item in edit item = "+ i);
			getServletContext().getRequestDispatcher("/add-item.jsp").forward(req, resp);
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String path = req.getServletPath();
		
		if ("/item-save".equals(path)) {
			//get data from req
			String itemId = req.getParameter("itemId");
			String name = req.getParameter("name");
			String price = req.getParameter("price");
			String catId = req.getParameter("category");
			String exp_date = req.getParameter("exp_date");
			
			//create item obj
			Item item = (itemId.equals("0") ? new Item() : itemService.findById(Integer.parseInt(itemId)));
			item.setName(name);
			item.setPrice(Integer.parseInt(price));
			item.setExp_date(exp_date != null ? LocalDate.parse(exp_date) : null);
			item.setCategory(catService.findById(Integer.parseInt(catId)));
			
			//save it to db
			itemService.save(item);

			//invoke other resource to display
			resp.sendRedirect(req.getContextPath().concat("/items"));
		}
	}
	
	

	
}
