package com.cug.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cug.user.domain.User;

public class jdbcUserDaoImpl implements UserDao{

	@Override
	public void addUser(User user) {
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = jdbcUtils.getConnection();
			
			pst = conn.prepareStatement("insert into user values(?,?,?,?)");
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setInt(3, user.getAge());
			pst.setString(4, user.getGender());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(pst != null)	pst.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}		
	}

	@Override
	public User findByUsername(String username) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = jdbcUtils.getConnection();
			pst = conn.prepareStatement("select * from user where username=?");
			pst.setString(1, username);
			
			rs = pst.executeQuery();
			if(rs == null)
				return null;
			User user = new User();
			if(rs.next()){
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAge(rs.getInt("age"));
				user.setGender("gender");
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{			
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
