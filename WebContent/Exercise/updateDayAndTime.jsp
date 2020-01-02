<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="DAO.MyExerciseDAO"%>
<%@page import="org.json.simple.JSONObject"%>

<%
   request.setCharacterEncoding("UTF-8");
	String get_param = request.getParameter("ID");   

   MyExerciseDAO myExercise = new MyExerciseDAO();
   JSONObject obj = myExercise.updateDayAndTime(get_param);
   out.println(obj.toString());
%>