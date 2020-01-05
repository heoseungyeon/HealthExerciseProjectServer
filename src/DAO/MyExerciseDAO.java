package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
//my_exercise 테이블에 접근하는 클래스 
public class MyExerciseDAO {

	@SuppressWarnings("unchecked")
	//my_exercise 테이블의 내용을 검색하여 JSONArray형태로 반환하는 Method
	public JSONArray selectMyExerciseList(String id) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray jsonArray = new JSONArray();
		System.out.println(id);
		try {
			//user_id 를 통한 검색 Query
			String sql = "select * from my_exercise where user_id =?";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("no", rs.getString("my_exercise_no"));
				jsonObject.put("name", rs.getString("health_name"));
				jsonArray.add(jsonObject);
				jsonObject = null;

			}
		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return jsonArray;
	}

	//my_exercise 테이블에 같은 운동이 있는지 확인한 뒤 데이터를 입력하는 함수
	public String insertMyExercise(JSONObject exercise) throws ClassNotFoundException, ParseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "success";

		try {
			conn = DBConnection.getConnection();

			//입력 유저의 운동리스트를 가져옴
			JSONArray jsonArray = new JSONArray();
			jsonArray = selectMyExerciseList(exercise.get("user_id").toString());

			//중복된 운동이 있는 지 확인
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				if (jsonObject.get("name").toString().contains(exercise.get("health_name").toString())) {
					rst = "fail";
				}
			}
			//중복 운동이 없으면 입력 Query를 통해 실행
			if(rst.contains("success"))
			{
				String sql = "insert INTO my_exercise(health_name, user_id)" + "values ( ? , ? )";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, exercise.get("health_name").toString());
				pstmt.setString(2, exercise.get("user_id").toString());

				pstmt.executeUpdate();
			}
			

		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
			rst = sqle.getMessage();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				rst = "fail";
			}
		}
		return rst;
	}

	//내 운동 리스트에서 입력받은 데이터를 삭제하는 Method
	public String deleteMyExercise(JSONObject deleteData) throws ClassNotFoundException, ParseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "success";

		try {
			conn = DBConnection.getConnection();
			//삭제 Query
			String sql = "Delete from my_exercise where health_name = ? AND user_id = ? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, deleteData.get("health_name").toString());
			pstmt.setString(2, deleteData.get("user_id").toString());

			pstmt.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
			rst = sqle.getMessage();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				rst = "fail";
			}
		}
		System.out.println(rst);
		return rst;
	}

	// 데모버젼에서 user_id 없이 운동한 총 날짜와 시간을 갱신 시켜주기 위한 Method
	public JSONArray updateDayAndTime() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "success";
		JSONArray jsonArray = new JSONArray();

		try {
			conn = DBConnection.getConnection();

			String sql1 = "select user_total_day from user";
			String sql2 = "select user_total_time from user";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("user_total_day", rs.getString("user_total_day"));
				jsonArray.add(jsonObject);
				jsonObject = null;
			}

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("user_total_time", rs.getString("user_total_time"));
				jsonArray.add(jsonObject);
				jsonObject = null;
			}

		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
			rst = sqle.getMessage();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				rst = "fail";
			}
			return jsonArray;
		}
	}

	//user_id 를 받아 해당 유저의 총운동시간,총운동 날짜를 검색하여 가져오는 Method
	public JSONObject updateDayAndTime(String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "success";
		JSONObject jsonObject = new JSONObject();

		try {
			conn = DBConnection.getConnection();

			String sql1 = "select user_total_day from user where user_id = ?";
			String sql2 = "select user_total_time from user where user_id = ?";

			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				jsonObject.put("user_total_day", rs.getString("user_total_day"));
			}

			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				jsonObject.put("user_total_time", rs.getString("user_total_time"));
			}

		} catch (SQLException sqle) {
			System.out.println("sql err : " + sqle.getMessage());
			rst = sqle.getMessage();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				rst = "fail";
			}
			System.out.println("DAO jsonObject " + jsonObject);
			return jsonObject;
		}
	}

}