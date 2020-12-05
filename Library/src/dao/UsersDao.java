package dao;

import dataBaseConnection.DbClose;
import dataBaseConnection.DbConn;
import entity.Users;
import tools.QueryPrint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * IntelliJ IDEA
 *
 * @author wxy
 * @version 2020-12-03  08:36
 */
public final class UsersDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * 1. 用户添加
     * @return 返回false/true 判断是否添加成功
     */
    public boolean addUser(Users user){
        boolean bool = false;
        // 打开连接
        conn = DbConn.getConn();
        String sql ="INSERT INTO USERS(USERID, USERNAME, USERPASSWORD) VALUE (?, ?, ?)";
        try {
            // sql语句预处理
            pstmt = conn.prepareStatement(sql);
            // 传参
            pstmt.setInt(1,user.getUserId());
            pstmt.setString(2,user.getUserName());
            pstmt.setString(3,user.getUserPassword());
            // 执行sql语句
            int rs = pstmt.executeUpdate();
            // rs 为更新的条数
            if (rs >0){
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            DbClose.addClose(pstmt,conn);
        }
        return bool;
    }
    /**
     * 2. 用户删除
     * @return  返回false/true 判断是否删除成功
     */
    public Boolean deleteUsers(int userId){
        boolean bool = false;
        // 打开连接
        conn = DbConn.getConn();
        String sql = "DELETE FROM USERS WHERE USERID = ?";
        try {
            // sql语句预处理
            pstmt = conn.prepareStatement(sql);
            // 传参
            pstmt.setInt(1,userId);
            // 执行sql语句
            int rs = pstmt.executeUpdate();
            // rs 为更新的条数
            if (rs >0){
                bool = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            // 关闭连接
            DbClose.addClose(pstmt,conn);
        }
        return  bool;
    }
    /**
     *3. 用户查询
     * @return 从数据库中查找到的信息
     */
    public ArrayList<Users> queryUsers(int userId, String userName){
        return new QueryPrint().queryUsersKey(userId,userName);
    }
    /**
     * 4. 用户信息更改
     * @return 返回false/true 判断是否修改成功
     */
    public Boolean updateUsers(int choice,Users user){
        boolean bool = false;
        // 打开连接
        conn = DbConn.getConn();
        switch (choice){
            case 1:
                String sqlName = "UPDATE USERS SET USERNAME = ? WHERE USERID = ?";
                try {
                    // sql语句预处理
                    pstmt = conn.prepareStatement(sqlName);
                    // 传参
                    pstmt.setString(1,user.getUserName());
                    pstmt.setInt(2,user.getUserId());
                    // 执行sql语句
                    int rs = pstmt.executeUpdate();
                    // rs 为更新的条数
                    if (rs >=0){
                        bool = true;
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }finally {
                    // 关闭连接
                    DbClose.addClose(pstmt,conn);
                }
                break;
            case 2:
                String sqlPassword = "UPDATE USERS SET USERPASSWORD = ? WHERE USERID = ?";
                try {
                    // sql语句预处理
                    pstmt = conn.prepareStatement(sqlPassword);
                    // 传参
                    pstmt.setString(1,user.getUserPassword());
                    pstmt.setInt(2,user.getUserId());
                    // 执行sql语句
                    int rs = pstmt.executeUpdate();
                    // rs 为更新的条数
                    if (rs >= 0){
                        bool = true;
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }finally {
                    // 关闭连接
                    DbClose.addClose(pstmt,conn);
                }
                break;
            default:
                break;
        }
        return bool;
    }

    /**
     * 5. 用户清单
     */
    public ArrayList<Users> showUsers(){
      ArrayList<Users> usersList = new ArrayList<>();
        // 打开连接
      conn = DbConn.getConn();
      String sql = "SELECT * FROM USERS";
      try {
          // sql语句预处理
          pstmt = conn.prepareStatement(sql);
          // 执行sql语句
          rs = pstmt.executeQuery();
          // rs为查询到的信息数组
          while (rs.next()){
              int userId = rs.getInt(1);
              String userName = rs.getString(2);
              String userPassword = rs.getString(3);
              Users user = new Users(userId,userName,userPassword);
              usersList.add(user);
          }
      }catch (SQLException e){
          e.printStackTrace();
      }finally {
          // 关闭连接
          DbClose.queryClose(pstmt,rs,conn);
      }
      return usersList;
    }

}
