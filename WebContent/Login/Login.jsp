<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="DAO.LoginDAO"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
try{
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("user_id");
	String pw = request.getParameter("user_pw");
	
	LoginDAO login = new LoginDAO();
	JSONObject loginObject = new JSONObject();
	loginObject.put("user_id", id);
	loginObject.put("user_pw", pw);
	 
	JSONArray arr = login.checkID(loginObject);
	out.println(arr.toString());
} catch(Exception e) {
}
%>
</body>
</html>