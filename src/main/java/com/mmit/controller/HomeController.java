package com.mmit.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mmit.model.entity.Category;
import com.mmit.model.entity.Item;
import com.mmit.model.entity.Sale;
import com.mmit.model.entity.SaleDetail;
import com.mmit.model.service.CategoryService;
import com.mmit.model.service.ItemService;
import com.mmit.model.service.SaleService;

@WebServlet(urlPatterns = {"/home","/add-to-cart","/cart-action"}, loadOnStartup = 1)
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CategoryService catService;
	private ItemService itemService;
	private SaleService saleService;
	
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
		saleService = new SaleService(emf.createEntityManager());
		
		//get category lists from db
		List<Category> list = catService.findAll();
		getServletContext().setAttribute("categories", list);
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
		if ("/add-to-cart".equals(path)) {
			addToCart(req);
		}
		getAllItems(req);
		getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
	}

	private void getAllItems(HttpServletRequest req) {
		// TODO Auto-generated method stub
		List<Item> list = itemService.findAll();
		req.setAttribute("itemlists", list);
	}

	private void addToCart(HttpServletRequest req) {
		// TODO Auto-generated method stub
		//get current item add to cart
		String itemId = req.getParameter("id");
		int currentitemid = Integer.parseInt(itemId);
		
		//get cart info (saleRecord) from session
		HttpSession session = req.getSession(true);
		Sale sale = (Sale) session.getAttribute("cart");
		if (sale == null) {
			sale = new Sale();
		}
		
		//verify current item is new or not?
		List<SaleDetail> saleitemList = sale.getDetailList(); 
		boolean alreadyExist = true;
		for(SaleDetail sd: saleitemList) {
			if (sd.getItem().getId() == currentitemid) {
				sd.setSubQty(sd.getSubQty()+1);
				alreadyExist = false;
				break;
			}
		}
		
		//new sale item
		if (alreadyExist) {
			 Item item = itemService.findById(currentitemid);
			 SaleDetail new_sale_item = new SaleDetail();
			 new_sale_item.setItem(item);
			 new_sale_item.setSubQty(1);
		
			 sale.addSaleItem(new_sale_item);
		}
		
		session.setAttribute("cart", sale);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = req.getParameter("btnAction");
		HttpSession session = req.getSession(true);
		
		if ("Paid".equals(action)) {
			Sale sale = (Sale) session.getAttribute("cart");

			System.out.println("sale" + sale.getDetailList());
			if (sale != null && !sale.getDetailList().isEmpty()) {
				//save to db
				saleService.save(sale);
				
				
			}
			
		} 
		
		//clear sale item form cart(sale-record)
		session.removeAttribute("cart");
		
		resp.sendRedirect(req.getContextPath().concat("/home"));
	}
}
