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
   public JSONArray selectMyExerciseList() throws ClassNotFoundException {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      JSONArray jsonArray = new JSONArray();

      try {
         String sql = "select * from my_exercise";

         conn = DBConnection.getConnection();
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("no", rs.getString("user_no"));
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

   public String insertMyExercise(String exercise) throws ClassNotFoundException, ParseException {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String rst = "success";

      try {
         conn = DBConnection.getConnection();

         String sql = "insert INTO my_exercise(health_name)" + "values (?)";

         pstmt = conn.prepareStatement(sql);

         pstmt.setString(1, exercise.toString());
 

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

   public String deleteMyExercise(String deleteExerciseName) throws ClassNotFoundException, ParseException {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String rst = "success";

      try {
         conn = DBConnection.getConnection();

         String sql = "Delete from my_exercise where health_name = ?";
         pstmt = conn.prepareStatement(sql);

         pstmt.setString(1, deleteExerciseName); // (exercise.get("name")).toString());

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
   

}