<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="DAO.ExerciseDAO"%>
<%
	request.setCharacterEncoding("UTF-8");
	String get_param = request.getParameter("board_param");
	
  	System.out.println("get_param : " + get_param);
   	JSONParser parser = new JSONParser();
   	Object obj = parser.parse(get_param);
   	JSONObject jsonObj = (JSONObject)obj;
   	System.out.println("test1 : " + jsonObj);
   	ExerciseDAO exerciseDAO = new ExerciseDAO();
   	String rst = exerciseDAO.insertExercise(jsonObj);
   	
   	//더미 값 일단 출력
   	out.println(rst);
   
%>