package com.cug.customer.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;

import com.cug.customer.domain.Customer;
import com.cug.customer.domain.PageBean;

public class CustomerDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public void add(Customer cus){
		String sql = "insert into t_customer values(?,?,?,?,?,?,?)";
		Object[] params = {cus.getCid(),cus.getCname(),cus.getGender(),cus.getBirthday(),
				cus.getCellphone(),cus.getEmail(),cus.getDescription()};
		try {
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public PageBean<Customer> findAll(int pc, int ps){
		try {
			PageBean<Customer> pb = new PageBean<Customer>();
			pb.setPc(pc);
			pb.setPs(ps);
			
			String sql = "select count(*) from t_customer";
			Number number = (Number)qr.query(sql, new ScalarHandler());
			int tr = number.intValue();
			pb.setTr(tr);
			
			sql = "select * from t_customer order by cname limit ?,?";
			List<Customer> beanList = qr.query(sql,new BeanListHandler<Customer>(Customer.class),
					(pc-1)*ps, ps);
			pb.setBeanList(beanList);
			
			return pb;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Customer load(String cid){
		
		try {
			String sql = "select * from t_customer where cid=?";
			return qr.query(sql,new BeanHandler<Customer>(Customer.class),cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void edit(Customer cus){
		String sql = "update t_customer set cname=?,gender=?,birthday=?,cellphone=?,email=?,description=?"
				+ "  where cid=?";
		Object[] params = {cus.getCname(),cus.getGender(),cus.getBirthday(),
				cus.getCellphone(),cus.getEmail(),cus.getDescription(),cus.getCid()};
		try {
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(String cid) {
		String sql = "delete from t_customer where cid=?";
		try {
			qr.update(sql,cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

//	public List<Customer> query(Customer cus) {
//		StringBuilder sql = new StringBuilder("select * from t_customer where 1=1");
//		List<String> params = new ArrayList<String>();
//		
//		String cname = cus.getCname();
//		if(cname!=null && !cname.trim().isEmpty()){
//			sql.append(" and cname like ?");
//			params.add("%"+cname+"%");
//		}
//		
//		String gender = cus.getGender();
//		if(gender!=null && !gender.trim().isEmpty()){
//			sql.append(" and gender=?");
//			params.add(gender);
//		}
//		
//		String cellphone = cus.getCellphone();
//		if(cellphone!=null && !cellphone.trim().isEmpty()){
//			sql.append("and cellphone like ?");
//			params.add("%"+cellphone+"%");
//		}
//		
//		String email = cus.getEmail();
//		if(email!=null && !email.trim().isEmpty()){
//			sql.append("and email like ?");
//			params.add("%"+email+"%");
//		}
//		
//		try {
//			return qr.query(sql.toString(), new BeanListHandler<Customer>(Customer.class),
//					params.toArray());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	public PageBean<Customer> query(Customer cus, int pc, int ps) {
		PageBean<Customer> pb = new PageBean<Customer>();
		pb.setPc(pc);
		pb.setPs(ps);
		
		StringBuilder cntSql = new StringBuilder("select count(*) from t_customer");
		StringBuilder whereSql = new StringBuilder(" where 1=1");
		
		List<Object> params = new ArrayList<Object>();
		
		String cname = cus.getCname();
		if(cname!=null && !cname.trim().isEmpty()){
			whereSql.append(" and cname like ?");
			params.add("%"+cname+"%");
		}
		
		String gender = cus.getGender();
		if(gender!=null && !gender.trim().isEmpty()){
			whereSql.append(" and gender=?");
			params.add(gender);
		}
		
		String cellphone = cus.getCellphone();
		if(cellphone!=null && !cellphone.trim().isEmpty()){
			whereSql.append(" and cellphone like ?");
			params.add("%"+cellphone+"%");
		}
		
		String email = cus.getEmail();
		if(email!=null && !email.trim().isEmpty()){
			whereSql.append("and email like ?");
			params.add("%"+email+"%");
		}
		
		Number number = null;
		try {
			number = (Number)qr.query(cntSql.append(whereSql).toString(),
					new ScalarHandler(),params.toArray());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		int tr = number.intValue();
		pb.setTr(tr);
		
		StringBuilder sql = new StringBuilder("select * from t_customer");
		StringBuilder limitSql = new StringBuilder(" limit ?,?");
		params.add((pc-1)*ps);
		params.add(ps);
		
		List<Customer> beanList = null;
		try {
			beanList = qr.query(sql.append(whereSql).append(limitSql).toString(),
					new BeanListHandler<Customer>(Customer.class),params.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pb.setBeanList(beanList);
		
		return pb;	
	
	}
}
