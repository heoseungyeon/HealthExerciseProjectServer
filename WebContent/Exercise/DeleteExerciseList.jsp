<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="DAO.MyExerciseDAO"%>
<%
	request.setCharacterEncoding("UTF-8");
	String deleteExerciseName = (String) request.getParameter("deleteExerciseName");
	out.println(deleteExerciseName);
	MyExerciseDAO myExerciseDAO = new MyExerciseDAO();
	String rst = myExerciseDAO.deleteMyExercise(deleteExerciseName);
	out.println(rst);
	
%>