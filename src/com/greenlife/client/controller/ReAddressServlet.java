package com.greenlife.client.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.greenlife.dao.AdressInfoDao;
import com.greenlife.dao.UserInfoDao;
import com.greenlife.model.AdressInfo;
import com.greenlife.model.UserInfo;

public class ReAddressServlet extends HttpServlet{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 public void doGet(HttpServletRequest request, HttpServletResponse response)
	   throws ServletException, IOException {
	   doPost(request,response);
	 }

	 public void doPost(HttpServletRequest request, HttpServletResponse response)
	   throws ServletException, IOException {
	   //������Ӧ�����õı��뷽ʽ
	   request.setCharacterEncoding("UTF-8");
	   
	   String wechatId = "huangjianqiang";
	   //HttpSession session = request.getSession();
	   //String wechatId = session.getAttribute("wechatId");
	   
	   String receiverName =request.getParameter("iConsignee");
	   String receiverPhone =request.getParameter("iPhnoe");
	   String addrDetail1  =request.getParameter("iRegione");
	   String addrDetail2  =request.getParameter("iAddress");
	   StringBuffer addrDetail = new StringBuffer(addrDetail1+" "+ addrDetail2);
	   String zipAddress = request.getParameter("zipAddress");
	   String check =request.getParameter("iCheck");
	   
	   AdressInfo address = new AdressInfo();
	   address.setWechatId(wechatId);
	   address.setAddrZipcode(zipAddress); 
	   address.setReceiverName(receiverName);
	   address.setReceiverPhone(receiverPhone);
       address.setAddrDetail(addrDetail.toString());
       
       AdressInfoDao addressInfoDao = new AdressInfoDao();
       int addrid = addressInfoDao.addAdressInfo(address);
       
       if(addrid !=-1){
           //�޸�Ĭ�ϵ�ַ
           if(check!=null){
        	   UserInfoDao userInfoDao = new UserInfoDao();
        	   UserInfo userInfo = userInfoDao.getUserInfo(wechatId);
        	   userInfo.setAddrId(addrid);
        	   userInfoDao.updateUserInfo(userInfo);
           }
       }  
       
	  String forward = "/Client/page/home.jsp";
	  RequestDispatcher rd=request.getRequestDispatcher(forward);
	  rd.forward(request,response);
	 }
}