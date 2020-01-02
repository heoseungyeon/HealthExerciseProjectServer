package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class LoginDAO {
	@SuppressWarnings("unchecked")
	public String loginUser(JSONObject registUser) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "NOTFOUND";
		String id;
		String pw;

		conn = DBConnection.getConnection();

		String sql = "select user_id,user_pw from user " + "where user_id = ? AND user_pw =  ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, (registUser.get("user_id")).toString());
			pstmt.setString(2, (registUser.get("user_pw")).toString());

			rs = pstmt.executeQuery();

			while (rs.next()) { // Position the cursor 4
				id = rs.getString(1);
				pw = rs.getString(2);
				if (id.equals(registUser.get("user_id").toString()) && pw.equals(registUser.get("user_pw").toString())) {
					rst = "success";
				}
			}
			rs.close(); // Close the ResultSet 5
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rst = "fail";
		}

		

		System.out.println(rst);
		return rst;
	}
}