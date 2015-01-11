package com.cug.user.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;

import com.cug.user.domain.User;
import com.cug.user.service.UserException;
import com.cug.user.service.UserService;

public class LoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService service = new UserService();
		
		User form = CommonUtils.toBean(req.getParameterMap(), User.class);
		try {
			User user = service.login(form);
			req.getSession().setAttribute("session_user", user);
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
		} catch (UserException e) {
			req.setAttribute("msg", e.getMessage());
			req.setAttribute("form", form);
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
}
