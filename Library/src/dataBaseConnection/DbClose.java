package dataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * IntelliJ IDEA
 *
 * @author wxy
 * @version 2020-11-29  16:44
 */
public class DbClose {

    /**
     * 关闭 pstmt conn
     * @param pstmt PreparedStatement 类型变量
     * @param conn Connection 类型变量
     */
    public static void addClose(PreparedStatement pstmt, Connection conn){
        /**
         * 多个 try-catch出发点 安全
         */
        try{
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try {
            if (conn != null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    /**
     * 关闭 pstmt conn rs
     * @param pstmt PreparedStatement 类型变量
     * @param rs  ResultSet 类型变量
     * @param conn Connection 类型变量
     */
    public  static void queryClose(PreparedStatement pstmt, ResultSet rs, Connection conn){
        try {
            if (pstmt != null){
                pstmt.close();
            }
        }catch (SQLException e1){
            e1.printStackTrace();
        }
        try {
            if (rs != null){
                rs.close();
            }
        }catch (SQLException e1){
            e1.printStackTrace();
        }
        try {
            if ( conn != null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}


