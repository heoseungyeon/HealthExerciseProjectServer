
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="DAO.ExerciseDAO"%>
<%@page import="org.json.simple.JSONArray"%>
<%
try{
	ExerciseDAO exercise = new ExerciseDAO();
	JSONArray list = exercise.selectExerciseList();
	out.println(list.toString());
} catch(Exception e) {
}
%>