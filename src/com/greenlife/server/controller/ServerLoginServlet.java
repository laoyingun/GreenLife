package com.greenlife.server.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.greenlife.dao.AdminListDao;

public class ServerLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServerLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * 接受登录表单中的账户和密码
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		System.out.println(userId+password);
		if (AdminListDao.loginCheck(userId, password)) {// 登录成功
			session.setAttribute("login", true);/// 保存session。确保登录

			response.sendRedirect("/GreenLife/Server/Page/product.jsp");
		} else {
			session.setAttribute("userId", userId);
			session.setAttribute("error", "用户名或密码错误");
			response.sendRedirect("/GreenLife/Server/Page/login.jsp");
		}

	}

}
