<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="DAO.MyExerciseDAO"%>
<%@page import="org.json.simple.JSONObject"%>

<%
   request.setCharacterEncoding("UTF-8");
      String user_id = (String) request.getParameter("user_id");
   

   MyExerciseDAO myExercise = new MyExerciseDAO();
   JSONObject obj = myExercise.updateDayAndTime(user_id);
   out.println(obj.toString());
%>