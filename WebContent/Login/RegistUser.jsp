<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="DAO.RegistDAO"%>
<%
	request.setCharacterEncoding("UTF-8");
	//회원가입 정보를 파라미터를 통해 받음
	String get_param = request.getParameter("User_data");
	
  	System.out.println("get_param : " + get_param);
  	//파서를 통해 파싱 
   	JSONParser parser = new JSONParser();
   	Object obj = parser.parse(get_param);
   	JSONObject jsonObj = (JSONObject)obj;
   	System.out.println("test1 : " + jsonObj);
   	RegistDAO registDAO = new RegistDAO();
   	String rst = registDAO.registUser(jsonObj);
   	
   	//회원가입 성공 여부 출력
   	out.println(rst);
   
%>