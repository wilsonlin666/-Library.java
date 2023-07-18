import java.util.*;
public class ReturnStack {
    private ReturnLog topLog;

    public ReturnStack(){topLog = null;}

    public boolean pushLog(long returnISBN, int returnUserID, Date returnDate) throws InvalidISBNException, InvalidReturnDateException,BookCheckedOutBySomeoneElseException,BookNotCheckedOutAException{
        BookRepository shelves = new BookRepository();
        Book checkedOutBook = shelves.findBookByISBN(returnISBN);
        if(checkedOutBook == null || !checkedOutBook.getCheckedOut()){
            throw new BookNotCheckedOutAException("Book with ISBN " + returnISBN + "is not checked out");
        }
        if(checkedOutBook.getCheckOutUserID() != returnUserID){
            throw new BookCheckedOutBySomeoneElseException("Book with ISBN " + returnISBN + " was checked out by someonelse ");
        }
        if(returnDate.getMonth() > 12 || returnDate.getMonth() <= 0 || returnDate.getDay() > 31 || returnDate.getDay() <= 0){
            throw new InvalidReturnDateException("Invalid Return Date");
        }
        // create a new returnLog for the returned book
        ReturnLog newLog = new ReturnLog(returnISBN,returnUserID,returnDate,null);
        // set the previous topLog as the nextLog of the newLog and set the newLog as the new topLog
        newLog.setNextLog(topLog);
        topLog = newLog;
        //check if the book was returned late and return true if it was on time
        return !returnDate.after(checkedOutBook.getCheckedOutDate());
    }

    public ReturnLog getTopLog(){
        return topLog;
    }

    public ReturnLog popLog() throws EmptyStackException{
        if(topLog == null){
            throw new EmptyStackException();
        }
        ReturnLog poppedLog = topLog;
        topLog = topLog.getNextLog();
        return poppedLog;
    }

    public void printTable(){
        System.out.printf("%-15s%-15s%-15s\n","ISBN","UserID", "Return Date");
        System.out.println("-----------------------------------------------------");
        for(ReturnLog log = topLog; log != null;log = log.getNextLog()){
            System.out.printf("%-15d%-15d%-15s\n",
                    log.getISBN(),
                    log.getUserID(),
                    log.getReturnDate().toString());
        }
    }
}
