package tools;

import page.BooksPage;
import page.MainPage;
import page.UsersPage;

import java.util.Scanner;

/**
 * IntelliJ IDEA
 *
 * @author wxy
 * @version 2020-11-29  18:44
 */
public class ScannerChoice {

    public static final String ADD_BOOKS_PAGE = "addBooksPage";
    public static final String DELETE_BOOKS_PAGE = "deleteBooksPage";
    public static final String QUERY_BOOKS_PAGE = "queryBooksPage";
    public static final String UPDATE_BOOKS_PAGE = "updateBooksPage";
    public static final String ADD_USERS_PAGE = "addUsersPage";
    public static final String DELETE_USERS_PAGE = "deleteUsersPage";
    public static final String QUERY_USERS_PAGE = "queryUsersPage";
    public static final String UPDATE_USERS_PAGE = "updateUsersPage";

    /**
     * @return double 输入的图书价格,小数点后两位
     */
    public static double scannerInfo(){
        double num ;
        do{
            Scanner in  = new Scanner(System.in);
            System.out.print("保留小数点后两位,请输入：");
            String info = in.next();
            // 正则表达式
            String regex = "(([1-9][0-9]*)\\.([0-9]{2}))|[0]\\.([0-9]{2})";
            if (info.matches(regex)){
                num = Double.parseDouble(info);
            }else{
                System.out.print("!输入有误！");
                continue;
            }
            break;
        }while(true);
        return num;
    }
    /**
     * @return int 输入的图书数量
     */
    public static int  scannerNum(){
        int num;
        // 正则表达式
        String regex = "([1-9])|([1-9][0-9]+)";
        do{
            Scanner in = new Scanner(System.in);
            System.out.print("请输入:");
            String nums = in.next();

            if (nums.matches(regex)){
                num = Integer.parseInt(nums);
            }else{
                System.out.print("!输入有误");
                continue;
            }
            break;
        }while(true);
        return num;
    }
    /**
     * @return String 输入字符串
     */
    public static String scannerInfoString(){
        Scanner in = new Scanner(System.in);
        System.out.print("请输入：");
        return in.next();
    }

    /**
     * 选择继续操作/返回上一级菜单
     * @param oper 选择的操作类型（DRUD）
     */
    public static void changedInfoNext(String oper){
        //noinspection InfiniteLoopStatement
        do{
            System.out.println("是否继续进行-当前操作（Y/N）");
            String choice = scannerInfoString();

            //equals比较值，==比较地址
            if ("y".equals(choice) || "Y".equals(choice)){
                if (ADD_BOOKS_PAGE.equals(oper)){
                    BooksPage.addBooksPage();
                }else if (DELETE_BOOKS_PAGE.equals(oper)){
                    BooksPage.deleteBooksPage();
                }else if (QUERY_BOOKS_PAGE.equals(oper)){
                    BooksPage.queryBooksPage();
                }else if (UPDATE_BOOKS_PAGE.equals(oper)){
                    BooksPage.updateBooksPage();
                }
            }else if ("N".equals(choice) || "n".equals(choice)){
                MainPage.booksManagementPage();
            }
            System.out.println("\n输入有误！请重新输入");
        }while(true);
    }

    /**
     * 选择继续操作/返回上一级菜单
     * @param oper 选择的操作类型（DRUD）
     */
    public static void userChangedInfoNext(String oper){
        //noinspection InfiniteLoopStatement
        do{
            System.out.println("是否继续进行-当前操作（Y/N）");
            String choice = scannerInfoString();

            //equals比较值，==比较地址
            if ("y".equals(choice) || "Y".equals(choice)){
                if (ADD_USERS_PAGE.equals(oper)){
                    UsersPage.addUsersPage();
                }else if (DELETE_USERS_PAGE.equals(oper)){
                    UsersPage.deleteUsersPage();
                }else if (QUERY_USERS_PAGE.equals(oper)){
                    UsersPage.queryUsersPage();
                }else if (UPDATE_USERS_PAGE.equals(oper)){
                    UsersPage.updateUsersPage();
                }
            }else if ("N".equals(choice) || "n".equals(choice)){
                MainPage.usersManagement();
            }
            System.out.println("\n输入有误！请重新输入");
        }while (true);
    }
}
