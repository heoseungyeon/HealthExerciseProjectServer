package DTO;

//Login시 user테이블에 대한 데이터 DTO
public class LoginDTO {
   private String id;
   private String pw;
   private boolean check;
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getPw() {
      return pw;
   }
   public void setPw(String pw) {
      this.pw = pw;
   }
   public boolean isCheck() {
      return check;
   }
   public void setCheck(boolean check) {
      this.check = check;
   }
   
   
}