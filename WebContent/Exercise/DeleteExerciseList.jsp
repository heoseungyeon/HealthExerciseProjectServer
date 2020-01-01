<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="DAO.MyExerciseDAO"%>
<%
   request.setCharacterEncoding("UTF-8");
   String deleteExerciseName = (String) request.getParameter("deleteExerciseName");
   
    MyExerciseDAO myExerciseDAO = new MyExerciseDAO();
      String rst = myExerciseDAO.deleteMyExercise(deleteExerciseName);
      
      //더미 값 일단 출력
      //out.println(rst);
%>