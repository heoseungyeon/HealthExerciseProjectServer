package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RegistDAO {

	@SuppressWarnings("unchecked")
	public String registUser(JSONObject registUser) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "success";

		try {
			conn = DBConnection.getConnection();

			String sql = "insert INTO user(user_id, user_pw,user_total_day,user_total_time)"
					+ "values (?, ?, 0, 0)";

			pstmt = conn.prepareStatement(sql);

		

				pstmt.setString(1, (registUser.get("user_id")).toString());
				pstmt.setString(2, (registUser.get("user_pw")).toString());

		
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
		return rst;
	}
}