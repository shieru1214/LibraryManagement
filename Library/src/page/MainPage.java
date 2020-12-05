package page;



import tools.ScannerChoice;

/**
 * IntelliJ IDEA
 *
 * @author wxy
 * @version 2020-11-29  18:43
 */
public final class MainPage extends ScannerChoice {
    /**
     * 入口函数
     */
    public static void  main(String[] args){
        MainPage.mainPage();
    }
    /**
     * 主界面
     */
    public static void mainPage(){
        System.out.println("***************************\n");
        System.out.println("\t 1.图书管理\n");
        System.out.println("\t 2.用户管理\n");
        System.out.println("***************************");
        System.out.println("\n请输入选项，或者按0退出.");
        do{
            // 输入操作选项
            String choice =  scannerInfoString();
            // 正则表达式
            String regex = "[0-2]";
            if (choice.matches(regex)){
                int info = Integer.parseInt(choice);
                switch (info){
                    case 0:
                        System.out.println("------------------");
                        System.out.println("您已经退出系统!");
                        System.exit(1);
                        break;
                    case 1:
                        // 图书管理界面
                        booksManagementPage();
                        break;
                    case 2:
                        // 用户管理界面
                        usersManagement();
                        break;
                    default:
                        break;
                }
            }
        }while(true);
    }


    /**
     *  1、图书管理
     */
    public static void booksManagementPage(){
        System.out.println("***************************\n");
        System.out.println("\t 1.添加图书信息\n");
        System.out.println("\t 2.删除图书信息\n");
        System.out.println("\t 3.查询图书信息\n");
        System.out.println("\t 4.更改图书信息\n");
        System.out.println("\t 5.显示所有图书信息\n");
        System.out.println("***************************");
        System.out.println("\n请输入选项，或者按0 返回上一级菜单");
        //noinspection InfiniteLoopStatement
        do{
            // 输入操作选项
            String choice = scannerInfoString();
            // 正则表达式
            String regex = "[0-5]";
            if (choice.matches(regex)){
                int info = Integer.parseInt(choice);
                switch (info){
                    case 0:
                        // 主界面
                        mainPage();
                        break;
                    case 1:
                        // 图书增加
                        BooksPage.addBooksPage();
                        break;
                    case 2:
                        // 图书删除
                        BooksPage.deleteBooksPage();
                        break;
                    case 3:
                        // 图书查询
                        BooksPage.queryBooksPage();
                        break;
                    case 4:
                        // 图书信息更改
                        BooksPage.updateBooksPage();
                        break;
                    case 5:
                        // 图书信息清单
                        BooksPage.showBooksPage();
                        break;
                    default:
                        break;
                }
            }else{
                System.out.println("!输入有误！");
                System.out.println("重新输入或按0 返回上一级菜单：");
            }
        }while(true);
    }
    /**
     * 2.用户管理
     */
    public static void usersManagement(){
        System.out.println("***************************\n");
        System.out.println("1. 添加用户");
        System.out.println("2. 删除用户");
        System.out.println("3. 查找用户");
        System.out.println("4. 更改信息");
        System.out.println("5. 用户清单");
        System.out.println("***************************");
        System.out.println("\n请输入选项，或者按0 返回上一级菜单");

        //noinspection InfiniteLoopStatement
        do
        {
            // 输入操作选项
            String info = ScannerChoice.scannerInfoString();
            // 正则表达式
            String regex = "[0-5]";
            if (info.matches(regex)) {
                int choice = Integer.parseInt(info);
                switch (choice) {
                    case 0:
                        // 主界面
                        MainPage.mainPage();
                        break;
                    case 1:
                        // 用户增加
                        UsersPage.addUsersPage();
                        break;
                    case 2:
                        // 用户删除
                        UsersPage.deleteUsersPage();
                        break;
                    case 3:
                        // 用户查询
                        UsersPage.queryUsersPage();
                        break;
                    case 4:
                        // 用户信息更改
                        UsersPage.updateUsersPage();
                        break;
                    case 5:
                        // 用户信息清单
                        UsersPage.showUsersPage();
                        break;
                    default:
                        break;
                }
            } else {
                System.out.println("!输入有误！");
                System.out.println("重新输入或按0 返回上一级菜单：");
            }
        }while(true);
    }

}
