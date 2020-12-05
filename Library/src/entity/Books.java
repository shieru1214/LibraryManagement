package entity;

/**
 * IntelliJ IDEA
 *
 * @author wxy
 * @version 2020-11-29  15:59
 */
public  final class Books {
    private String bookId;
    private String bookName;

    @Override
    public String toString() {
        return "Books{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookWriter='" + bookWriter + '\'' +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookNum=" + bookNum +
                ", bookNumBroughtOut=" + bookNumBroughtOut +
                ", bookNumBorrowed=" + bookNumBorrowed +
                '}';
    }

    private String bookWriter;
    private String bookIsbn;
    private double bookPrice;
    private int bookNum;
    private int bookNumBroughtOut;
    private int bookNumBorrowed;

    /**
     * 添加图书信息
     * @param bookId 图书编码
     * @param bookName 图书名称
     * @param bookWriter 图书作者名
     * @param bookIsbn 图书ISBN
     * @param bookPrice 图书价格
     * @param bookNum 图书添加数量
     */
    public Books(String bookId, String bookName, String bookWriter,String bookIsbn, int bookNum,double bookPrice ){
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookWriter = bookWriter;
        this.bookIsbn = bookIsbn;
        this.bookPrice = bookPrice;
        this.bookNum = bookNum;
        this.bookNumBroughtOut = 0;
        this.bookNumBorrowed = 0;
    }

    /**
     * @param bookId 图书编码
     * @param bookName 图书名称
     * @param bookWriter 图书作者名
     * @param bookIsbn 图书ISBN
     * @param bookNum 图书添加数量
     * @param bookPrice 图书价格
     * @param bookNumBroughtOut 图书外借量
     * @param bookNumBorrowed 图书介借阅次数
     */
    public Books(String bookId, String bookName, String bookWriter,String bookIsbn, int bookNum,double bookPrice, int bookNumBroughtOut,int bookNumBorrowed){
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookWriter = bookWriter;
        this.bookIsbn = bookIsbn;
        this.bookPrice = bookPrice;
        this.bookNum = bookNum;
        this.bookNumBorrowed = bookNumBroughtOut;
        this.bookNumBroughtOut = bookNumBorrowed;
    }

    public Books(){

    }

    /**
     * 根据编码更改书名
     * @param bookId 图书编码
     * @param bookName 图书名称
     */
    public Books(String bookId, String bookName){
        this.bookId = bookId;
        this.bookName = bookName;
    }

    /**
     * 根据编码更改图书数量
     * @param bookId 图书编码
     * @param bookNum 图书添加数量
     */
    public Books(String bookId, int bookNum){
        this.bookId = bookId;
        this.bookNum = bookNum;
    }

    /**
     * 根据编码更改图书价格
     * @param bookId 图书编码
     * @param bookPrice 图书价格
     */
    public Books(String bookId, double bookPrice){
        this.bookId = bookId;
        this.bookPrice = bookPrice;
    }

    /**
     * set get method
     */
    public String getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookWriter() {
        return bookWriter;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public int getBookNum() {
        return bookNum;
    }

    public int getbookNumBroughtOut() {
        return bookNumBroughtOut;
    }

    public int getbookNumBorrowed() {
        return bookNumBorrowed;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookWriter(String bookWriter) {
        this.bookWriter = bookWriter;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }
}
