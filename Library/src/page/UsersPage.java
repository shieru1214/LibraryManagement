package page;


import dao.UsersDao;
import entity.Users;
import tools.QueryPrint;
import tools.ScannerChoice;


import java.util.ArrayList;

/**
 * IntelliJ IDEA
 *
 * @author wxy
 * @version 2020-12-03  08:18
 */
public final class UsersPage extends ScannerChoice {

    /**
     * 1. 用户添加
     */
    public static void addUsersPage() {
        System.out.println("\t正在进行添加用户操作\n");
        System.out.println("\n请输入用户名");
        String userName = scannerInfoString();
        System.out.println("\n请输入用户密码");
        String userPassword = scannerInfoString();

        Users user = new Users(userName,userPassword);
        user.setUserId();
        boolean bool  = new UsersDao().addUser(user);
        if (bool){
            System.out.println("\n!您已成功添加用户到数据库");
        }else{
            System.out.println("添加用户失败");
        }
        // 选择继续操作/返回上一级菜单
        userChangedInfoNext("addUsersPage");
    }

    /**
     * 2. 用户删除
     */
    public static void deleteUsersPage(){
        System.out.println("\t正在进行删除用户操作\n");
        System.out.println("请输入想要删除的用户名");
        int userId = QueryPrint.queryUser("deleteUsersPage");
        //确认删除
        //noinspection InfiniteLoopStatement
        do{
            System.out.println("\n确认删除此用户（Y/N）");
            // 输入操作选项
            String choice = ScannerChoice.scannerInfoString();
            if ("Y".equals(choice)|| "y".equals(choice)){
                boolean bool = new UsersDao().deleteUsers(userId);
                if (bool){
                    System.out.println("\t!!已经成功删除此用户");
                }else {
                    System.out.println("\t\n!!删除此用户失败");
                }
                // 选择继续操作/返回上一级菜单
                ScannerChoice.userChangedInfoNext("deleteUsersPage");
            }else if ("N".equals(choice) || "n".equals(choice)){
                // 返回用户管理主界面
                MainPage.usersManagement();
            }
            System.out.println("\t!!输入有误，请重新输入！！\n");
        }while (true);
    }
    /**
     * 3. 用户查询
     */
    public static void queryUsersPage(){
        System.out.println("\t\t 正在进行用户查找操作");
        System.out.println("\t\t 请输入用户名");
        int userId = QueryPrint.queryUser("queryUsersPage");
        ArrayList<Users> usersList = new UsersDao().queryUsers(userId,"");
        // 查找结果返回后
        if (usersList.size() <= 0){
            System.out.println("查找失败，用户不存在！");
        }
        // 选择继续操作/返回上一级菜单
        userChangedInfoNext("queryUsersPage");
    }
    /**
     * 4. 更改用户
     */
    public static void updateUsersPage(){
        System.out.println("\t\t 正在进行用户信息更改操作");
        System.out.println("\t\t 请输入用户名");
        int userId = QueryPrint.queryUser("updateUsersPage");
        System.out.println("\n--------请选择您要更改的内容\n");
        System.out.println("\t1.更改用户-名称");
        System.out.println("\t2.更改用户-密码");
        System.out.println("\n请输入选项,或者按0返回上一级菜单.");
        //noinspection InfiniteLoopStatement
        do{
            // 输入操作选项
            String choice = scannerInfoString();
            // 正则表达式
            String regex = "[0-2]";
            if (choice.matches(regex)){
                int info = Integer.parseInt(choice);
                switch (info){
                    case 0:
                        // 返回用户管理主界面
                        MainPage.usersManagement();
                        break;
                    case 1:
                        // 更改用户-名称
                        System.out.println("\t\t 请输入用户密码");
                        String userPassword = ScannerChoice.scannerInfoString();
                        System.out.println("\t\t 请输入用户新名");
                        String userName = ScannerChoice.scannerInfoString();
                        Users userUpdateName = new Users(userId,userName,userPassword);
                        boolean boolName = new UsersDao().updateUsers(1,userUpdateName);
                        if (boolName){
                            System.out.println("\n\t！！信息更改成功！");
                        }else {
                            System.out.println("\n\t ！！信息更改失败！");
                        }
                        break;
                    case 2:
                        // 更改用户-密码
                        System.out.println("\t\t 请输入用户新密码");
                        String userPasswordNew = ScannerChoice.scannerInfoString();
                        Users userUpdatePassword = new Users(userId,userPasswordNew);
                        boolean boolPassword = new UsersDao().updateUsers(2,userUpdatePassword);
                        if (boolPassword){
                            System.out.println("\n\t！！信息更改成功！");
                        }else {
                            System.out.println("\n\t ！！信息更改失败！");
                        }
                        break;
                    default:
                        break;
                }
                // 选择继续操作/返回上一级菜单
                userChangedInfoNext("updateUsersPage");
            }else{
                System.err.println("！输入有误！");
                System.out.println("请重新选择,或者按0返回上一级菜单.");
            }
        }while (true);
    }
    /**
     * 5. 用户清单
     */
    public static void showUsersPage(){
        System.out.println("\t\t\t\t\t所有用户列表\n\n");
        ArrayList<Users> usersList  = new UsersDao().showUsers();
        if (usersList.size() <=0 ){
            System.out.println("!用户信息为空");
        }else{
            // 打印用户信息
            QueryPrint.userPrint(usersList);
        }
        System.out.println("-----------------------");
        //noinspection
        do{
            System.out.println("输入0，返回上一级");
            // 输入操作选项
            String choice = scannerInfoString();
            if ("0".equals(choice)){
                MainPage.usersManagement();
            }else {
                System.out.println("输入错误！请重新输入！");
            }
        }while (true);
    }
}
