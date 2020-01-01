<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="DAO.MyExerciseDAO"%>
<%
	request.setCharacterEncoding("UTF-8");
	String get_param = request.getParameter("My_exercise_data");
	
  	System.out.println("get_param : " + get_param);
   	JSONParser parser = new JSONParser();
   	MyExerciseDAO myexerciseDAO = new MyExerciseDAO();
   	String rst = myexerciseDAO.insertMyExercise(get_param);
   	
   	//더미 값 일단 출력
   	out.println(rst);
   
%>