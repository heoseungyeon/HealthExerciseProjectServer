<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="DAO.MyExerciseDAO"%>
<%
	request.setCharacterEncoding("UTF-8");
	//파라미터를 통해 데이터를 받음.
	String get_param = request.getParameter("My_exercise_data");
	
  	System.out.println("get_param : " + get_param);
   	JSONParser parser = new JSONParser();
	//파서를 통해 파싱 후 jsonObject에 담음
   	Object obj = parser.parse(get_param);
   	JSONObject jsonObj = (JSONObject)obj;
   	//json Object 를 매개변수로 메소드 실행 
   	MyExerciseDAO myexerciseDAO = new MyExerciseDAO();
   	String rst = myexerciseDAO.insertMyExercise(jsonObj);
   	
   	//성공 여부 반환 
   	out.println(rst);
   
%>