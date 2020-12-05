package page;

import entity.Books;
import dao.BooksDao;
import tools.QueryPrint;
import tools.ScannerChoice;

import java.util.ArrayList;

/**
 * IntelliJ IDEA
 *
 * @author wxy
 * @version 2020-11-29  18:43
 */
public final class BooksPage extends ScannerChoice {
    /**
     * 1.图书添加
     */
    public static void addBooksPage(){
        System.out.println("\t正在执行添加图书信息\n");
        System.out.println("\n请输入添加图书-编码");
        String bookId = scannerInfoString();
        System.out.println("\n请输入添加图书-名称");
        String bookName = scannerInfoString();
        System.out.println("\n请输入添加图书-作者名");
        String bookWriter = scannerInfoString();
        System.out.println("\n请输入添加图书-ISBN");
        String bookIsbn = scannerInfoString();
        System.out.println("\n请输入添加图书-数量");
        int  bookNum = scannerNum();
        System.out.println("\n请输入添加图书-价格");
        double bookPrice = scannerInfo();

        Books book = new Books(bookId,bookName,bookWriter,bookIsbn,bookNum,bookPrice);
        boolean bool = new BooksDao().addBooks(book);

        if (bool){
            System.out.println("\n!您已成功添加图书到数据库");
        }else{
            System.out.println("添加图书失败");
        }
        changedInfoNext("addBooksPage");
    }

