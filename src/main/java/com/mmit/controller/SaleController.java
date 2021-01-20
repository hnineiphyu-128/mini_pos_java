package com.mmit.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import com.mmit.model.entity.Address;
import com.mmit.model.entity.Sale;
import com.mmit.model.entity.SaleDetail;
import com.mmit.model.entity.SaleDetailJSON;
import com.mmit.model.entity.Student;
import com.mmit.model.service.SaleService;
import com.mmit.utilities.JsonConverter;

@WebServlet(urlPatterns = {"/sales","/search","/sale-detail"},loadOnStartup = 1)
public class SaleController extends HttpServlet {

	private static final long serialVersionUID = 1L;
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
		saleService = new SaleService(emf.createEntityManager());
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
		if ("/sales".equals(path)) {
			List<Sale> list = saleService.findAll();
			
			req.setAttribute("sales", list);
			getServletContext().getRequestDispatcher("/sale-history.jsp").forward(req, resp);
		}else if ("/sale-detail".equals(path)) {
			getSaleDetail(req,resp);
		}
		
	}
	
	private void getSaleDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		// setup context type
		resp.setContentType("application/json;charset=UTF-8");

		
		String saleId = req.getParameter("id");
		System.out.println("-------------------------------");
		System.out.println("Sale id using ajax = "+saleId);
		
		Sale sale = saleService.findById(Integer.parseInt(saleId));
		System.out.println("-------------------------------");
		System.out.println("Saledetail id using ajax = "+sale.getDetailList().get(0).getItem().getName());
		
		List<SaleDetailJSON> details=new ArrayList<>();
		for(SaleDetail sd:sale.getDetailList()) {
			SaleDetailJSON s=new SaleDetailJSON(sd.getItem(),sd.getSubQty());
			details.add(s);
		}
		
		var converter = new JsonConverter(); 
		String output = converter.convertToJson(details);
		System.out.println("-------------------------------");
		System.out.println("Sale Detail = "+ output);
		resp.getWriter().print(output);
		 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		if ("".equals(path)) {
			
			String from = req.getParameter("from");
			String to = req.getParameter("to");
			
		}
		
	}

}
