
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="DAO.MyExerciseDAO"%>
<%@page import="org.json.simple.JSONArray"%>
<%
try{
	MyExerciseDAO myexercise = new MyExerciseDAO();
	String get_param = request.getParameter("user");
	
  	System.out.println("get_param : " + get_param);
  	// 내 운동리스트를 jsonArray형식으로 출력
	JSONArray list = myexercise.selectMyExerciseList(get_param);
	out.println(list.toString());
} catch(Exception e) {
}
%>