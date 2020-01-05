<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="DAO.MyExerciseDAO"%>
<%
	request.setCharacterEncoding("UTF-8");
	//파라 미터를 통해 데이터를 받음.
	String get_param = (String) request.getParameter("DeleteData");
	System.out.println("get_param : " + get_param);
	JSONParser parser = new JSONParser();
	//파서를 통해 파싱 후 jsonObject에 담음
	Object obj = parser.parse(get_param);
	JSONObject jsonObj = (JSONObject) obj;
	MyExerciseDAO myExerciseDAO = new MyExerciseDAO();
	//jsonObject를 매개변수로 메소드 실행
	String rst = myExerciseDAO.deleteMyExercise(jsonObj);
	out.println(rst);
%>