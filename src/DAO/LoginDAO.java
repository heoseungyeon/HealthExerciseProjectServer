package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
//Login 관련하여 User 테이블에 접근하는 클래스 
public class LoginDAO {
	@SuppressWarnings("unchecked")
	//등록 유저 데이터를 받아 id와 pw 일치여부를 확인하고 성공여부를 반환하는  Method 
	public String loginUser(JSONObject registUser) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rst = "NOTFOUND";
		String id;
		String pw;

		conn = DBConnection.getConnection();
		//select SQL Query ( id,pw를 조건문으로 하여 검색 함 ) 
		String sql = "select user_id,user_pw from user " + "where user_id = ? AND user_pw =  ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, (registUser.get("user_id")).toString());
			pstmt.setString(2, (registUser.get("user_pw")).toString());

			rs = pstmt.executeQuery();

			// 일치하는 아이디 및 패스워드를 확인함 . 없으면 "fail"
			while (rs.next()) { 
				id = rs.getString(1);
				pw = rs.getString(2);
				if (id.equals(registUser.get("user_id").toString()) && pw.equals(registUser.get("user_pw").toString())) {
					rst = "success";
				}
			}
			rs.close(); 
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