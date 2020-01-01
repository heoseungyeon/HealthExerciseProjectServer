package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class ExerciseDAO {
	
   @SuppressWarnings("unchecked")
   public JSONArray selectExerciseList() throws ClassNotFoundException {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      JSONArray jsonArray = new JSONArray();

      try {
         String sql = "select * from exercise";

         conn = DBConnection.getConnection();
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("no", rs.getString("health_no"));
            jsonObject.put("name", rs.getString("health_name"));
            jsonObject.put("detail", rs.getString("health_detail"));
            jsonObject.put("kind", rs.getString("health_kind"));
            jsonObject.put("kind_detail", rs.getString("health_kind_detail"));
            jsonObject.put("img_location",rs.getString("img_location"));
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
   
   public String insertExercise(JSONObject exercise) throws ClassNotFoundException, ParseException {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String rst = "success";

      try {
         conn = DBConnection.getConnection();

            String sql = "insert INTO exercise(health_no, health_name, health_detail, health_kind, health_kind_detail)"
                  + "values (?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, (exercise.get("no")).toString());
            pstmt.setString(2, (exercise.get("name")).toString());
            pstmt.setString(3, (exercise.get("detail")).toString());
            pstmt.setString(4, (exercise.get("kind")).toString());
            pstmt.setString(5, (exercise.get("kind_detail")).toString());
      
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