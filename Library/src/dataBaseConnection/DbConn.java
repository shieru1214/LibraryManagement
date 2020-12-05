package dataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * IntelliJ IDEA
 * @author wxy
 * @version 2020-11-29  16:32
 */
public final class DbConn {
    /**
     * @return 已经连接数据库的Connection类型变量
     */
    public static Connection getConn(){
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/Library?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
        String user = "root";
        String passWord = "wang697020";
        try {
            conn = DriverManager.getConnection(url,user,passWord);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

}
