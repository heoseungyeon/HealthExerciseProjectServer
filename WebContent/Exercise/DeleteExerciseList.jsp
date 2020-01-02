<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="DAO.MyExerciseDAO"%>
<%
	request.setCharacterEncoding("UTF-8");
	String get_param = (String) request.getParameter("DeleteData");
	System.out.println("get_param : " + get_param);
	JSONParser parser = new JSONParser();
	Object obj = parser.parse(get_param);
	JSONObject jsonObj = (JSONObject) obj;
	MyExerciseDAO myExerciseDAO = new MyExerciseDAO();
	String rst = myExerciseDAO.deleteMyExercise(jsonObj);
	out.println(rst);
%>