<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="DAO.UserDAO"%>
<%
	request.setCharacterEncoding("UTF-8");
	//파라미터를 통해 user 데이터를 받음
	String get_param = request.getParameter("user_data");
	//파서를 통해 파싱
	JSONParser parser = new JSONParser();
   	Object obj = parser.parse(get_param);
   	JSONObject jsonObj = (JSONObject)obj;

   	//update 성공여부를 메소드를 통해 전달받음.
   	UserDAO userDAO = new UserDAO();
   	String rst = userDAO.updateUserData(jsonObj);
   	
   	//성공여부  출력
   	out.println(rst);
   
%>