package dao;

import dataBaseConnection.DbClose;
import dataBaseConnection.DbConn;
import entity.Books;
import tools.QueryPrint;

import java.sql.*;
import java.util.ArrayList;

/**
 * IntelliJ IDEA
 *
 * @author wxy
 * @version 2020-11-29  18:42
 */
public final class BooksDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    /**
     * 1.添加图书信息
     * @param book 需要增加到数据库的对象
     * @return 返回false/true 判断是否添加成功
     */
    public boolean addBooks(Books book) {
        boolean bool = false;
        conn = DbConn.getConn();
        String sql = "INSERT INTO BOOKS(BOOKID,BOOKNAME,BOOKWRITER,BOOKISBN,BOOKNUM,BOOKPRICE) VALUE (?,?,?,?,?,?)";

        try{
            // sql语句预处理
            pstmt = conn.prepareStatement(sql);
            // 传参
            pstmt.setString(1,book.getBookId());
            pstmt.setString(2,book.getBookName());
            pstmt.setString(3, book.getBookWriter());
            pstmt.setString(4,book.getBookIsbn());
            pstmt.setInt(5,book.getBookNum());
            pstmt.setDouble(6,book.getBookPrice());
            // 执行sql语句
            int rs = pstmt.executeUpdate();
            // rs 为更新的条数
            if (rs > 0){
                bool = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            // 关闭连接
            DbClose.addClose(pstmt,conn);
        }
        return bool;
    }

    /**
     * 2. 从数据库删除图书信息
     * @param bookId 图书编码
     * @return 返回false/true 判断是否删除成功
     */
    public boolean deleteBooks(String bookId){
        boolean bool = false;
        conn = DbConn.getConn();
        String sql = " DELETE FROM BOOKS WHERE BOOKID = ?";
        try {
            // sql语句预处理
            pstmt = conn.prepareStatement(sql);
            // 传参
            pstmt.setString(1,bookId);
            // 执行sql语句
            int rs = pstmt.executeUpdate();
            // rs 为更新的条数
            if (rs > 0){
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 关闭连接
            DbClose.addClose(pstmt,conn);
        }
        return bool;
    }

    /**
     * 3. 查询图书信息
     * @param bookId 图书编码
     * @param bookName 图书名称
     * @return 从数据库中查找到的信息
     */
    public ArrayList<Books> queryBooks(String bookId, String bookName){
        ArrayList<Books> booksList;
        booksList = new QueryPrint().queryBooksKey(bookId,bookName);
        return  booksList;
    }

    /**
     * 4. 更改图书信息
     * @param choice 选择修改内容的选项
     * @param book 要修改的对象
     * @return 返回false/true 判断是否修改成功
     */
    public boolean updateBooks(int choice, Books book){
        boolean bool = false;
        conn = DbConn.getConn();
        switch (choice){
            // 修改图书名称
            case 1:
                String sqlName = "UPDATE BOOKS SET BOOKNAME=? WHERE BOOKID=?";
                try {
                    // sql语句预处理
                    pstmt = conn.prepareStatement(sqlName);
                    // 传参
                    pstmt.setString(1,book.getBookName());
                    pstmt.setString(2,book.getBookId());
                    // 执行sql语句
                    int rs = pstmt.executeUpdate();
                    // rs 为更新的条数
                    if (rs > 0){
                        bool = true;
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }finally {
                    // 关闭连接
                    DbClose.addClose(pstmt,conn);
                }
                break;
            // 更改图书数量
            case 2:
                String sqlNum = "UPDATE BOOKS SET BOOKNUM = ? WHERE BOOKID = ?";
                try {
                    // sql语句预处理
                    pstmt = conn.prepareStatement(sqlNum);
                    // 传参
                    pstmt.setInt(1,book.getBookNum());
                    pstmt.setString(2, book.getBookId());
                    // 执行sql语句
                    int rs = pstmt.executeUpdate();
                    // rs 为更新的条数
                    if (rs > 0){
                        bool = true;
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }finally {
                    // 关闭连接
                    DbClose.addClose(pstmt,conn);
                }
                break;
            // 更改图书价格
            case 3:
                String sqlPrice = "UPDATE BOOKS SET BOOKPRICE = ? WHERE BOOKID = ?";
                try {
                    // sql语句预处理
                    pstmt = conn.prepareStatement(sqlPrice);
                    // 传参
                    pstmt.setDouble(1,book.getBookPrice());
                    pstmt.setString(2,book.getBookId());
                    // 执行sql语句
                    int rs = pstmt.executeUpdate();
                    // rs 为更新的条数
                    if (rs > 0){
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
     * 5. 显示所有图书信息
     * @return 从数据库查到到的信息
     */
    public ArrayList<Books> showBooks(){
        ArrayList<Books> booksList = new ArrayList<>();
        conn = DbConn.getConn();
        String sql = "SELECT * FROM BOOKS";

        try{
            // sql语句预处理
            pstmt = conn.prepareStatement(sql);
            // 执行sql语句
            rs = pstmt.executeQuery();
            // rs为查询到的信息数组
            while(rs.next()){
                String bookId = rs.getString(1);
                String bookName = rs.getString(2);
                String bookWriter = rs.getString(3);
                String bookIsbn = rs.getString(4);
                int bookNum = rs.getInt(5);
                double bookPrice = rs.getDouble(6);
                int bookNumBroughtOut = rs.getInt(7);
                int bookNumBorrowed = rs.getInt(8);

                Books book = new Books(bookId,bookName,bookWriter,bookIsbn,bookNum,bookPrice,bookNumBroughtOut,bookNumBorrowed);
                booksList.add(book);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }finally {
            // 关闭连接
            DbClose.queryClose(pstmt,rs,conn);
        }
        return  booksList;
    }
}
