public class Book {

    enum Condition{NEW,GOOD,BAD,REPLACE}
    private String name,author,genre;
    private long ISBN;
    private int yearPublished,checkOutUserID;
    private Condition bookCondition;
    private Date checkOutDate;
    Book nextBook;
    private boolean checkedOut;

    public Book(){}

    public Book(String name, String author, String genre, long ISBN, int yearPublished, int checkOutUserID, Condition bookCondition, Date checkOutDate, boolean checkedOut){
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.ISBN = ISBN;
        this.yearPublished = yearPublished;
        this.checkOutUserID = checkOutUserID;
        this.bookCondition = bookCondition;
        this.checkOutDate = checkOutDate;
        this.checkedOut = checkedOut;
    }

    public Book(long addISBN, String addName, String addAuthor, String addGenre, Condition addCondition) {
        this.ISBN = addISBN;
        this.name = addName;
        this.author = addAuthor;
        this.genre = addGenre;
        this.bookCondition = addCondition;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearPublished;
    }

    public void setYearOfPublication(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Condition getCondition() {
        return bookCondition;
    }

    public void setCondition(Condition condition) {
        this.bookCondition = condition;
    }

    public long getISBNNumber() {
        return ISBN;
    }

    public void setISBNNumber(int ISBNNumber) {
        this.ISBN = ISBNNumber;
    }

    public int getCheckOutUserID(){
        return checkOutUserID;
    }

    public void setCheckOutUserID(int checkOutUserID){
        this.checkOutUserID = checkOutUserID;
    }

    public Date getCheckedOutDate() {
        return checkOutDate;
    }

    public void setCheckedOutDate(Date checkedOutDate) {
        this.checkOutDate = checkedOutDate;
    }

    public void setCheckedOut(boolean checkedOut){this.checkedOut = checkedOut;}

    public boolean getCheckedOut(){return checkedOut;}

    public Book getNextBook(){
        return nextBook;
    }

    public void setNextBook(Book nextBook){
        this.nextBook = nextBook;
    }

    @Override
    public String toString() {
        String result = "";
        result += String.format("%-15s%-15s%-15s%-15d%-15d%-15d%-15s%-15s%-15b\n", name, author,genre,ISBN,yearPublished,checkOutUserID
                ,bookCondition,checkOutDate.toString(),checkedOut);
        return result;
    }

    public static void main(String...args){
    }
}
