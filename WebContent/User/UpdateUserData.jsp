<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="DAO.UserDAO"%>
<%
	request.setCharacterEncoding("UTF-8");
	String get_param = request.getParameter("Exercise_time");
	
  	System.out.println("get_param : " + get_param);
   	UserDAO userDAO = new UserDAO();
   	String rst = userDAO.updateUserData(get_param);
   	
   	//더미 값 일단 출력
   	out.println(rst);
   
%>