    /**
     * 2. 删除图书信息
     */
    public static void deleteBooksPage(){
        System.out.println("\t正在执行 删除图书信息 操作\n");
        System.out.println("请输入想要删除的图书");
        // 调用查找商品的函数，显示将要删除的商品
        String bookId = QueryPrint.query("deleteBooksPage");
        //确认删除
        //noinspection InfiniteLoopStatement
        do{
            System.out.println("\n确定删除该商品: Y/N");
            // 输入操作选项
            String choice = ScannerChoice.scannerInfoString();
            if ("Y".equals(choice) || "y".equals(choice)){
                // 进行删除操作
                boolean boolDeleteBooks = new BooksDao().deleteBooks(bookId);
                if (boolDeleteBooks){
                    System.out.println("\t!!已经成功删除图书");
                }else{
                    System.out.println("\n\t!!删除该商品失败");
                }
                // 选择继续操作/返回上一级菜单
                changedInfoNext("deleteBooksPage");
            }else if ("N".equals(choice) || "n".equals(choice)){
                // 返回图书管理主界面
                MainPage.booksManagementPage();
            }
            System.out.println("\t!!输入有误，请重新输入！！\n");
        }while (true);
    }
    /**
     * 3. 查询图书信息
     */
    public static void queryBooksPage(){
        System.out.println("\t\t 正在执行查询图书信息操作");
        System.out.println("\t\t 1. 按照图书编码查找");
        System.out.println("\t\t 2. 按照图书名称查找");
        System.out.println("\n请输入选项，或者按0返回上一级菜单");
        ArrayList<Books> booksList  = new ArrayList<>();
        //noinspection InfiniteLoopStatement
        do{
            // 输入选项
            String info = ScannerChoice.scannerInfoString();
            String regex = "[0-2]";
            if (info.matches(regex)){
                int choice = Integer.parseInt(info);
                switch (choice){
                    case 0:
                        // 返回上一级菜单
                        MainPage.booksManagementPage();
                        break;
                    case 1:
                        // 输入要查找的图书编码
                        String bookId = ScannerChoice.scannerInfoString();
                        booksList = new BooksDao().queryBooks(bookId,"");
                        break;
                    case 2:
                        // 输入要查找的图书名称
                        String bookName = ScannerChoice.scannerInfoString();
                        booksList = new BooksDao().queryBooks("",bookName);
                      break;
                    default:
                        break;
                }
                // 返回的信息数组
                if (booksList.size() <= 0){
                    System.out.println("查找失败，未找到该图书信息");
                }else{
                    // 打印信息
                    QueryPrint.print(booksList);
                }
                // 选择继续操作/返回上一级菜单
                ScannerChoice.changedInfoNext("queryBooksPage");
            }else{
                System.out.println("输入有误，请重新输入");
            }
        }while(true);
    }
    /**
     * 4. 更改图书信息
     */
    public static void updateBooksPage() {
        System.out.println("\t\t 正在执行更改图书信息操作\n");
        System.out.println("请输入想要更改的书籍名称");
        // 调用query函数查找该图书编码，方便以下操作
        String bookId = QueryPrint.query("updateGoodsPage");
        System.out.println("\n--------请选择您要更改的内容\n");
        System.out.println("\t1.更改图书-名称");
        System.out.println("\t2.更改图书-数量");
        System.out.println("\t3.更改图书-价格");
        System.out.println("\n请输入选项,或者按0返回上一级菜单.");
        //noinspection InfiniteLoopStatement
        do {
            // 输入操作选项
            String choice = ScannerChoice.scannerInfoString();
            // 正则表达式
            String regex = "[0-3]";
            if (choice.matches(regex)){
                int info = Integer.parseInt(choice);
                switch (info){
                    case 0:
                        MainPage.booksManagementPage();
                        break;
                    case 1:
                        // 更改图书-名称
                        System.out.println("请输入图书-新名称");
                        String newName = ScannerChoice.scannerInfoString();
                        Books updateBookName = new Books(bookId,newName);
                        boolean boolName = new BooksDao().updateBooks(1,updateBookName);
                        if (boolName){
                            System.out.println("\n\t! ! 成功更新图书名称！！\n");
                        }else {
                            System.err.println("\n\t！！更新图书名称失败！！");
                        }
                        break;
                    case 2:
                        // 更改图书-数量
                        System.out.println("请输入图书-新数量");
                        int newNum = ScannerChoice.scannerNum();
                        Books updateBookNum = new Books(bookId,newNum);
                        boolean boolNum = new BooksDao().updateBooks(2,updateBookNum);
                        if (boolNum){
                            System.out.println("\n\t! ! 成功更新图书数量！！\n");
                        }else {
                            System.err.println("\n\t！！更新图书数量失败！！");
                        }
                        break;
                    case 3:
                        // 更改图书-价格
                        System.out.println("请输入图书-新价格");
                        double newPrice = ScannerChoice.scannerInfo();
                        Books updateBookPrice = new Books(bookId,newPrice);
                        boolean boolPrice = new BooksDao().updateBooks(3,updateBookPrice);
                        if (boolPrice){
                            System.out.println("\n\t! ! 成功更新图书价格！！\n");
                        }else {
                            System.err.println("\n\t！！更新图书价格失败！！");
                        }
                        break;
                    default:
                        break;
                }
                // 选择继续操作/返回上一级菜单
                ScannerChoice.changedInfoNext("updateBooksPage");
            }else {
                System.err.println("！输入有误！");
                System.out.println("请重新选择,或者按0返回上一级菜单.");
            }
        } while (true);
    }
    /**
     * 5.显示所有图书信息
     */
    public static void showBooksPage(){
        System.out.println("\t\t\t\t\t所有图书列表\n\n");
        ArrayList<Books> booksList = new BooksDao().showBooks();
        // 得到返回的图书信息数组
        if (booksList.size() <= 0 ){
            System.out.println("!馆存为空");
            // 返回图书管理主界面
            MainPage.booksManagementPage();
        }else{
            // 打印图书信息
            QueryPrint.print(booksList);
        }
        System.out.println("-----------------------");
        //noinspection InfiniteLoopStatement
        do{
            System.out.println("输入0,,返回上一级菜单");
            // 输入操作选项
            String choice = scannerInfoString();
            if ("0".equals(choice)){
                // 返回图书管理主界面
                MainPage.booksManagementPage();
            }
            System.out.println("输入有误！");
        }while(true);

    }
}
