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
   	Object obj = parser.parse(get_param);
   	JSONObject jsonObj = (JSONObject)obj;
   	MyExerciseDAO myexerciseDAO = new MyExerciseDAO();
   	String rst = myexerciseDAO.insertMyExercise(jsonObj);
   	
   	//더미 값 일단 출력
   	out.println(rst);
   
%>