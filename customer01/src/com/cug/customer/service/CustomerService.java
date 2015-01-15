package com.cug.customer.service;

import java.sql.SQLException;
import java.util.List;

import com.cug.customer.dao.CustomerDao;
import com.cug.customer.domain.Customer;
import com.cug.customer.domain.PageBean;

public class CustomerService {
	private CustomerDao customerDao = new CustomerDao();
	
	public void add(Customer cus) throws SQLException{
		customerDao.add(cus);
	}
	
	public PageBean<Customer> findAll(int pc, int ps) throws SQLException{
		return customerDao.findAll(pc, ps);
	}
	
	public Customer load(String cid){
		return customerDao.load(cid);
	}
	
	public void edit(Customer cus) throws SQLException{
		customerDao.edit(cus);
	}

	public void delete(String cid) {
		customerDao.delete(cid);
	}

	public PageBean<Customer> query(Customer cus,int pc, int ps) throws SQLException {
		return customerDao.query(cus, pc, ps);
	}
}
