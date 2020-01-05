package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

//운동 시 user 테이블에 접근하는 클래스 
public class UserDAO {
   
   // 운동 종료 시 user 테이블의 운동 총 시간과 총 날짜를 갱신 시켜주는 Method
   public String updateUserData(JSONObject user_data) throws ClassNotFoundException, ParseException {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String rst = "success";

      try {
         conn = DBConnection.getConnection();

            String sql = "Update user SET " + "user_total_day = user_total_day + 1 , user_total_time = user_total_time + ?"
            		+ " WHERE user_id = ? ";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,user_data.get("user_time").toString());
            pstmt.setString(2,user_data.get("user_id").toString());

      
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