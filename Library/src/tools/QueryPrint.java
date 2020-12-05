package tools;


import dataBaseConnection.DbClose;
import dataBaseConnection.DbConn;
import entity.Books;
import entity.Users;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * IntelliJ IDEA
 *
 * @author wxy
 * @version 2020-11-30  19:22
 */


public final class QueryPrint {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * 根据图书名称查找图书编码
     * @param oper 调用者
     * @return 查询的图书编码  当返回的图书编码为空字符串 代码异常
     */
    public static String query(String oper){
        String bookId = "";
        // 输入图书名称
        String bookName = ScannerChoice.scannerInfoString();
        ArrayList<Books> booksList = new QueryPrint().queryBooksKey("",bookName);
        if (booksList.size() <= 0){
            System.err.println("\t!!查无此图书");
            // 选择继续操作/返回上一级菜单
            ScannerChoice.changedInfoNext(oper);
        }else{
            // 查到此图书
            Books book = booksList.get(0);
            System.out.println("\t"+"图书编号"+"\t\t\t"+"书籍名称"+"\t\t\t"+"书籍作者"+"\t\t\t"+"ISBN"+
               "\t\t\t  "+"书籍馆存量"+"\t\t"+"书籍价格"+"\t\t"+"书籍借出量"+"\t\t"+"书籍借阅次数"+"\t\t"+"\n");
            System.out.println("\t" + book.getBookId() + "\t\t" + book.getBookName() + "\t\t" + book.getBookWriter() + "\t\t" + book.getBookIsbn() +
                 "\t\t\t" + book.getBookNum() + "\t\t" + book.getBookPrice() + " $" + "\t\t  " + book.getbookNumBroughtOut() + "\t\t\t\t  " + book.getbookNumBorrowed());
            bookId = book.getBookId();
        }
        return bookId;
    }

    /**
     * 根据用户名查找用户编码
     * @param oper 选择的操作类型（DRUD）
     * @return 用户编码
     */
    public static int queryUser(String oper) {
        int userId = 0;
        String userName = ScannerChoice.scannerInfoString();
        ArrayList<Users> usersList = new QueryPrint().queryUsersKey(userId,userName);
        if (usersList.size() <= 0){
            System.err.println("\t!!查无此用户");
            // 选择继续操作/返回上一级菜单
            ScannerChoice.userChangedInfoNext(oper);
        }else{
                Users user = usersList.get(0);
                System.out.println("\t用户编号"+"\t\t\t"+"用户名"+"\t\t\t"+"用户密码"+"\n");
                System.out.println("\t"+user.getUserId()+"\t\t\t\t"+user.getUserName()+"\t\t\t\t"+user.getUserPassword());
                userId = user.getUserId();
        }
        return userId;
    }

    /**
     * 根据用户名/用户编码查询
     * @param userId 用户编码
     * @param userName 用户名
     * @return 此用户的信息
     */
    public ArrayList<Users> queryUsersKey(int userId,String userName){
        ArrayList<Users> usersList = new ArrayList<>();
        // 打开连接
        conn = DbConn.getConn();

        String sql = "SELECT * FROM USERS WHERE USERID = ? OR USERNAME = ?";
        try {
            // sql语句预处理
            pstmt = conn.prepareStatement(sql);
            // 传参
            pstmt.setInt(1,userId);
            pstmt.setString(2,userName);
            // 执行sql语句
            rs = pstmt.executeQuery();
            // rs 为查询的结果集
            while (rs.next()){
                int userIdNew = rs.getInt(1);
                String userNameNew = rs.getString(2);
                String userPassword = rs.getString(3);

                Users user = new Users(userIdNew,userNameNew,userPassword);
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 关闭连接
            DbClose.queryClose(pstmt,rs,conn);
        }
        return usersList;
    }
    /**
     * 根据图书/图书编码 进行查询
     * @param bookId 图书编码
     * @param bookName 图书名称
     * @return 查询到的图书信息 当返回的图书编码为空字符串 代码异常
     */
    public ArrayList<Books> queryBooksKey(String bookId, String bookName){
        ArrayList<Books> booksList = new ArrayList<>();
        // 打开连接
        conn = DbConn.getConn();

        String sql = "SELECT * FROM BOOKS WHERE BOOKID=? OR BOOKNAME=?";
        try{
            // sql语句预处理
            pstmt = conn.prepareStatement(sql);
            // 传参
            pstmt.setString(1,bookId);
            pstmt.setString(2,bookName);
            // 执行sql语句
            rs = pstmt.executeQuery();
            // rs 为查询的结果集
            while(rs.next()){
                String bookIdNew = rs.getString(1);
                String bookNameNew = rs.getString(2);
                String bookWriter = rs.getString(3);
                String bookIsbn = rs.getString(4);
                int bookNum = rs.getInt(5);
                double bookPrice = rs.getDouble(6);
                int bookNumBroughtOut = rs.getInt(7);
                int bookNumBorrowed = rs.getInt(8);

                Books book = new Books(bookIdNew,bookNameNew,bookWriter,bookIsbn,bookNum,bookPrice,bookNumBroughtOut,bookNumBorrowed);
                booksList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 关闭连接
            DbClose.queryClose(pstmt, rs, conn);
        }
        return booksList;
    }

    /**
     * 打印图书信息
     */
    public static void print(ArrayList<Books> booksList){
        for (Books book : booksList) {
            System.out.println("\t"+"图书编号"+"\t\t\t"+"书籍名称"+"\t\t\t"+"书籍作者"+"\t\t\t"+"ISBN"+
                    "\t\t\t  "+"书籍馆存量"+"\t\t"+"书籍价格"+"\t\t"+"书籍借出量"+"\t\t"+"书籍借阅次数"+"\t\t"+"\n");
            System.out.println("\t" + book.getBookId() + "\t\t" + book.getBookName() + "\t\t" + book.getBookWriter() + "\t\t" + book.getBookIsbn() +
                    "\t\t\t" + book.getBookNum() + "\t\t" + book.getBookPrice() + " $" + "\t\t  " + book.getbookNumBroughtOut() + "\t\t\t\t  " + book.getbookNumBorrowed());
        }
    }
    /**
     * 打印用户信息
     */
    public static void userPrint(ArrayList<Users> usersList){
        for (Users user : usersList){
            System.out.println("\t用户编号"+"\t\t\t"+"用户名"+"\t\t\t"+"用户密码"+"\n");
            System.out.println("\t"+user.getUserId()+"\t\t\t\t"+user.getUserName()+"\t\t\t\t"+user.getUserPassword());
        }
    }
}
