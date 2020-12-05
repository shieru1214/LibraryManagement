package entity;

/**
 * IntelliJ IDEA
 *
 * @author wxy
 * @version 2020-11-29  16:00
 */
public class Users {

    /**
     * @param nextId  该值为类的共享实例域 无法对象化 系统重启后会初始化为1
     */
    private static int  nextId = 1;
    private int userId;
    private String userName;
    private  String userPassword;

    /**
     * @param
     * @param userName
     * @param userPassword
     */

    public Users (String userName, String userPassword){
        this.userName = userName;
        this.userPassword = userPassword;
    }
    public Users (int userId, String userPassword){
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public Users(int userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public void setUserId() {
        userId = nextId;
        nextId ++;
    }

    public int getUserId() {
        return userId;
    }

    public static int getNextId(){
        return nextId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
