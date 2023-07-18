public class ReturnLog {

    private long ISBN;
    private int userID;
    private Date returnDate;
    private ReturnLog nextLog;

    public ReturnLog() {}

    public ReturnLog(long ISBN, int userID, Date returnDate, ReturnLog nextLog) {
        this.ISBN = ISBN;
        this.userID = userID;
        this.returnDate = returnDate;
        this.nextLog = nextLog;
    }

    public long getISBN() {
        return ISBN;
    }

    public int getUserID() {
        return userID;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public ReturnLog getNextLog() {
        return nextLog;
    }

    public void setNextLog(ReturnLog nextLog) {
        this.nextLog = nextLog;
    }

}
