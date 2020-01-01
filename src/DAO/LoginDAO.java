package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class LoginDAO {
   String id;
   String pw;

   JSONArray loginCheckArray = new JSONArray();
   public JSONArray checkID(JSONObject loginObject) throws ClassNotFoundException, ParseException {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      try {
         conn = DBConnection.getConnection();

         String sql = "select user_id from user where user_id = ? && user_pw = ?";

         pstmt = conn.prepareStatement(sql);

         pstmt.setString(1, (loginObject.get("user_id")).toString());
         pstmt.setString(2, (loginObject.get("user_pw")).toString());

         rs = pstmt.executeQuery();
         if (rs.next()) {
            loginObject.put("check", rs.getString("check"));
            loginCheckArray.add(loginObject);

         }

      } catch (SQLException sqle) {
         System.out.println("sql err : " + sqle.getMessage());
         sqle.printStackTrace();
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
      return loginCheckArray;
   }
}