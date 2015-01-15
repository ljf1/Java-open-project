package com.cug.customer.web.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import com.cug.customer.domain.Customer;
import com.cug.customer.domain.PageBean;
import com.cug.customer.service.CustomerService;

public class CustomerServlet extends BaseServlet{
	private CustomerService customerService = new CustomerService();
	
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException{
		Customer cus = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		cus.setCid(CommonUtils.uuid());
		customerService.add(cus);
		request.setAttribute("msg", "用户添加成功");
		return "f:/msg.jsp";
	}
	
//	public String findAll(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException, SQLException{
//		request.setAttribute("cstList", customerService.findAll());
//		return "f:/list.jsp";
//	}
	
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException{
		int pc = getPc(request);
		int ps = 10;
		PageBean<Customer> pb = customerService.findAll(pc,ps);
		pb.setUrl(getUrl(request));
		request.setAttribute("pb", pb);
		return "f:/list.jsp";
	}
	
	private String getUrl(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		String queryString = request.getQueryString();
		
		System.out.println(contextPath);
		System.out.println(servletPath);
		System.out.println(queryString);
		
		if(queryString.contains("&pc=")){
			int index = queryString.lastIndexOf("&pc=");
			queryString = queryString.substring(0, index);
		}
		return contextPath + servletPath + "?" + queryString;
	}

	private int getPc(HttpServletRequest request) {
		String value = request.getParameter("pc");
		if(value == null || value.trim().isEmpty()){
			return 1;
		}
		return Integer.parseInt(value);
	}

	public String preEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException{
		String cid = (String)request.getParameter("cid");
		Customer cstm = customerService.load(cid);
		request.setAttribute("cstm", cstm);
		return "f:/edit.jsp";
	}
	
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException{
		Customer cus = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		customerService.edit(cus);
		request.setAttribute("msg", "修改用户信息成功");
		return "f:/msg.jsp";
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException{
		Customer cus = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		String cid = request.getParameter("cid");
		customerService.delete(cid);
		request.setAttribute("msg", "删除用户成功");
		return "f:/msg.jsp";
	}
	
//	public String query(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException, SQLException{
//		Customer cus = CommonUtils.toBean(request.getParameterMap(), Customer.class);
//		List<Customer> cstList = customerService.query(cus);
//		System.out.println(cstList);
//		request.setAttribute("cstList", cstList);
//		return "f:/list.jsp";
//	}
	
	public String query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException{
		
		Customer cus = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		cus = encoding(cus);
		int pc = getPc(request);
		int ps = 10;
		PageBean<Customer> pb = customerService.query(cus,pc,ps);
		pb.setUrl(getUrl(request));
		request.setAttribute("pb", pb);
		return "f:/list.jsp";
	}

	private Customer encoding(Customer cus) throws UnsupportedEncodingException {
		String cname = cus.getCname();
		String gender = cus.getGender();
		String cellphone = cus.getCellphone();
		String email = cus.getEmail();
		
		if(cname != null && !cname.trim().isEmpty()){
			cname = new String(cname.getBytes("ISO-8859-1"),"UTF-8");
			cus.setCname(cname);
		}
		
		if(gender != null && !gender.trim().isEmpty()){
			gender = new String(gender.getBytes("ISO-8859-1"),"UTF-8");
			cus.setGender(gender);
		}
		
		if(cellphone != null && !cellphone.trim().isEmpty()){
			cellphone = new String(cellphone.getBytes("ISO-8859-1"),"UTF-8");
			cus.setCellphone(cellphone);
		}
		
		if(email != null && !email.trim().isEmpty()){
			email = new String(email.getBytes("ISO-8859-1"),"UTF-8");
			cus.setEmail(email);
		}
		
		return cus;
	}
	

}
