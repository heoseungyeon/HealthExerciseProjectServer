package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class MyExerciseDAO {

   @SuppressWarnings("unchecked")
   public JSONArray selectMyExerciseList(String id) throws ClassNotFoundException {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      JSONArray jsonArray = new JSONArray();
      System.out.println(id);
      try {
         String sql = "select * from my_exercise where user_id =?";

         conn = DBConnection.getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1,id);
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

   public String insertMyExercise(JSONObject exercise) throws ClassNotFoundException, ParseException {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String rst = "success";

      try {
         conn = DBConnection.getConnection();

         String sql = "insert INTO my_exercise(health_name, user_id)" + "values ( ? , ? )";

         pstmt = conn.prepareStatement(sql);

         pstmt.setString(1, exercise.get("health_name").toString());
         pstmt.setString(2, exercise.get("user_id").toString());
 

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

   public String deleteMyExercise(JSONObject deleteData) throws ClassNotFoundException, ParseException {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String rst = "success";

      try {
         conn = DBConnection.getConnection();

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