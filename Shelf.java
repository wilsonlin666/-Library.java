import java.util.*;
public class Shelf{

    enum SortCriteria {
        TITLE{
            public Comparator<Book> getComparator(){
                return Comparator.comparing(Book::getName);
            }
        },
        AUTHOR{
            public Comparator<Book> getComparator(){
                return Comparator.comparing(Book::getAuthor);
            }
        },
        ISBN{
            public Comparator<Book> getComparator(){
                return Comparator.comparing(Book::getISBNNumber);
            }
        },
        GENRE{
            public Comparator<Book> getComparator(){return Comparator.comparing(Book::getGenre);}
        },
        CONDITION{
          public Comparator<Book> getComparator(){return Comparator.comparing(Book::getCondition);}
        },
        YEAR{
            public Comparator<Book> getComparator(){return Comparator.comparing(Book::getYearOfPublication);}
        };

        public static SortCriteria fromString(String sortCriteria) {
                if(sortCriteria == null){return null;}
                String upperCaseStr = sortCriteria.toUpperCase();
                try{
                    return valueOf(upperCaseStr);
                }catch(IllegalArgumentException e){
                    return null;
                }
        }

        public abstract Comparator<Book> getComparator();
    }

    Book headBook, tailBook;
    SortCriteria condition;
    int length;

    public Shelf() {
        this.headBook = null;
        this.tailBook = null;
        this.length = 0;
        this.condition = SortCriteria.ISBN;
    }

    public Book getHeadBook(){
        return this.headBook;
    }

    public Book findBook(long isbn) throws InvalidISBNException {
        if(isbn < 1){throw new InvalidISBNException("Invalid ISBN: " + isbn);}
        Book currBook = headBook;
        Book prevBook = null;
        while(currBook != null && currBook.getISBNNumber() < isbn){
            prevBook = currBook;
            currBook = currBook.getNextBook();
        }
        if (currBook != null && currBook.getISBNNumber() == isbn) {
            return currBook;
        }
        throw new InvalidISBNException("Invalid ISBN: " + isbn);
    }

    public void addBook(Book addedBook) throws BookAlreadyExistException {
        if (headBook == null) {
            headBook = addedBook;
            tailBook = addedBook;
        } else {
            Book currBook = headBook;
            Book prevBook = null;
            while (currBook != null && currBook.getISBNNumber() < addedBook.getISBNNumber()) {
                prevBook = currBook;
                currBook = currBook.getNextBook();
            }
            if (currBook != null && currBook.getISBNNumber() == addedBook.getISBNNumber()) {
                throw new BookAlreadyExistException("A book with the same ISBN already exists");
            }
            // add to front of shelf
            if(prevBook == null){
                addedBook.setNextBook(headBook);
                headBook = addedBook;
            }else{// add after prevBook
                prevBook.setNextBook(addedBook);
                addedBook.setNextBook(currBook);
                if(currBook == null){ // update tailBook
                    tailBook = addedBook;
                }
            }
        }
        length++;
    }

    public void removeBook(String removedISBN) throws BookDoesNotExistException {
        //empty shelf
        if (headBook == null) {
            throw new BookDoesNotExistException("Shelf is empty");
        }
        Book currBook = headBook;
        Book prevBook = null;
        while(currBook != null && !currBook.getName().equalsIgnoreCase(removedISBN)){
            prevBook = currBook;
            currBook = currBook.getNextBook();
        }
       //if we reach the end of the list and book is not found, throw exception
        if(currBook == null){throw new BookDoesNotExistException("A book with the same Name does not exist");}
        if(prevBook == null){
            //if it is the first book on the shelf remove the first book
            headBook = currBook.getNextBook();
        }else{
            prevBook.setNextBook(currBook.getNextBook());
            if(currBook.getNextBook() == null){ // update tailBook
                tailBook = prevBook;
            }
        }
        length --;
    }

    public void sort(SortCriteria sortCriteria) {
        // nothing is sorted if criteria is the same as condition
        if (length <= 1 || condition == sortCriteria) {
            return;
        }
        // create a copy of the linked list as an array
        Book[] array = new Book[length];
        Book currentBook = headBook;
        for(int i = 0; i < length; i++){
            array[i] = currentBook;
            currentBook = currentBook.getNextBook();
        }
        // sort the array using the criteria
        Comparator<Book> comparator = sortCriteria.getComparator();
        Arrays.sort(array,comparator);
        //rebuild the linked list from the sorted array
        headBook = array[0];
        for(int i = 1; i < length; i++){
            array[i - 1].setNextBook(array[i]);
        }
        tailBook = array[length-1];
        tailBook.setNextBook(null);
        // adjust the sorting criteria
        condition = sortCriteria;
    }

    public String toString(){
        String shelf = String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n", "Name", "Author","Genre","ISBN","Year Published","UserID","Book Condition",
                "CheckOutDate","CheckedOut");
        Book currBook = headBook;
        while(currBook != null){
            shelf += currBook.toString();
            currBook = currBook.getNextBook();
        }
        return shelf;
    }

    public static void main(String...args) throws BookAlreadyExistException, BookDoesNotExistException {
        Shelf shelf1 = new Shelf();
        shelf1.addBook(new Book("Bob","Jun","Fiction",1234555891012L,2002,11333232, Book.Condition.NEW,new Date(10,10,2002),true));
        shelf1.addBook(new Book("pop","Jun","Fiction",1223234910123L,2002,11333232, Book.Condition.NEW,new Date(10,9,2002),true));
        shelf1.addBook(new Book("mop","Jun","Fiction",2223234,2002,11333232, Book.Condition.NEW,new Date(10,9,2002),true));
        shelf1.addBook(new Book("wop","Jun","Fiction",3223234,2002,11333232, Book.Condition.NEW,new Date(10,9,2002),true));
        shelf1.removeBook("bob");
        System.out.println(shelf1);
        shelf1.sort(SortCriteria.ISBN);
        System.out.println(shelf1);
    }
}
