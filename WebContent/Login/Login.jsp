<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="DAO.LoginDAO"%>
<%
	request.setCharacterEncoding("UTF-8");
	//파라미터를 통해 데이터 받음
	String get_param = request.getParameter("User_data");
	
  	System.out.println("get_param : " + get_param);
  	//파서를 통해 데이터 파싱 
   	JSONParser parser = new JSONParser();
   	Object obj = parser.parse(get_param);
   	JSONObject jsonObj = (JSONObject)obj;
   	System.out.println("test1 : " + jsonObj);
   	//로그인 메소드 실행 후 성공여부 반환
   	LoginDAO loginDAO = new LoginDAO();
   	String rst = loginDAO.loginUser(jsonObj);
   	
   	//성공  여부 반환 
   	out.println(rst);
   
%>