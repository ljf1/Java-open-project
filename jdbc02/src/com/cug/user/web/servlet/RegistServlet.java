package com.cug.user.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;

import com.cug.user.domain.User;
import com.cug.user.service.UserException;
import com.cug.user.service.UserService;

public class RegistServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		UserService registService = new UserService();
		User form = CommonUtils.toBean(req.getParameterMap(), User.class);
		
		Map<String, String> errors = new HashMap<String, String>();
		
		String username = form.getUsername();
		if(username==null || username.trim().isEmpty()){
			errors.put("username", "用户名不能为空");
		}else if(username.length()<3 || username.length()>15){
			errors.put("username","用户名的长度应该在3~15之间");
		}
		
		String password = form.getPassword();
		if(password==null || password.trim().isEmpty()){
			errors.put("password", "密码不能为空");
		}else if(password.length()<3 || password.length()>15){
			errors.put("password","密码的长度应该在3~15之间");
		}
		
		String repassword = form.getRepassword();
		if(repassword == null || repassword.trim().isEmpty()){
			errors.put("repassword","确认密码不能为空");
		}else if(!repassword.equals(password)){
			errors.put("repassword","两次输入的密码不一样");
		}
		
		int age = form.getAge();
		if(age < 10 || age > 90){
			errors.put("age","年龄应该在10~90之间");
		}
		
		String gender = form.getGender();
		if(gender == null || gender.trim().isEmpty()){
			errors.put("gender", "性别不能为空");
		}else if(!(gender.equals("男") || gender.equals("女"))){
			errors.put("gender", "性别应该为\"男\"or\"女\"");
		}
		
		String verifyCode = form.getVerifyCode();
		String session_vcode = (String)req.getSession().getAttribute("vcode");
//		if(verifyCode == null || verifyCode.trim().isEmpty()){
//			errors.put("verifyCode", "验证码不能为空");
//		}else if(!verifyCode.equalsIgnoreCase(session_vcode)){
//			errors.put("verifyCode","验证码输入有误");
//		}
		
		if(errors.size() > 0){
			System.out.println("errors");
			System.out.println(errors);
			req.setAttribute("errors", errors);
			req.setAttribute("form", form);
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}
		
		try {
			registService.register(form);
			resp.getWriter().print("注册成功，请登录");
		} catch (UserException e) {
			req.setAttribute("msg", e.getMessage());
			req.setAttribute("form", form);
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}
		
	}
}
