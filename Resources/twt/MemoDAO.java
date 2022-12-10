package Resources.twt;

import Resources.signup.SignupVO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class MemoDAO {
    private Connection con;
    private PreparedStatement pstmt;
    public String text;
    public String team_id;
    public String posX;
    public String posY;
    public String qur;

    public MemoDAO(String team_id) {
        this.team_id = team_id;
    }

    public MemoDAO(String text, String team_id, String posX, String posY) {
        this.text=text;
        this.team_id=team_id;
        this.posX=posX;
        this.posY=posY;
    }

    public MemoVO findMemo() {
        MemoVO memoVO = new MemoVO();
        try {
            connDB();

            String query="select * from memo_table where team_id = '" + team_id + "'";
            pstmt=con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            String input_text="", x="", y="";
            while(rs.next()) {
                if(!input_text.equals("")) {
                    input_text += ":";
                    x += ":";
                    y += ":";
                }
                input_text += rs.getString("post_text");
                x += rs.getString("posX");
                y += rs.getString("posY");
            }

            memoVO.setText(input_text);
            memoVO.setX(x);
            memoVO.setY(y);

            rs.close();
            pstmt.close();
            con.close();
        }catch(Exception e) {
            e.printStackTrace();
        }

        return memoVO;
    }

    public void insertMemo() {
        try {
            connDB();

            String query_Insert="insert into memo_table values('" + text + "','" + team_id + "','" + posX + "','" + posY + "')";
            qur = query_Insert.toString();
            pstmt = con.prepareStatement(query_Insert);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMemo() {
        try {
            connDB();

            String query_Insert="insert into memo_table values('" + text + "','" + team_id + "','" + posX + "','" + posY + "')";
            qur = query_Insert.toString();
            pstmt = con.prepareStatement(query_Insert);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void connDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("드라이버 로딩 성공");
            con= DriverManager.getConnection("jdbc:mysql://3.39.132.237:59550/twt","webp","0000");
            System.out.println("Connection 생성 성공");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
